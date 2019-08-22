package containers;

import java.util.ArrayList;

public abstract class Container <T extends Object>{
    private ArrayList <T> content;
    private String boxID;
    private Double weight = 0.0;

    public Container (ArrayList <T> content, String boxID) {
        this.content = new ArrayList<T>(content);
        this.boxID = boxID;
    }
    public Double getWeight() {
        return weight;
    }

    public String getBoxID() {
        return boxID;
    }

    public ArrayList<T> getContent() {
        return new ArrayList<T>(this.content);
    }

    public void addWeight(Double weight) {
        this.weight += weight;
    }
    public void removeWeight(Double weight) {
        this.weight -= weight;
    }

    public void addItem(T item ) {
        content.add(item);
   }
    public void setContent (ArrayList <T> content) {
        this.content = content;
    }
    public void removeItem (T item) {
        content.remove(item);
    }

    public void setBoxID(String boxID) {
        this.boxID = boxID;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }


}
