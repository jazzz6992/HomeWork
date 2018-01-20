package manager.download;

import manager.listeners.DownloadCompleteListener;
import model.Model;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Downloader implements Runnable {
    private String urlLink;
    private String path;
    private DownloadCompleteListener listener;
    private final Model monitor;

    /*
    конструктор, с параметрами, необходимыми, для загрузки файла, так же принимает объект-монитор,
    используемый для синхронизации (менеджер передает Model)
     */
    public Downloader(String urlLink, String path, DownloadCompleteListener listener, Model monitor) {
        this.urlLink = urlLink;
        this.path = path;
        this.listener = listener;
        this.monitor = monitor;
    }

    /*
    создает файл и пытается загрузить в него данные из интернета. по завершении
    вызывает метод слушателя в зависимости от результата выполнения
     */
    private void downloadFile() {
        File file = createFile(path);
        if (file != null) {
            try {
                HttpURLConnection connection = createConnection(urlLink);
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    try {
                        download(connection, file);
                        listener.onDownloadSuccess(file);
                    } catch (IOException e) {
                        listener.onDownloadFailed(e.getMessage());
                    }
                } else {
                    listener.onDownloadFailed("Response code is " + connection.getResponseCode());
                }
            } catch (IOException e) {
                listener.onDownloadFailed(e.getMessage());
            }
        } else {
            listener.onDownloadFailed("Can't create file");
        }
    }

    /*
    создает файл по указанному адресу, если его еще не существует и возвращает объект этого файла.
    в случае, если создание файла невозможно, возвращает null
     */
    private File createFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    return null;
                }
            } catch (IOException e) {
                listener.onDownloadFailed(e.getMessage());
                return null;
            }
        }
        return file;
    }

    private HttpURLConnection createConnection(String link) throws IOException {
        URL url = new URL(link);
        return (HttpURLConnection) url.openConnection();
    }

    /*
    скафивает данные из интернета в переданный файл
     */
    private void download(HttpURLConnection connection, File file) throws IOException {
        try (InputStream is = connection.getInputStream()) {
            try (OutputStream os = new FileOutputStream(file)) {
                byte[] buf = new byte[4096];
                int bufSize;
                while ((bufSize = is.read(buf)) != -1) {
                    os.write(buf, 0, bufSize);
                }
            }
        }
    }

    @Override
    public void run() {
        synchronized (monitor) {
            downloadFile();
        }
    }
}
