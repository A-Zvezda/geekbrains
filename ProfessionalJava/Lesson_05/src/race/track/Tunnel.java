package race.track;


import race.Main;
import race.participant.*;

public class Tunnel extends Stage {
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                Main.SEMAPHORE.acquire();

                    int capacityNumber = -1;

                    //Ищем свободное место и паркуемся
                    synchronized (Main.TUNNEL_CAPACITY){
                        for (int i = 0; i < Main.TUNNEL_CAPACITY.length; i++)
                            if (!Main.TUNNEL_CAPACITY[i]) {      //Если место свободно
                                Main.TUNNEL_CAPACITY[i] = true;  //занимаем его
                                capacityNumber = i;         //Наличие свободного места, гарантирует семафор
                                System.out.println(c.getName() + " начал этап: " + description);
                                break;
                            }
                        synchronized (Main.TUNNEL_CAPACITY) {
                            Main.TUNNEL_CAPACITY[capacityNumber] = false;//Освобождаем место
                        }

                        Main.SEMAPHORE.release();

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