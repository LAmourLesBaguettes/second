class Bus {
    private int availableSeats = 10;

    public synchronized boolean reserveSeats(int requestedSeats) {
        System.out.println(Thread.currentThread().getName() + "가 들어왔음.");
        System.out.println("가능한 좌석 수: " + availableSeats + ", 요청 좌석 수: " + requestedSeats);
        if (availableSeats >= requestedSeats) {
            availableSeats -= requestedSeats;
            System.out.println(requestedSeats + " 좌석이 예약되었음.");
            System.out.println(Thread.currentThread().getName() + "가 나갑니다.");
            System.out.println("------------------------");
            return true;
        } else {
            System.out.println("좌석 예약이 불가능합니다.");
            System.out.println(Thread.currentThread().getName() + "가 나갑니다.");
            System.out.println("------------------------");
            return false;
        }
    }
}

class Passenger implements Runnable {
    private Bus bus;
    private int requestedSeats;

    public Passenger(Bus bus, int requestedSeats) {
        this.bus = bus;
        this.requestedSeats = requestedSeats;
    }

    @Override
    public void run() {
        bus.reserveSeats(requestedSeats);
    }
}

public class BusSeatReservation {
    public static void main(String[] args) {
        Bus bus = new Bus();

        Thread t1 = new Thread(new Passenger(bus, 5), "Thread-0");
        Thread t2 = new Thread(new Passenger(bus, 2), "Thread-2");
        Thread t3 = new Thread(new Passenger(bus, 4), "Thread-1");

        t1.start();
        t2.start();
        t3.start();
    }
}

