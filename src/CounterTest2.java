class Counter2 {
    private int value = 0;

    public void increment() {
        synchronized (this) {
            value++;
        }
    }

    public void decrement() {
        synchronized (this) {
            value--;
        }
    }

    public int getValue() {
        return value;
    }
}

public class CounterTest2 {
    public static void main(String[] args) throws InterruptedException {
        Counter2 counter = new Counter2(); // 수정된 부분

        Runnable incrementTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    counter.increment();
                }
            }
        };

        Runnable decrementTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    counter.decrement();
                }
            }
        };

        Thread t1 = new Thread(incrementTask);
        Thread t2 = new Thread(decrementTask);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final value: " + counter.getValue());
    }
}