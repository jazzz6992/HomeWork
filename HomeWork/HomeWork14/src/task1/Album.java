package task1;

import org.jaudiotagger.audio.AudioFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Album {
    private Map<String, Set<AudioFile>> songs;

    Album() {
        songs = new HashMap<>();
    }

    public Map<String, Set<AudioFile>> getSongs() {
        return songs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Set<AudioFile>> entry :
                songs.entrySet()) {
            for (AudioFile file :
                    entry.getValue()) {
                sb.append("\t\t").append(entry.getKey())
                        .append(", ")
                        .append("duration = ")
                        .append(file.getAudioHeader().getTrackLength())
                        .append(" seconds, ")
                        .append(file.getFile().getAbsolutePath())
                        .append("\n");
            }
        }
        sb.append("\n");
        return sb.toString();
    }
}
