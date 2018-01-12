package l15;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadThread extends Thread {
    private File file;
    //ответ на вопрос из третьего задания, о том, почему wait и notify объявлены в Object
    private FileReciever reciever;

    public DownloadThread(FileReciever reciever) {
        this.reciever = reciever;
    }

    @Override
    public void run() {
        file = downloadFile("http://kiparo.ru/t/stock.xml");
        reciever.setFileReadyForProcess(file);
        synchronized (reciever) {
            reciever.notifyAll();
        }
        try {
            synchronized (reciever) {
                reciever.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        file = downloadFile("http://kiparo.ru/t/stock.json");
        reciever.setFileReadyForProcess(file);
        synchronized (reciever) {
            reciever.notifyAll();
        }
    }

    public File downloadFile(String link) {
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                File file = new File(link.substring(link.lastIndexOf("/") + 1));
                file.createNewFile();
                try (InputStream is = connection.getInputStream()) {
                    try (FileOutputStream fos = new FileOutputStream(file)) {
                        byte[] buf = new byte[1024];
                        int count;
                        while ((count = is.read(buf)) > 0) {
                            fos.write(buf, 0, count);
                        }
                    }
                }
                connection.disconnect();
                return file;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
