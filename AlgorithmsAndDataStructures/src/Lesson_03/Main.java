package Lesson_03;
import Lesson_03.classWork.*;

import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) {

//        String stringToReverse = "Мама мыла раму";
//        reverseStringStringBuilder(stringToReverse);
//        reverseStringStack(stringToReverse);
        myDeq();
    }

    public static void  myDeq () {
        MyDeque dq = new MyDeque(5);

        System.out.println("Вставка елемента спава  : 5 ");
        dq.insertRight(5);
        System.out.println("Вставка елемента слева : 10 ");
        dq.insertLeft(10);
        System.out.println("Вставка елемента слева : 11 ");
        dq.insertLeft(11);
        dq.info();
        System.out.println("Получение левого : "+ dq.peekLeft());
        dq.removeLeft();
        dq.info();
        System.out.println("После удаления левого элемента : " + dq.peekLeft());
        System.out.println("Вставка элемента вправо");
        dq.insertRight(15);
        dq.insertRight(16);
        dq.info();
        System.out.println("Получение левого элемента: " +dq.peekLeft());
        dq.removeRight();
        dq.info();
        System.out.println("После удаления правого элемента  peekLeft: " + dq.peekLeft());
        System.out.println("После удаления правого элемента peekRight: " + dq.peekRight());
        dq.info();

    }
   private static void reverseStringStringBuilder (String inputString) {
       StringBuilder stringBuilder = new StringBuilder(inputString);
       System.out.println(stringBuilder.reverse().toString());
   }
    private static void reverseStringStack(String inputString) {
        String reversedString;
        reversedString = "";
        MyStack<Character> myStack = new MyStack(inputString.length());
        for (int i = 0 ; i < inputString.length() ; i ++) {
            myStack.push(inputString.charAt(i));
        }
        while (!myStack.isEmpty()) {
            reversedString += myStack.pop();
        }
        System.out.println(reversedString);
    }
}
