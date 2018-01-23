package manager.listeners;

//интерфейс для отслеживания изменений модели
public interface DataChangedResultListener {
    void onSuccess();

    void onFail(String message);
}
