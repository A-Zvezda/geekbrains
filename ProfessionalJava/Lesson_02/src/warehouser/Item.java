package warehouser;

public class Item {
    private int cost = 0;
    private String title = null;

    public String getTitle() {
        return title;
    }

    public int getCost() {
        return cost;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return  "Наименование " + title + " Цена " + cost;
    }
}
