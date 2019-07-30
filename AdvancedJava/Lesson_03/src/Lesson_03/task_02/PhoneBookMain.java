/**
 *Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
 *В этот телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get() искать
 *номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов,
 *тогда при запросе такой фамилии должны выводиться все телефоны.
 *@author      Aleksandr Zvezda
 */

package Lesson_03.task_02;

public class PhoneBookMain {
    public static void main (String[] arg) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addRecord("Иванов","+76851254124");
        phoneBook.addRecord("Иванов","+76851254125");
        phoneBook.addRecord("Петров","+76851254125");
        phoneBook.addRecord("Сидоров","+76851254125");
        phoneBook.getRecord("Иванов");
        phoneBook.removePhone("Иванов","+76851254125");
        phoneBook.getRecord("Иванов");
        phoneBook.removeContact("Иванов");
        phoneBook.printAllRecord();
    }

}
