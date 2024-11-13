public class UsingThreadTest {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis(); // 개시 시간

        Runnable evenSumTask = new Runnable() {
            public void run() {
                long evenSum = 0; // 짝수합
                for (long i = Integer.MIN_VALUE; i <= Integer.MAX_VALUE; i++) {
                    if (i % 2 == 0) {
                        evenSum += i;
                    }
                }
                System.out.println("evenSum: " + evenSum);
            }
        };

        Runnable oddSumTask = new Runnable() {
            public void run() {
                long oddSum = 0; // 홀수합
                for (long i = Integer.MIN_VALUE; i <= Integer.MAX_VALUE; i++) {
                    if (i % 2 != 0) {
                        oddSum += i;
                    }
                }
                System.out.println("oddSum: " + oddSum);
            }
        };

        Thread t1 = new Thread(evenSumTask);
        Thread t2 = new Thread(oddSumTask);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        long end = System.currentTimeMillis(); // 종료 시간
        System.out.println("실행시간: " + (end - start) / 1000.0 + "초");
    }
}
