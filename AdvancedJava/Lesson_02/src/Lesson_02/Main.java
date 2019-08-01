package Lesson_02;

import Lesson_02.exception.MyArrayDataException;
import Lesson_02.exception.MyArraySizeException;

public class Main {

    public static void main(String[] args) {
        String[][] arrayForWork=  {{"1","1","1","0"}, {"3","4","1","1"}, {"3","4","1","1"}, {"3","4","1","1"}};
        int result = 0;
        try {
            result = doWork(arrayForWork);
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage() + ". Величина массива " + e.getCol() + " на " + e.getRow() + " элементов");
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage() + ". В ячейке  [" + e.getCol() + "][" + e.getRow() + "] содержатся данные, которые невозможно преобразовать к целому числу! В ячейке содержится строка: " + e.getValue());
        }
        System.out.println("Сумма: " + result );

    }
    private static int doWork (String[][] arrayForWork) throws MyArrayDataException,MyArraySizeException {
        for (int i = 0; i < arrayForWork.length; i++){
            if (arrayForWork.length != 4|| arrayForWork[i].length != 4) {
                throw new MyArraySizeException ("Величина массива не может быть больше 4х элементов", arrayForWork.length  , arrayForWork[i].length );
            }
        }
        int sum = 0;
        for (int i = 0; i < arrayForWork.length; i ++) {
            for (int j = 0; j < arrayForWork[i].length; j++) {
                try {

                    sum += Integer.parseInt(arrayForWork[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Ошибка преобразования", j, i, arrayForWork[i][j]);
                }

            }
        }
        return sum;
    }
}
