package task1.Observer;

public interface MyObservable {
    void addObserver(MyObserver observer);

    void removeObserver(MyObserver observer);

    void notifyObservers();
}
