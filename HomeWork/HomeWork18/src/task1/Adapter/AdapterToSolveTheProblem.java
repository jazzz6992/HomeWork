package task1.Adapter;

public class AdapterToSolveTheProblem implements SomeInterfaceWeNeedBadly {
    SomeInterfaceWeHave someInterfaceWeHave;

    public AdapterToSolveTheProblem(SomeInterfaceWeHave someInterfaceWeHave) {
        this.someInterfaceWeHave = someInterfaceWeHave;
    }


    @Override
    public void doStuff() {
        someInterfaceWeHave.makeStuff();
    }
}
