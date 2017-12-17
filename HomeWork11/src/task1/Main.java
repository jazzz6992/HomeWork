package task1;

import org.xml.sax.SAXException;
import task1.entity.Root;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        String link = "http://kiparo.ru/t/test.xml";
        File file = new File("src/task1/test.xml");
        URL url = new URL(link);
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
                    while ((bufSize = inputStream.read(buf)) != -1) {
                        outputStream.write(buf, 0, bufSize);
                    }
                }
            }
            Root root = parseXml(file);
            System.out.println(root.getHumans().toString());
        }
    }

    public static Root parseXml(File file) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        MyHandler handler = new MyHandler();
        parser.parse(file, handler);
        return handler.getRoot();
    }
}
