/**
*1. Создать массив с набором слов (10-20 слов, среди которых должны встречаться повторяющиеся). Найти и вывести список
*уникальных слов, из которых состоит массив (дубликаты не считаем). Посчитать, сколько раз встречается каждое слово.
*@author      Aleksandr Zvezda
*/

package Lesson_03.task_01;

public class AnalyzerMain {
    public static void main (String[] arg) {
        ArrayAnalyzer sd = new ArrayAnalyzer();
        sd.printUniqueWordsAllInOne();
    }
}
