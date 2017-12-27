package manager.download;

import manager.listeners.DownloadCompleteListener;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Downloader implements Runnable {
    private String urlLink;
    private String path;
    private DownloadCompleteListener listener;

    public Downloader(String urlLink, String path, DownloadCompleteListener listener) {
        this.urlLink = urlLink;
        this.path = path;
        this.listener = listener;
    }

    private void downloadFile(String link, String fileName) {
        File file = createFile(fileName);
        if (file != null) {
            HttpURLConnection connection = createConnection(link);
            if (connection != null) {
                if (download(connection, file)) {
                    listener.onDownloadSuccess(file);
                } else {
                    listener.onDownloadFailed();
                }
            } else {
                listener.onDownloadFailed();
            }
        } else {
            listener.onDownloadFailed();
        }
    }

    private static File createFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return file;
    }

    private static HttpURLConnection createConnection(String link) {
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return connection;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean download(HttpURLConnection connection, File file) {
        try (InputStream is = connection.getInputStream()) {
            try (OutputStream os = new FileOutputStream(file)) {
                byte[] buf = new byte[4096];
                int bufSize;
                while ((bufSize = is.read(buf)) != -1) {
                    os.write(buf, 0, bufSize);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void run() {
        synchronized (listener) {
            downloadFile(urlLink, path);
        }
    }
}
