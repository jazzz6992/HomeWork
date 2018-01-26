package manager.interfaces;

import java.io.File;

//интерфейс для отслеживания результата загрузки
public interface DownloadCompleteListener {
    void onDownloadSuccess(File file);

    void onDownloadFailed(String message);
}
