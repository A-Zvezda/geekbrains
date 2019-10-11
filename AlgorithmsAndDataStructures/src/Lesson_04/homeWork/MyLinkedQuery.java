package Lesson_04.homeWork;



public class MyLinkedQuery<T> {
    private MyLinkedList<T> stack = new MyLinkedList<>();

    public void push(T value){
        stack.insertFirst(value);
    }
    public T pop(){
        return stack.removeLast();
    }
    public T peek(){
        return stack.getLast();
    }

    public int size(){
        return stack.size();
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }
    @Override
    public String toString() {
        return  stack.toString();
    }
}