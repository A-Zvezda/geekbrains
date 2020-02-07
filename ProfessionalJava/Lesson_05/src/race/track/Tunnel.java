package race.track;

import race.participant.*;
import java.util.concurrent.Semaphore;

public class Tunnel extends Road {
    private Semaphore semaphore;
    public Tunnel(int limit) {
        super(80);
        semaphore = new Semaphore(limit);
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                semaphore.acquire();
                super.go(c);
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