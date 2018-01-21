package task1.Adapter;

public class Main {

    public static void main(String[] args) {
        SomeInterfaceWeNeedBadly interfaceWeNeedBadly = new AdapterToSolveTheProblem(new ClassWeHave());
        interfaceWeNeedBadly.doStuff();
    }
}
