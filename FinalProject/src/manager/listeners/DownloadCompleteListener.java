package manager.listeners;

import java.io.File;

public interface DownloadCompleteListener {
    void onDownloadSuccess(File file);

    void onDownloadFailed();
}
