package Lesson_03.task_02;

import java.util.*;

public class PhoneBook {
    Map<String,  HashSet<String>> hm = new HashMap<>();
    public void addRecord(String name,String phoneNumber) {
        HashSet<String> hs = this.hm.get(name);
        if (hs == null) {
            hs = new HashSet<>();
        }
        hs.add(phoneNumber);
        this.hm.put(name, hs);
    }

    /**
     * Метод выводит данные абонента по его имени
     * @param name - имя абонента
     */
    public void getRecord(String name) {
        HashSet hs = this.hm.get(name);
        System.out.println(name);
        System.out.println(hs);
    }

    /**
     * Метод выводит все данные содержащиеся в телефонной книге
     */
    public void printAllRecord() {
        for(Map.Entry<String, HashSet<String>> entry : this.hm.entrySet()) {
            String key = entry.getKey();
            HashSet value = entry.getValue();
            System.out.println(key);
            System.out.println(value);
        }
    }

    /**
     * Метод увдаляет телефонный номер из телефонной книги
     * @param name - Имя абонента
     * @param phoneNumber - Номер телефона
     */
    public void removePhone(String name, String phoneNumber) {
        HashSet<String> hs = this.hm.get(name);
        hs.remove(phoneNumber);
    }

    /**
     * Удаляет контакт из телефонной книги
     * @param name - имя абонента
     */
    public void removeContact(String name) {
        this.hm.remove(name);
    }
}
