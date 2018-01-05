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
            try {
                HttpURLConnection connection = createConnection(link);
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

    private File createFile(String fileName) {
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

    private HttpURLConnection createConnection(String link) throws IOException {
        URL url = new URL(link);
        return (HttpURLConnection) url.openConnection();
    }

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
        synchronized (listener) {
            downloadFile(urlLink, path);
        }
    }
}
