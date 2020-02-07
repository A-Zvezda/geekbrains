package race.participant;

import race.Race;

public class Car extends Thread {
    private static int CARS_COUNT = 0;

    private Race race;
    private int speed;


    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        setName("Участник №" + ++CARS_COUNT);
        //    this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            race.start(getName());
            race.getStages().forEach(stage -> stage.go(this));
            race.finish(getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}