package race;


import race.track.*;
import race.participant.Car;
import java.util.concurrent.*;

public class Main {
    public static final CountDownLatch START = new CountDownLatch(5);
    public static final CountDownLatch FINISH = new CountDownLatch(4);
    public static final boolean[] TUNNEL_CAPACITY  = new boolean[2];
    public static final Semaphore SEMAPHORE = new Semaphore(2, true);

    public static final int CARS_COUNT = 4;
    public static void main(String[] args) throws InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));

        ExecutorService executorService = Executors.newFixedThreadPool(CARS_COUNT);
        for (int i = 0; i < CARS_COUNT; i++) {
            executorService.execute(new Car(race, 20 + (int) (Math.random() * 10)));
        }

        while (true)  {
            if (START.getCount() == 1) {
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
                START.countDown();
                break;
            }
        }
        executorService.shutdown();
        while (true) {
            if (executorService.isShutdown()) {
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
                break;
            }
        }

    }
}
