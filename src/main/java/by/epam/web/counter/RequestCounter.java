package by.epam.web.counter;

public class RequestCounter {
    private static int counter = 0;

    public static synchronized void increment() {
        counter++;
    }

    public static synchronized void decrement() {
        counter--;
    }

    public static int getCounter() {
        return counter;
    }
}
