package com.company;

import java.util.Scanner;

/*
    1. Создать пустой проект в IntelliJ IDEA и прописать метод main();
    2. Создать переменные всех пройденных типов данных, и инициализировать их значения;
    3. Написать метод, вычисляющий выражение a * (b + (c / d)) и возвращающий результат, где a, b, c, d – входные параметры этого метода;
    4. Написать метод, принимающий на вход два числа, и проверяющий, что их сумма лежит в пределах от 10 до 20 (включительно), если да – вернуть true, в противном случае – false;
    5. Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль положительное число передали или отрицательное (Замечание: ноль считаем положительным числом.);
    6. Написать метод, которому в качестве параметра передается целое число, метод должен вернуть true, если число отрицательное;
    7. Написать метод, которому в качестве параметра передается строка, обозначающая имя, метод должен вывести в консоль сообщение «Привет, указанное_имя!»;
    8. *Написать метод, который определяет, является ли год високосным, и выводит сообщение в консоль. Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
    9. *Не набирая код в IDE, ответьте на следующий вопрос. Есть два метода:

    void myMethod(int a, String b) {}
    void myMethod(String b, int a) {}
    Это две разных сигнатуры одного метода или один и тот же метод?
    Разные сигнатруы, т.е. метод перегружен.
 */

public class Main {

    public static void main(String[] args) {

        System.out.println("Урок 1.");
        System.out.println("1. Метод иницализацющий и вывводящий все пройденные типы данных;");
        System.out.println("2. Метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат, где a, b, c, d – входные параметры этого метода;");
        System.out.println("3. Метод принимающего на вход два числа, и проверяющий, что их сумма лежит в пределах от 10 до 20 (включительно), если да – вернуть true, в противном случае – false;");
        System.out.println("4. Метод которому в качестве параметра передается целое число, метод должен напечатать в консоль положительное число передали или отрицательное (Замечание: ноль считаем положительным числом.); ");
        System.out.println("5. Метод которому в качестве параметра передается строка, обозначающая имя, метод должен вывести в консоль сообщение «Привет, указанное_имя!»;");
        System.out.println("6. Метод который определяет, является ли год високосным, и выводит сообщение в консоль. Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.;");
        System.out.print("Ввдеите номер операции: ");
        Scanner userInput = new Scanner(System.in);
        String userInputData = userInput.nextLine();
        double InputValueA, InputValueB, InputValueC, InputValueD;
        int InputValueInt;
        switch (userInputData) {
            case "1":
                printAllLearnDataTypes();
                break;
            case "2":

                System.out.print("Введите значение a:");
                userInputData = userInput.nextLine();
                InputValueA = parseUserInputInNumber(userInputData);
                System.out.print("Введите значение b: ");
                userInputData = userInput.nextLine();
                InputValueB = parseUserInputInNumber(userInputData);
                System.out.print("Введите значение c: ");
                userInputData = userInput.nextLine();
                InputValueC = parseUserInputInNumber(userInputData);
                System.out.print("Введите значение d: ");
                userInputData = userInput.nextLine();
                InputValueD = parseUserInputInNumber(userInputData);
                if (InputValueD == 0 ) {
                    System.out.print("Значение d не мсжет быть равно 0!");
                }
                System.out.println("Результат: " + calculateExpression(InputValueA,InputValueB,InputValueC,InputValueD));
                break;
            case "3":
                System.out.print("Введите значение a:");
                userInputData = userInput.nextLine();
                InputValueA = parseUserInputInNumber(userInputData);
                System.out.print("Введите значение b: ");
                userInputData = userInput.nextLine();
                InputValueD = parseUserInputInNumber(userInputData);
                System.out.println("Результат: " + checkSum(InputValueA,InputValueD));
                break;
            case "4":
                System.out.print("Введите значение a: ");
                userInputData = userInput.nextLine();
                InputValueInt = parseUserInputInNumber(userInputData,true);
                checkIsItPositive(InputValueInt);
                break;
            case "5":
                System.out.print("Введите вщае имя: ");
                userInputData = userInput.nextLine();
                sayHelloToUser(userInputData);
                break;
            case "6":
                System.out.print("Введите год: ");
                userInputData = userInput.nextLine();
                InputValueInt = parseUserInputInNumber(userInputData,true);
                checkYear(InputValueInt);
                break;
            default:
                System.err.println("Данная операция не предусмотренна!");
        }


    }

    private static void printAllLearnDataTypes() {
        //Primitive Data Types
        //Integer data types
        byte byteVariable = 0;
        short shortVariable = 32767;
        int integerVariable = 256;
        long longVariable = 35555L;
        // Char
        char charVariable = 'c';
        //Floating point data types
        float floatVariable = 356.0F;
        double doubleVariable = 356.0;
        //Boolean
        boolean booleanVariable = false;
        //Print all variables
        System.out.println(byteVariable);
        System.out.println(shortVariable);
        System.out.println(integerVariable);
        System.out.println(longVariable);
        System.out.println(charVariable);
        System.out.println(floatVariable);
        System.out.println(doubleVariable);
        System.out.println(booleanVariable);

    }

    private static double parseUserInputInNumber (String userInputValue) {

            double userInputDoubleValue = 0;
            try {
                userInputDoubleValue = Double.parseDouble(userInputValue);
            } catch (NumberFormatException catchedException) {
                System.err.println("Введенное значение не является числом!");
                System.err.println(catchedException);
                System.exit(1);
            }
            return userInputDoubleValue;
    }

    private static int parseUserInputInNumber (String userInputValue, boolean isInteger) {
        int userInputDoubleValue = 0;
        try {
            userInputDoubleValue = Integer.parseInt(userInputValue);
        } catch (NumberFormatException catchedException) {
            System.err.println("Введенное значение не является числом!");
            System.err.println(catchedException);
            System.exit(1);
        }
        return userInputDoubleValue;
    }

    private static double calculateExpression(double a, double b, double c, double d) {
        return a * (b + (c / d));
    }

    private static boolean checkSum(double a, double b) {
        return  ((a+b >=10) && (a+b)<=20)? true : false;
    }

    private static void checkIsItPositive(int userInputNumberInt) {
        if (userInputNumberInt >= 0) {
            System.out.println("Введенное число положительное.");
        } else {
            System.out.println("Введенное число отрицательное.");
        }

    }

    private static void sayHelloToUser (String userInputValue) {
        System.out.println("Приветсвую тебя, " + userInputValue + "!");
    }

    private static void checkYear (int userInputNumberInt) {
        if ( (userInputNumberInt%4 == 0 && !(userInputNumberInt%100 == 0)) || userInputNumberInt%400 == 0) {
            System.out.println("Год высокосный");
        } else {
            System.out.println("Год не высокосный");
        }
    }
}
