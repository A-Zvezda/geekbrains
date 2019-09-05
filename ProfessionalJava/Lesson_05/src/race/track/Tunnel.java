package race.track;


import race.StartRace;
import race.participant.*;

public class Tunnel extends Stage {
    private StartRace controller;
    public Tunnel(StartRace controller) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.controller = controller;
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                controller.SEMAPHORE.acquire();
                    int capacityNumber = -1;
                    synchronized (controller.TUNNEL_CAPACITY){
                        for (int i = 0; i < controller.TUNNEL_CAPACITY.length; i++)
                            if (!controller.TUNNEL_CAPACITY[i]) {
                                controller.TUNNEL_CAPACITY[i] = true;
                                capacityNumber = i;
                                System.out.println(c.getName() + " начал этап: " + description);
                                break;
                            }
                        Thread.sleep(length / c.getSpeed() * 1000);
                        synchronized (controller.TUNNEL_CAPACITY) {
                            controller.TUNNEL_CAPACITY[capacityNumber] = false;
                        }
                        controller.SEMAPHORE.release();

                    }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}