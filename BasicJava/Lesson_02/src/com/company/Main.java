package com.company;
import java.util.Scanner;
/**
 1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;
 2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
 3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ], пройти по нему циклом, и числа, меньшие 6, умножить на 2;
 4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
 5. Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
 6. Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны. Примеры: checkBalance([1, 1, 1, || 2, 1]) > true, checkBalance ([2, 1, 1, 2, 1]) > false, checkBalance ([10, || 10]) > true, граница показана символами ||, эти символы в массив не входят;
 7. *Написать метод, которому на вход подается одномерный массив и число n (может быть положительным или отрицательным), при этом метод должен сместить все элементы массива на n позиций. Нельзя пользоваться вспомогательными массивами.
 @author      Aleksandr Zvezda
 */

public class Main {

    private int arrayItem;

    public static void main(String[] args) {
        int inputArrayLen;
        System.out.println("Урок 2.");
        System.out.println("1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;");
        System.out.println("2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;");
        System.out.println("3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ], пройти по нему циклом, и числа, меньшие 6, умножить на 2;");
        System.out.println("4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью цикла(-ов) заполнить его диагональные элементы единицами;");
        System.out.println("5. Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);");
        System.out.println("6. Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны. Примеры: checkBalance([1, 1, 1, || 2, 1]) > true, checkBalance ([2, 1, 1, 2, 1]) > false, checkBalance ([10, || 10]) > true, граница показана символами ||, эти символы в массив не входят;");
        System.out.println("7. *Написать метод, которому на вход подается одномерный массив и число n (может быть положительным или отрицательным), при этом метод должен сместить все элементы массива на n позиций. Нельзя пользоваться вспомогательными массивами.");
        Scanner userInput = new Scanner(System.in);
        System.out.print("Введите номер задачи: ");
        String userInputData = userInput.nextLine();
        inputArrayLen = inputArrayLen();
        switch (userInputData) {
            case "1":
                binaryArrayChange(getRandomArray(inputArrayLen,100));
                break;
            case "2":
                arithmeticProgression(inputArrayLen);
                break;
            case "3":
                changeArray(getRandomArray(inputArrayLen,10));
                break;
            case "4":
                fillDiagonalArray(getRandomArray(inputArrayLen, true));
                break;
            case "5":
                findMinMaxArrayElement(getRandomArray(inputArrayLen,100));
                break;
            case "6":
                int[] firstArray = {1, 1, 1, 2, 1};
                int[] secondArray = {2, 1, 1, 2, 1};
                int[] thirdArray = {10, 10};
                System.out.println("Тестовые массивы из задания");
                System.out.println(checkBalance(firstArray));
                System.out.println(checkBalance(secondArray));
                System.out.println(checkBalance(thirdArray));
                System.out.println("Окончание тестовых массивов из задания");
                System.out.println("Случайно сгенерированный массив");
                System.out.println(checkBalance(getRandomArray(inputArrayLen,100)));
                break;
            case "7":
                int arrayShift = inputShift();
                shiftArray(getRandomArray(inputArrayLen,100),arrayShift);
                break;
            default:
                System.err.println("Данная операция не предусмотренна!");
        }
        userInput.close();
    }

    private static int inputArrayLen () {
        int inputValueInt = 0;
        Scanner userInput = new Scanner(System.in);
        System.out.print("Введите рамер массива: ");
        String userInputData = userInput.nextLine();
        inputValueInt = parseUserInputInNumber(userInputData);
        if (inputValueInt < 2) {
            System.err.print("Массив не может иметь длину меньше двух!");
            userInput.close();
            System.exit(1);
        }
        return inputValueInt;
    }

    private static int inputShift () {
        int inputValueInt = 0;
        Scanner userInput = new Scanner(System.in);
        System.out.print("Введите рамзер сдвига: ");
        String userInputData = userInput.nextLine();
        inputValueInt = parseUserInputInNumber(userInputData);
        if (inputValueInt == 0) {
            System.err.print("Сдвиг не может быть равен 0!");
            userInput.close();
            System.exit(1);
        }
        return inputValueInt;
    }

    private static int parseUserInputInNumber (String userInputValue) {

        int userInputIntValue = 0;
        try {
            userInputIntValue = Integer.parseInt(userInputValue);
        } catch (NumberFormatException catchedException) {
            System.err.println("Введенное значение не является числом!");
            catchedException.printStackTrace();
            System.exit(1);
        }
        return userInputIntValue;
    }

    private static int[] getRandomArray (int arrayLength, int maxItemValue)  {
        int [] arrayForReturn;
        arrayForReturn = new int[arrayLength];
        for (int i = 0; arrayForReturn.length > i; i++) {
            arrayForReturn[i] = (int)(Math.random() * maxItemValue);
        }
        return arrayForReturn;
    }



    private static int[][] getRandomArray (int arrayLength, boolean itMultidimensional)  {
        int [][] arrayForReturn;
        arrayForReturn = new int[arrayLength][arrayLength];
        for (int i = 0 ; arrayForReturn.length > i; i++) {
            for (int j = 0; arrayForReturn[i].length > j;j++) {
                arrayForReturn[i][j] = 0;
            }
        }
        return arrayForReturn;
    }

    private static void printArray (int [] arrayForOut) {
        for (int arrayItem:arrayForOut) {
            System.out.print(arrayItem +"\t");
        }
    }

    private static void printMultidimensionalArray (int [][] arrayForOut) {
        for (int i = 0 ; arrayForOut.length > i; i++) {
            for (int j = 0; arrayForOut[i].length > j;j++) {
                System.out.print(arrayForOut[i][j] +"\t");
            }
            System.out.print("\n");
        }
    }

    private static void binaryArrayChange(int [] arrayForWork) {
        for (int i = 0; arrayForWork.length > i ; i++) {
            arrayForWork[i] = arrayForWork[i]%2 ==0 ? 1:0;
        }
        System.out.println("Входной массив:");
        printArray(arrayForWork);
        for (int i = 0; arrayForWork.length > i ; i++) {
            if (arrayForWork[i] == 1) {
                arrayForWork[i] = 0;
            } else {
                arrayForWork[i] = 1;
            }
        }
        System.out.println("\nВыходной массив:");
        printArray(arrayForWork);
    }

    private static void arithmeticProgression(int arrayLength) {
        int [] arrayForWork;
        arrayForWork = new int[arrayLength];
        for (int i = 1; arrayForWork.length > i ; i++) {
            arrayForWork[i] = arrayForWork[i - 1] + 3;
        }
        System.out.println("Выходной массив:");
        printArray(arrayForWork);
    }

    private static void changeArray (int[] arrayForWork) {
        System.out.println("Входной массив:");
        printArray(arrayForWork);
        for (int i = 1; arrayForWork.length > i; i++) {
            if (arrayForWork[i] < 6) {
                arrayForWork[i] = arrayForWork[i] * 2;
            }
        }
        System.out.println("\nВыходной массив:");
        printArray(arrayForWork);
    }

    private static void fillDiagonalArray(int[][] arrayForWork) {
        int j = arrayForWork.length - 1;
        for (int i = 0; arrayForWork.length > i; i ++) {
            arrayForWork[i][i] = 1;
            arrayForWork[i][j] = 1;
            j--;
        }
        System.out.println("Выходной массив:");
        printMultidimensionalArray(arrayForWork);
    }

    private static void findMinMaxArrayElement (int[] arrayForWork) {
        System.out.println("Входной массив:");
        printArray(arrayForWork);
        int maxValue = arrayForWork[0], minValue = arrayForWork[0];
        for (int i = 0; arrayForWork.length > i; i ++) {
            maxValue = maxValue > arrayForWork[i] ? maxValue : arrayForWork[i];
            minValue = minValue < arrayForWork[i] ? minValue : arrayForWork[i];
        }
        System.out.print("\nМаксимальное значение: " + maxValue +" Минимальное значение: " + minValue);
    }

    private static boolean checkBalance (int[] arrayForWork) {
        System.out.println("Входной массив:");
        printArray(arrayForWork);
        int foundedHalfArray = arrayForWork.length  % 2 == 0 ? (arrayForWork.length / 2) - 1 : arrayForWork.length / 2;
        int firstHalf = 0, secondHalf = 0;
        for (int i = 0; arrayForWork.length > i; i++) {
            if (foundedHalfArray < i) {
                firstHalf += arrayForWork[i];
            } else {
                secondHalf += arrayForWork[i];
            }
        }
        System.out.print("\n");
        return firstHalf == secondHalf;
    }

    private static void shiftArray(int[] arrayForWork, int shift) {
        System.out.println("Входной массив:");
        printArray(arrayForWork);
        int lastShift;
        if (shift > arrayForWork.length){
            shift = Math.abs(shift % arrayForWork.length);
        } else {
            lastShift = shift;
        }

        if (shift > 0) {
            for (int i = 0; i < shift; i++) {
                int bufferForElement = arrayForWork[0];
                arrayForWork[0] = arrayForWork[arrayForWork.length - 1];
                for (int j = 1; j < arrayForWork.length - 1; j++) {
                    arrayForWork[arrayForWork.length - j] = arrayForWork[arrayForWork.length - j - 1];
                }
                arrayForWork[1] = bufferForElement;
            }
        }
        else if (shift < 0) {
            for (int i = 0; i > shift; i--) {
                int bufferForElement = arrayForWork[arrayForWork.length - 1];
                arrayForWork[arrayForWork.length - 1] = arrayForWork[0];
                for (int j = 1; j < arrayForWork.length - 1; j++) {
                    arrayForWork[j - 1] = arrayForWork[j];
                }
                arrayForWork[arrayForWork.length - 2] = bufferForElement;
            }
        }
        System.out.println("\nВыходной массив:");
        printArray(arrayForWork);
    }

}
