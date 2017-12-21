package task1;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import task1.entity.Root;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) throws IOException {
        String linkJson = "http://kiparo.ru/t/test.json";
        File file = new File("src/task1/newfile.json");
        if (!file.exists()) {
            if (!file.createNewFile()) {
                return;
            }
        }
        URL url = new URL(linkJson);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            try (InputStream is = connection.getInputStream()) {
                try (OutputStream os = new FileOutputStream(file)) {
                    byte[] buf = new byte[2048];
                    int bufSize;
                    while ((bufSize = is.read(buf)) != -1) {
                        os.write(buf, 0, bufSize);
                    }
                }
            }
            Root root = parseJackson(file);
            System.out.println(root);
            String result = serializeJackson(root);
            System.out.println(result);
        }
    }

    private static Root parseJackson(File file) {
        Root root = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        try {
            root = mapper.readValue(file, Root.class);
            return root;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    private static String serializeJackson(Root root) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        try {
            String result = mapper.writeValueAsString(root);
            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
