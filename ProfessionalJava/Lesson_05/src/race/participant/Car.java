package race.participant;

import race.track.*;
import race.Main;


public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        //this.START = START;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            Main.START.countDown();
            Main.START.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(Main.START.getCount());
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
            //Main.FINISH.countDown();
        }
    }
}