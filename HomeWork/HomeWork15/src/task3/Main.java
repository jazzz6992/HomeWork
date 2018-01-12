package task3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main extends Thread {


    public synchronized void print10(int counter) {
        System.out.print(Thread.currentThread().getName() + ": ");
        for (int i = 0; i < 10; i++, counter++) {
            System.out.print(counter + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Main main = new Main();
        PrintThread printThread1 = new PrintThread("Thread 1", main);
        PrintThread printThread2 = new PrintThread("Thread 2", main);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s;
            while (!(s = reader.readLine()).equals("start")) {

            }
            printThread1.start();
            printThread2.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
