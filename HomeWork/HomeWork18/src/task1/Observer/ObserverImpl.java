package task1.Observer;

public class ObserverImpl implements MyObserver {

    MyObservable observable;

    public ObserverImpl(MyObservable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update() {
        if (observable instanceof ObservableImpl) {
            System.out.println(((ObservableImpl) observable).getI());
        }
    }
}
