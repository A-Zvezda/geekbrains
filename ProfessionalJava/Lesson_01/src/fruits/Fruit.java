package fruits;

public abstract class Fruit  {
    private String name;
    private Double weight;

    public Double getWeight() {
        return this.weight;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Fruit (String name, Double weight) {
        this.name = name;
        this.weight = weight;
    }
}
