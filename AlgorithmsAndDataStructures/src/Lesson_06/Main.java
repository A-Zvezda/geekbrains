package Lesson_06;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyTreeMap<Integer, Integer> mtm = new MyTreeMap<>();
        Random random = new Random();
        int count = 200000;
        int balanced = 0;
        int rand;
        float percent;
        LinkedList<MyTreeMap> myTreeMaps = new LinkedList<>();
        for(int i = 0; i < count;i++) {
            myTreeMaps.add(new MyTreeMap<>());
            mtm = myTreeMaps.get(i);
            while (mtm.maxHeight(mtm.root) <= 6) {
                rand = random.nextInt(100)-100;
                mtm.put(rand, rand);
            }
            if (mtm.isBalanced()) {
                balanced ++;
            }
        }
        System.out.println(balanced);
        percent = (float) balanced*(100f/(float)count);
        System.out.println(percent);
//        mtm.put(5,"five");
//        mtm.put(1,"one");
//        mtm.put(3,"tree");
//        mtm.put(4,"four");
//        mtm.put(2,"two");
//
//        System.out.println(mtm.get(2));
//        mtm.put(2,"two2222");
//
//        System.out.println(mtm.get(2));
//        System.out.println(mtm);
//
//        mtm.delete(3);
//        System.out.println(mtm);

    }
}
