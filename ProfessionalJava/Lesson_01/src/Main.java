import containers.Box;
import fruits.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main implements Processor<Fruit> {
    private static String[] stringArray = new String[10];


    public static void main (String args[]) {
        fillArray();
        task01();
        task02();
        warehouse();
    }

    private static void fillArray(){
        for (int i = 0; i < stringArray.length; i++) {
            stringArray[i] = String.valueOf(i);
        }
    }

    private static void task01() {
        System.out.println(Arrays.toString(stringArray));
        System.out.println(Arrays.toString(changeElements(stringArray,0,1)));
    }

    private static void  task02() {
        ArrayList<String> arrayList;
        arrayList = convertArrayToArrayList(stringArray);
        System.out.println(arrayList);
    }

    public static <T extends Object> T[] changeElements (T list[],int index1, int index2) {

        T element = list[index1];
        list [index1] = list[index2];
        list [index2] = element;
        return list;
    }

    public static <T> ArrayList<T> convertArrayToArrayList(T list[]) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (T o: list) {
            arrayList.add(o);
        }
        return arrayList;
    }

    public static void warehouse() {
        ArrayList<Apple> apples1 = new ArrayList<>();
        for (int i = 0; i < 5; i ++) {
            String  name = "Я " + String.valueOf(i);
            apples1.add(new Apple(name , Double.valueOf(i)));
        }
        ArrayList<Apple> apples2 = new ArrayList<>();
        for (int i = 0; i < 5; i ++) {
            String  name = "Я " + String.valueOf(i);
            apples2.add(new Apple(name , Double.valueOf(i)));
        }
        ArrayList<Orange> orange1 = new ArrayList<>();
        for (int i = 0; i < 5; i ++) {
            String  name = "А " + String.valueOf(i);
            orange1.add(new Orange(name , Double.valueOf(i)));
        }
        ArrayList<Orange> orange2 = new ArrayList<>();
        for (int i = 0; i < 5; i ++) {
            String  name = "А  " + String.valueOf(i);
            orange2.add(new Orange(name , Double.valueOf(i)));
        }
        Box appleBox = new Box(apples1,"Яблоки");
        Box appleBox1 = new Box(apples2,"Яблоки1");

        Box orangeBox = new Box(orange1,"Апельсины");
        Box orangeBox1 = new Box(orange2,"Апельсины");

    }
    @Override
    public boolean moveToBox(Fruit v1, Fruit v2) {
        return true;
    }
}
