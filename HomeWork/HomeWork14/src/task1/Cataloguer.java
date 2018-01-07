package task1;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cataloguer {

    private Mp3Collection collection = new Mp3Collection();
    private Map<String, Set<AudioFile>> checkSumDuplicates = new HashMap<>();
    private Map<String, Set<AudioFile>> tagInfoDuplicates = new HashMap<>();
    private CheckSumMaker checkSumMaker = new CheckSumMaker();
    private String[] formats = {".mp3", ".m4a", ".ogg", ".flac", ".wma"};
    private int counter = 0;

    public void createCollection(String[] dirs) {
        for (String s :
                dirs) {
            File cur = new File(s);
            if (cur.exists() && cur.isDirectory()) {
                createCollection(cur);
            }
        }
        System.out.println("Added = " + counter + " songs");
    }

    private void createCollection(File dir) {
        File[] childs = dir.listFiles();
        if (childs != null) {
            for (File f : childs) {
                if (f.isDirectory()) {
                    createCollection(f);
                } else if (f.isFile()) {
                    String fileNameSuffix = null;
                    try {
                        fileNameSuffix = f.getName().toLowerCase().substring(f.getName().lastIndexOf("."));
                    } catch (StringIndexOutOfBoundsException e) {
                        System.err.println("Can't read format for file " + f.getAbsolutePath());
                    }
                    if (fileNameSuffix != null && checkFormats(fileNameSuffix)) {
                        try {
                            AudioFile current = AudioFileIO.read(f);
                            Tag tag = current.getTag();
                            String artistName = tag.getFirst(FieldKey.ARTIST);
                            String albumName = tag.getFirst(FieldKey.ALBUM);
                            String title = tag.getFirst(FieldKey.TITLE);
                            Artist artist = createArtistIfNotExist(artistName);
                            Album album = createAlbumIfNotExist(albumName, artist);
                            Set<AudioFile> sameSongsInAlbum = createSameSongSetIfNotExist(title, album);
                            if (!sameSongsInAlbum.isEmpty()) {
                                addToTagInfoDuplicates(current, sameSongsInAlbum);
                                addToChecksumDuplicates(current, sameSongsInAlbum);
                            }
                            sameSongsInAlbum.add(current);
                            counter++;
                        } catch (CannotReadException e) {
                            System.out.println("Can't read file " + f.getAbsolutePath());
                            System.out.println(e.getMessage());
                        } catch (IOException e) {
                            System.out.println("I/O exception while reading file " + f.getAbsolutePath());
                            System.out.println(e.getMessage());
                        } catch (TagException e) {
                            System.out.println("Tag exception while reading file " + f.getAbsolutePath());
                            System.out.println(e.getMessage());
                        } catch (ReadOnlyFileException e) {
                            System.out.println("Read only permission granted for file " + f.getAbsolutePath());
                            System.out.println(e.getMessage());
                        } catch (InvalidAudioFrameException e) {
                            System.out.println("Invalid audio frame for file " + f.getAbsolutePath());
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
        }
    }

    public String checkSumDuplicatesToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Checksum duplicates:\n");
        for (Map.Entry<String, Set<AudioFile>> entry :
                checkSumDuplicates.entrySet()) {
            sb.append("\tDuplicates for checksum ").append(entry.getKey()).append(":\n");
            for (AudioFile file :
                    entry.getValue()) {
                sb.append("\t\t").append(file.getFile().getAbsolutePath()).append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String tagInfoDuplicatesToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name duplicates:\n");
        for (Map.Entry<String, Set<AudioFile>> entry :
                tagInfoDuplicates.entrySet()) {
            sb.append("\tDuplicates for tag info ").append(entry.getKey()).append(":\n");
            for (AudioFile file :
                    entry.getValue()) {
                sb.append("\t\t").append(file.getFile().getAbsolutePath()).append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private Artist createArtistIfNotExist(String name) {
        Artist artist = collection.getArtists().get(name);
        if (artist == null) {
            artist = new Artist();
            collection.getArtists().put(name, artist);
        }
        return artist;
    }

    private Album createAlbumIfNotExist(String name, Artist artist) {
        Album album = artist.getAlbums().get(name);
        if (album == null) {
            album = new Album();
            artist.getAlbums().put(name, album);
        }
        return album;
    }

    private Set<AudioFile> createSameSongSetIfNotExist(String title, Album album) {
        return album.getSongs().computeIfAbsent(title, k -> new HashSet<>());
    }

    private void addToTagInfoDuplicates(AudioFile fileToAdd, Set<AudioFile> sameSongsInAlbum) {
        Tag tag = fileToAdd.getTag();
        String fullTagInfo = tag.getFirst(FieldKey.ARTIST) + ", " + tag.getFirst(FieldKey.ALBUM) + ", " + tag.getFirst(FieldKey.TITLE);
        Set<AudioFile> curNameDups = tagInfoDuplicates.computeIfAbsent(fullTagInfo, k -> new HashSet<>());
        curNameDups.add(fileToAdd);
        curNameDups.addAll(sameSongsInAlbum);
    }

    private void addToChecksumDuplicates(AudioFile current, Set<AudioFile> sameSongsInAlbum) {
        boolean hasDuplicat = false;
        String checkSum = checkSumMaker.getCheckSum(current.getFile());
        Set<AudioFile> currentDuplicates = checkSumDuplicates.get(checkSum);
        for (AudioFile file :
                sameSongsInAlbum) {
            if (checkSum.equals(checkSumMaker.getCheckSum(file.getFile()))) {
                if (currentDuplicates == null) {
                    currentDuplicates = new HashSet<>();
                    checkSumDuplicates.put(checkSum, currentDuplicates);
                }
                currentDuplicates.add(file);
                hasDuplicat = true;
            }
        }
        if (hasDuplicat) {
            currentDuplicates.add(current);
        }
    }

    private boolean checkFormats(String format) {
        for (String supported :
                formats) {
            if (format.equals(supported)) {
                return true;
            }
        }
        return false;
    }

    public Mp3Collection getCollection() {
        return collection;
    }
}
