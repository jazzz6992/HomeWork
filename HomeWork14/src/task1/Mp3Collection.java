package task1;

import java.util.HashMap;
import java.util.Map;

public class Mp3Collection {
    private Map<String, Artist> artists;

    Mp3Collection() {
        artists = new HashMap<>();
    }

    public Map<String, Artist> getArtists() {
        return artists;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Artist> entry :
                artists.entrySet()) {
            sb.append(entry.getKey()).append("\n");
            sb.append(entry.getValue().toString());
        }
        return sb.toString();
    }
}
