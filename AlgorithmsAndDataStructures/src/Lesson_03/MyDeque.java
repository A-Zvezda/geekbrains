package Lesson_03;

import java.util.Arrays;
import java.util.EmptyStackException;

public class MyDeque<Item> {
    private Item[] list;
    private int size = 0;
    private final int DEFAULT_CAPACITY = 10;
    private int begin = 0;
    private int end = 0;
    //0 1 2 3 4
    //b
    //  e
    //7

    public MyDeque(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("bad size " + capacity);
        }
        list = (Item[]) new Object[capacity];
    }

    public MyDeque() {
        list = (Item[]) new Object[DEFAULT_CAPACITY];
    }

    public void insertRight(Item item) {
        if (isFull()) {
            throw new StackOverflowError();
        }
        size++;
        list[end] = item;
        end = nextIndex(end);
    }

    public Item removeRight() {
        Item value = peekRight();
        size--;
        list[begin] = null;
        begin = nextIndex(begin);
        return value;
    }
    public void insertLeft (Item item) {
        if (isFull()) {
            throw new StackOverflowError();
        }
        size++;
        begin = lastIndex(begin);
        list[begin] = item;
    }

    public Item  removeLeft()  {
        Item value = peekLeft();
        size--;
        list[begin] = null;
        begin = nextIndex(begin);
        return value;
    }
    public Item peekLeft()  {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        return list[lastIndex(end)];
    }
    public Item peekRight() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list[begin];
    }

    private int nextIndex(int index) {
        return (index + 1) % list.length;
    }
    private int lastIndex(int index) {
        return (list.length + index - 1) % list.length;
    }
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == list.length;
    }
    public void info() {
        System.out.println(Arrays.toString(list));
    }

}
