package Lesson_06;

public class Main {
    public static void main(String[] args) {
        MyTreeMap<Integer, String> mtm = new MyTreeMap<>();

        mtm.put(5,"five");
        mtm.put(1,"one");
        mtm.put(3,"tree");
        mtm.put(4,"four");
        mtm.put(2,"two");

        System.out.println(mtm.get(2));
        mtm.put(2,"two2222");

        System.out.println(mtm.get(2));
        System.out.println(mtm);

        mtm.delete(3);
        System.out.println(mtm);

    }
}
