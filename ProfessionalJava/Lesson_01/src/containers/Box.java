package containers;

import fruits.Fruit;

import java.util.ArrayList;
import java.util.Iterator;

public class Box <T extends Fruit> extends Container<T> {
    public Box(ArrayList<T> content, String boxID) {
        super(content, boxID);
        double width = 0;
        for (T fruit : content) {
            width += fruit.getWeight();
        }
        setWidth(width);
    }
    public void addItem(T item) {
        super.addItem(item);
        super.addWeight(item.getWeight());
    }

    public void removeItem (T item) {
        super.removeItem(item);
        super.removeWeight(item.getWeight());
    }
    private void setWidth (Double width) {
        super.setWeight(width);
    }
    public boolean compare (Box box, Box box1) {
        double i = box.getWeight();
        double j = box1.getWeight();
        return i == j;
    }
    public void moveContent(Box<T> box) {

        ArrayList<T> content = box.getContent();
        for (T o:content) {
            this.addItem(o);
            box.removeItem(o);
        }

    }

}
