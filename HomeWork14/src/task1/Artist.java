package task1;

import java.util.HashMap;
import java.util.Map;

public class Artist {
    private Map<String, Album> albums;

    Artist() {
        albums = new HashMap<>();
    }

    public Map<String, Album> getAlbums() {
        return albums;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Album> entry :
                albums.entrySet()) {
            sb.append("\t").append(entry.getKey()).append("\n");
            sb.append(entry.getValue().toString());
        }
        return sb.toString();
    }
}
