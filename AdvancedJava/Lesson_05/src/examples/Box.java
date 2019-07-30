package Lesson_5;

public class Box {
    Object obj = new Object();

    void doSomeThing() {
        System.out.println(1);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (obj) {
            System.out.println(11);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(22);
        }

        System.out.println(2);
    }
}


class BoxMain {
    public static void main(String[] args) {
        Box box = new Box();

        new Thread(new Runnable() {
            @Override
            public void run() {
                box.doSomeThing();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                box.doSomeThing();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                box.doSomeThing();
            }
        }).start();

    }
}