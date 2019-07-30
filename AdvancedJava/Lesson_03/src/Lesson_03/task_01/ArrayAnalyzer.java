package Lesson_03.task_01;

import java.util.*;
/**
*@author      Aleksandr Zvezda
 */
public class ArrayAnalyzer {
    private String[] arrayForWork;
    public ArrayAnalyzer() {
        this.arrayForWork = new String[]{"Volvo", "BMW", "Ford", "Mazda","Mazda","Citroen",
                "Aston Martin", "Jeep", "Lamborghini", "Mitsubishi", "Volvo", "Toyota", "Pagani",
                "Chrysler", "Ferrari", "Fiat", "Ford"};
    }
    public ArrayAnalyzer(String[] arrayForWork) {

        this.arrayForWork = new String[arrayForWork.length];
        this.arrayForWork = arrayForWork.clone();

    }
    /**
     * Процедура вывода данных из массива.
     * Выводит только уникальные записи,
     * и уникальные записи с количеством повоторений.
     */
    public void printUniqueWordsAllInOne () {

        Map<String, Integer> hm = new HashMap<>();

        for (int i = 0; i < this.arrayForWork.length; i++) {
            String brand = arrayForWork[i];
            Integer result = hm.get(brand);
            hm.put(brand, result == null ? 1 : result + 1);
        }
        System.out.println(hm.keySet());
        System.out.println(hm);
    }



}
