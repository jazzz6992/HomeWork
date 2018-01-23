package manager.interfaces;

//интерфейс для отслеживания изменений модели
public interface DataChangedResultListener {
    void onSuccess();

    void onFail(String message);
}