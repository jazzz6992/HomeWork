package l9;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        String link = "https://www.tut.by";
        File file = new File("/Users/vsevolodvisnevskij/IdeaProjects/HomeWork/ClassWork/src/l9/newfile.html");
        URL url = new URL(link);
        //почему не обойтись без HttpURLConnection. в URL есть метод openStream();
        //что вообще такое URL и HttpURLConnection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            if (!file.exists()) {
                file.createNewFile();
            }
            try (InputStream inputStream = connection.getInputStream()) {
                try (OutputStream outputStream = new FileOutputStream(file)) {
                    int bufSize;
                    byte[] buf = new byte[1024];
                    //почему не работает через availaible???
                    while ((bufSize = inputStream.read(buf)) != -1) {
                        outputStream.write(buf, 0, bufSize);
                    }
                }
            }
        }
    }
}
