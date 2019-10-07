package Lesson_02;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args)  {
        int capacity = 1000000;
        Random random = new Random();


        Integer intArr [] = new Integer[capacity];
        for (int i = 0; i < capacity ; i++) {
            intArr[i] = random.nextInt(100);
        }
        System.out.println("Кол-во элементов : " + capacity);
        MyArraylist<Integer> mal = new MyArraylist<>(intArr);
        MyArraylist<Integer> mal1 = new MyArraylist<>(intArr);
        MyArraylist<Integer> mal2 = new MyArraylist<>(intArr);
        MyThreadForSort t1 = new MyThreadForSort(SortType.SELECTION,mal);
        MyThreadForSort t2 = new MyThreadForSort(SortType.INSERTION,mal1);
        MyThreadForSort t3 = new MyThreadForSort(SortType.BUBBLE,mal2);

        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t3.start();
        try {
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long a = System.currentTimeMillis();
        Arrays.sort(intArr);
        System.out.println("Сортировка Array.sort() выполнялась "  + (System.currentTimeMillis() - a ) + " мс.");
    }

}

class MyThreadForSort extends Thread {
    SortType sortType;
    MyArraylist mal;
    MyThreadForSort(SortType sortType,MyArraylist mal) {
        this.sortType = sortType;
        this.mal = mal;
    }
    @Override
    public void run() {

        long a = System.currentTimeMillis();
        if (this.sortType == sortType.BUBBLE) {
            mal.bubbleSort();
        } else if (this.sortType == sortType.INSERTION) {
            mal.insertionSort();
        } else if (this.sortType == sortType.SELECTION) {
            mal.selectionSort();
        }
        System.out.println("Сортировка " + this.sortType.getSortName() + " выполнялась "  + (System.currentTimeMillis() - a ) + " мс.");
    }


}


enum SortType {
    BUBBLE("bubble"), INSERTION("insertion"), SELECTION("selection");

    private String sortName;


    SortType(String sortName) {
        this.sortName = sortName;

    }

    public String getSortName() {
        return sortName;
    }

}