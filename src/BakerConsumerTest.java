import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Table {
    private ReentrantLock lock = new ReentrantLock(); // 도급화를 위한 ReentrantLock
    private Condition condition = lock.newCondition(); // 스레드 조정을 위한 Condition 클래스
    private int cake; // 테이블에 있는 커피스
    private boolean empty = true; // 최종을 테이블이 비어있으면 true

    public void put(int cake) throws InterruptedException {
        lock.lock(); // 다른 스레드가 get에 접근할 수 없도록 단축
        try {
            while (!empty) { // 테이블이 비어있지 않으면
                condition.await(); // 제발내용이 막히면 개시
            }
            this.cake = cake; // 비어있을 인자로 역케스
            empty = false; // 테이블을 채워놓았습니다
            condition.signal(); // 다른 스레드에게 알림
        } finally {
            lock.unlock(); // 다른 스레드가 get에 접근할 수 없도록 단축 해제
        }
    }

    public int get() throws InterruptedException {
        lock.lock(); // 다른 스레드가 put에 접근할 수 없도록 단축
        try {
            while (empty) { // 테이블이 비어있으면
                condition.await(); // 고개가 잠을 산다
            }
            empty = true; // 테이블을 비어인자
            condition.signal(); // 다른 스레드에게 알림
            return cake; // 가져가용을 반환
        } finally {
            lock.unlock(); // 다른 스레드가 put에 접근할 수 없도록 단축 해제
        }
    }
}

class Baker implements Runnable {
    private Table table;

    public Baker(Table table) {
        this.table = table;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                table.put(i); // 케이크를 테이블에 놓기
                System.out.println("제빵사: " + i + "번 케이크를 생산하였습니다.");
                Thread.sleep((int) (Math.random() * 1000.0)); // 랜덤 시간만큼 쉼
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private Table table;

    public Consumer(Table table) {
        this.table = table;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                int cake = table.get(); // 테이블에서 케이크 가져가기
                System.out.println("소비자: " + cake + "번 케이크를 소비하였습니다.");
                Thread.sleep((int) (Math.random() * 1000.0)); // 랜덤 시간만큼 쉼
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class BakerConsumerTest {
    public static void main(String[] args) {
        Table table = new Table();
        new Thread(new Baker(table)).start();
        new Thread(new Consumer(table)).start();
    }
}
