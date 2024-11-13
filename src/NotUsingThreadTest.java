public class NotUsingThreadTest {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); // 개시 시간

        long evenSum = 0; // 짝수합
        for (long i = Integer.MIN_VALUE; i <= Integer.MAX_VALUE; i++) {
            if (i % 2 == 0) {
                evenSum += i;
            }
        }
        System.out.println("evenSum: " + evenSum);

        long oddSum = 0; // 홀수합
        for (long i = Integer.MIN_VALUE; i <= Integer.MAX_VALUE; i++) {
            if (i % 2 != 0) {
                oddSum += i;
            }
        }
        System.out.println("oddSum: " + oddSum);

        long endTime = System.currentTimeMillis(); // 종료 시간
        System.out.println("실행시간: " + (endTime - startTime) / 1000.0 + "초");
    }
}
