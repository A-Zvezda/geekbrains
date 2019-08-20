package containers;

import fruits.Fruit;

import java.util.ArrayList;

public class Box <T extends Fruit> extends Container {
    public Box(ArrayList<T> content, String boxID) {
        super(content, boxID);
        double width = 0;
        for (T fruit : content) {
            width += fruit.getWeight();
        }
        setWidth(width);
    }
    public void addItem(Fruit item ) {
        super.addItem(item);
        super.addWeight(item.getWeight());
    }

    public void removeItem (Fruit item) {
        super.removeItem(item);
        super.removeWeight(item.getWeight());
    }
    private void setWidth (Double width) {
        super.setWeight(width);
    }
}
