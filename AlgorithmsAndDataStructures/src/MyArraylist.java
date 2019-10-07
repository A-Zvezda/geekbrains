import java.util.Comparator;

public class MyArraylist<Item extends Comparable> {
    private Item[] list;
    private int size = 0;
    private final int DEFAULT_CAPACITY = 10;

    public MyArraylist(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity <= 0");
        }
        list = (Item[]) new Comparable[capacity];
    }

    public MyArraylist() {
        list = (Item[]) new Comparable[DEFAULT_CAPACITY];
    }

    public MyArraylist(Item[] items) {
        list = items.clone();
        size = items.length - 1;
    }

    public void add(Item item) {
        list[size] = item;
        size++;
    }

    public void add(int index, Item item) {
        if (index < 0) {
            index = 0;
        }
        if (index > size) {
            index = size;
        }
        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = item;
        size++;
    }

    public boolean remove(Item item) {
        int i = 0;
        while (i < size && !list[i].equals(item)) {
            i++;
        }
        if (i == size) {
            return false;
        }
        for (int j = i; j < size - 1; j++) {
            list[j] = list[j + 1];
        }
        size--;
        list[size] = null;
        return true;
    }

    public Item get(int index) {
        return list[index];
    }

    public void set(int index, Item item) {
        list[index] = item;
    }

    public int size() {
        return size;
    }

    public boolean find(Item item) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < size; i++) {
            s += list[i] + " ";
        }
        return s;
    }


    private boolean less(Item item1, Item item2) {
        return item1.compareTo(item2) < 0;
    }

    private void swap(int index1, int index2) {
        Item temp = list[index1];
        list[index1] = list[index2];
        list[index2] = temp;
    }

    public void selectionSort() {
        int iMin;
        for (int i = 0; i < size - 1; i++) {
            iMin = i;
            for (int j = i + 1; j < size; j++) {
                if (less(list[j], list[iMin])) {
                    iMin = j;
                }
            }
            swap(i, iMin);
        }
    }

    public void insertionSort() {
        Item key;
        for (int i = 1; i < size; i++) {
            int j = i;
            key = list[i];

            while (j > 0 && less(key, list[j - 1])) {
                list[j] = list[j - 1];
                j--;
            }
            list[j] = key;
        }
    }

    public void bubbleSort() {
        bubbleSort(Comparator.naturalOrder());
//        boolean isSwap;
//        for (int i = size - 1; i > 0; i--) {
//            isSwap = false;
//            for (int j = 0; j < i; j++) {
//                if (less(list[j + 1], list[j])) {
//                    swap(j + 1, j);
//                    isSwap = true;
//                }
//            }
//            if (!isSwap) {
////                System.out.println(i);
//                break;
//            }
//        }
    }

    public void bubbleSort(Comparator comparator) {
        boolean isSwap;
        for (int i = size - 1; i > 0; i--) {
            isSwap = false;
            for (int j = 0; j < i; j++) {
//                if (less(list[j + 1], list[j])) {
                if (comparator.compare(list[j + 1], list[j]) < 0) {
                    swap(j + 1, j);
                    isSwap = true;
                }
            }
            if (!isSwap) {
//                System.out.println(i);
                break;
            }
        }
    }

}