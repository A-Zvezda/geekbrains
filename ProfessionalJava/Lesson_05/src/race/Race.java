package race;

import race.participant.Car;
import race.track.Road;
import race.track.Stage;
import race.track.Tunnel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Race {
    private List<Stage> stages;
    private List<Car> cars= new ArrayList<>();
    private static final int CARS_COUNT = 4;
    private CountDownLatch start;
    private CyclicBarrier finish;
    private AtomicInteger result = new AtomicInteger(0);

    private void add (Car car ) {
        cars.add(car);
    }
    public Race (Stage... stages) {
        this.stages = Arrays.asList(stages);
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
    }

    public static  void  main(String[] args) {
        race.Race race = new race.Race(new Road(60), new Tunnel((int)(CARS_COUNT / 2)), new Road(40) );
        for (int i = 0; i < CARS_COUNT; i++) {
            Car car = new Car(race, 20 + (int) (Math.random() * 10));
            race.add(car);
        }
        race.start();
    }

    public void start () {
        int carSize = cars.size();
        start = new CountDownLatch(carSize);
        finish = new CyclicBarrier(carSize, () ->  System.out.println(" закончилась"));
        cars.forEach(Thread::start);
        try {
            start.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> началась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void finish (String name) {
        int position = result.incrementAndGet();
        System.out.println(position == 1 ? name + " - Win" : name + " - занял " + position + " место");
        try {
            finish.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
    public void start (String name) {
        try{
            System.out.println(name + " готовится");
            Thread.sleep(500 +(int) (Math.random() * 800));
            System.out.println(name + " готов");
            start.countDown();
            start.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Stage> getStages() {
        return stages;
    }
}