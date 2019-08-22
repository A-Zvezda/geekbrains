import containers.Box;
import fruits.Apple;
import fruits.Orange;

import java.util.ArrayList;

public class Warehouse {
    public void task03 () {
        ArrayList<Apple> apples1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String name = "Я " + String.valueOf(i);
            apples1.add(new Apple(name, (double) i));
        }
        ArrayList<Apple> apples2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String name = "Я " + i;
            apples2.add(new Apple(name, (double) i));
        }
        ArrayList<Orange> orange1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String name = "А " + i;
            orange1.add(new Orange(name, (double) i));
        }
        ArrayList<Orange> orange2 = new ArrayList<>();
        for (int i = 10; i < 12; i++) {
            String name = "А  " + String.valueOf(i);
            orange2.add(new Orange(name, (double) i));
        }
        Box<Apple> appleBox = new Box<>(apples1, "Яблоки");
        Box<Apple> appleBox1 = new Box<>(apples2, "Яблоки");
        Box<Orange> orangeBox = new Box<>(orange1, "Апельсины");
        Box<Orange> orangeBox1 = new Box<>(orange2, "Апельсины");
        System.out.println(appleBox.compare(appleBox1, orangeBox1));
        System.out.println(appleBox.compare(appleBox1, appleBox));
        appleBox.moveContent(appleBox1);
        //appleBox.moveContent(orangeBox);
    }
}
