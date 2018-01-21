package task1.Observer;

public class Main {
    public static void main(String[] args) {
        MyObservable observable = new ObservableImpl();
        MyObserver observer = new ObserverImpl(observable);
        for (int i = 0; i < 10; i++) {
            ((ObservableImpl) observable).increment();
        }
    }
}
