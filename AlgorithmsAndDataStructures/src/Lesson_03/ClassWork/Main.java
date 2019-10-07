package Lesson_03.classWork;

public class Main {
    public static void main(String[] args) {
//        MyStack<Integer> stack = new MyStack<>();
//
//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//        stack.push(4);
//        stack.push(5);
//
//        for (int i = 0; i < 5; i++) {
//            System.out.println(stack.pop());
//        }

//        Expression e = new Expression("(4-6)+[34+{2*5}]");
//        System.out.println(e.checkBracket());

//        MyQueue<Integer> queue = new MyQueue<>(5);
//        for (int i = 0; i < 5; i++) {
//            queue.insert(i);
//        }
//        for (int i = 0; i < 5; i++) {
//            System.out.println(queue.remove());
//        }

        MyPriorityQueue<Integer> mpq = new MyPriorityQueue<>();
        mpq.insert(7);
        System.out.println(mpq);
        mpq.insert(3);
        System.out.println(mpq);
        mpq.insert(4);
        System.out.println(mpq);
        mpq.insert(2);
        System.out.println(mpq);
        System.out.println(mpq.remove());
        System.out.println(mpq);
    }
}
