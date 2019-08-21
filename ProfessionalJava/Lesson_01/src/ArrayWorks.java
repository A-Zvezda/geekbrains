import java.util.ArrayList;
import java.util.Arrays;

public class ArrayWorks {

    private String[] stringArray = new String[10];

    public ArrayWorks () {
        this.fillArray();
    }

    private void fillArray() {
        for (int i = 0; i < this.stringArray.length; i++) {
            this.stringArray[i] = String.valueOf(i);
        }
    }

    public void task01() {
        System.out.println(Arrays.toString(stringArray));
        System.out.println(Arrays.toString(changeElements(stringArray, 0, 1)));
    }
    public void task02() {
        ArrayList<String> arrayList;
        arrayList = convertArrayToArrayList(stringArray);
        System.out.println(arrayList);
    }
    private <T extends Object> T[] changeElements(T list[], int index1, int index2) {

        T element = list[index1];
        list[index1] = list[index2];
        list[index2] = element;
        return list;
    }

    private static <T> ArrayList<T> convertArrayToArrayList(T list[]) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (T o : list) {
            arrayList.add(o);
        }
        return arrayList;
    }
}
