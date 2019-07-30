/**
 * Класс кот.
 * Кот не умеет плавать.
 * @author Aleksandr Zvezda
 */


public class Cat extends Animal {
	private Bowl bowl;

    /**
     * Конструктор кота. Вызывает конструктор родителя.
     * Устанавливает максимальную дистанцию плаванья в -1 - кот не умеет плавать.
     */
    public Cat () {
        super();
        super.setMaxSwimDistans(-1);
    }

    /**
     * Метод получения дистанции плаванья. Перегрузка метода родителя.
     * @return boolean - всегда false - кот не умеет плавать.
     */
    @Override
    public int getSwim() {
        System.out.println("Кот не умеет плавать!");
        return super.getSwim();

    }

    /**
     * Метод установки дистанции плаванья. Перегрузка метода родителя. Кот не умеет плавать.
     * @param swim - дистанция плаванья
     */
    @Override
    public void setSwim(int swim) {
        System.out.println("Кот не умеет плавать!");
    }

    /**
     * Метод проверки длины дистанции плаванья.  Перегрузка метода родителя. Кот не умеет плавать.
     * @param swim - длина заплыва
     * @return boolean - всегда false - кот не умеет плавать.
     */
    @Override
    public Boolean swim(int swim) {
        System.out.println("Кот не умеет плавать!");
        return false;
    }

    /**
     * Метод кормления кота.
     * @param bowl Bowl - класс миска
     */
	public void eatFromBowl (Bowl bowl) {
        boolean catIsFeed = bowl.changeVolumeCurrent(super.getAppetiteValue()*-1);
        super.setHungry(!catIsFeed);
        if (catIsFeed) {
            System.out.println("Кот покормлен");
        } else {
		    System.out.println("Кот голодный");
        }
	}

    /**
     * Метод привязки кота к миске
     * @param bowl - миска для кота
     */
    public void setBowl(Bowl bowl) {
        this.bowl = bowl;
    }

    /**
     * Метод получения миски кота
     * @return Blow - миска кота
     */
    public Bowl getBowl() {
        return bowl;
    }
}
