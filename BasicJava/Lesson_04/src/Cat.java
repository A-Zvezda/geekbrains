
public class Cat extends Animal {


    //private int msxSwomDistans = -1;
    public Cat () {
        super();
        super.setMaxSwimDistans(-1);
    }
    @Override
    public int getSwim() {
        System.out.println("Кот не умеет плавать!");
        return super.getSwim();

    }

    @Override
    public void setSwim(int swim) {
        System.out.println("Кот не умеет плавать!");
    }

    @Override
    public Boolean swim(int swim) {
        System.out.println("Кот не умеет плавать!");
        return false;
    }
}
