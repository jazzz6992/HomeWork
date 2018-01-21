package task1.Observer;

import java.util.LinkedList;
import java.util.List;

public class ObservableImpl implements MyObservable {
    List<MyObserver> observerList;
    private int i = 0;

    public ObservableImpl() {
        observerList = new LinkedList<>();
    }

    public int getI() {
        return i;
    }

    public void increment() {
        i++;
        dataChanged();
    }

    public void dataChanged() {
        notifyObservers();
    }

    @Override
    public void addObserver(MyObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(MyObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observerList.size(); i++) {
            observerList.get(i).update();
        }
    }
}
