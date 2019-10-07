package Lesson_04.homeWork;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        MyLinkedQuery<String> myLinkedQuery = new MyLinkedQuery<>();

        myLinkedQuery.push("1");
        myLinkedQuery.push("2");
        myLinkedQuery.push("3");

       System.out.println(myLinkedQuery);
       myLinkedQuery.pop();
       myLinkedQuery.pop();
       System.out.println(myLinkedQuery);

        MyLinkedList<String> mll = new MyLinkedList<>();

        mll.insertFirst("Katia");
        mll.insertFirst("Maria");
        mll.insertFirst("Lyba");

        System.out.println(mll);

        mll.insertLast("Petia");
        System.out.println(mll);
        Iterator<String> iterator = mll.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next()+" ");
            iterator.remove();
        }
        System.out.println();
        System.out.println("After remove is Empty: " + mll.isEmpty());


    }
}
