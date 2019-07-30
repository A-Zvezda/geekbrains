/**
 * Класс животное.
 * run - дистанция бега.
 * swim - дистанция плаванья.
 * jump - высота прыжка.
 * maxRunDistance - ограничение максимальной дистанции бега.
 * maxSwimDistance - ограничение максимальной дистанции плаванья.
 * maxJumpDistance -  ограничение максимальной высоты прыжка.
 * appetiteValue - величина аппетита.
 * hungry - голоден ли животное.
 * @author Aleksandr Zvezda
 */

import java.util.Random;
public abstract class  Animal {
    private int run;
    private int swim;
    private int jump;
    private int maxRunDistance;
    private int maxSwimDistance;
    private int maxJumpDistance;
	private int appetiteValue;
	private boolean notHungry = true;

    /**
     * Конструктор класса.
     * Устанавливает ограничения случайным образом.
     */
    public Animal () {
        Random randomValue = new Random();
        this.maxRunDistance = randomValue.nextInt(700);
        this.maxSwimDistance = randomValue.nextInt(700);
        this.maxJumpDistance = randomValue.nextInt(700);
		this.appetiteValue =  randomValue.nextInt(20)+1;
    }

    /**
     * Метод устанавливает максимальной дистанции плаванья.
     * @param maxSwimDistans - максимальный дистанция плаванья.
     */
    public void setMaxSwimDistans(int maxSwimDistans) {
        this.maxSwimDistance = maxSwimDistans;
    }

    /**
     * Метод возвращает дистанцию на которую может плавать животное.
     * @return int
     */
    public int getSwim() {
        return swim;
    }

    /**
     * Метод возвращает дистанцию на которую может бегать животное.
     * @return int
     */
    public int getRun() {
        return run;
    }

    /**
     * Метод возвращает высоту на которую может прыгать животное.
     * @return int
     */
    public int getJump() {
        return jump;
    }

    /**
     * Метод проверяет может ли данное животное прыгать на данную высоту и устанавливает высоту если оно прошло проверку.
     * @param jump - высота прыжка
     */
    public void setJump(int jump) {
        if (this.maxJumpDistance > jump ) {
            this.jump = jump;
        } else {
            outError(maxJumpDistance);
        }
    }

    /**
     * Метод проверяет может ли данное животное бегать на данную дистанцию и устанавливает длину если оно прошло проверку.
     * @param run - дистанция бега
     */
    public void setRun(int run) {
        if (this.maxRunDistance > jump ) {
            this.run = jump;
        } else {
            outError(maxRunDistance);
        }
    }
    /**
     * Метод проверяет может ли данное животное плавать на данную дистанцию и устанавливает длину если оно прошло проверку.
     * @param swim - дистанция плаванья
     */
    public void setSwim(int swim) {
        if (this.maxSwimDistance> swim ) {
            this.swim = swim;
        } else {
            outError(maxSwimDistance);
        }
    }

    /**
     * Метод проверяет может ли животное прыгнуть на данную высоту.
     * @param jump - высота прыжка.
     * @return boolean - true - может, false - не может.
     */
    public Boolean jump(int jump) {
        return jump <= this.jump;
    }

    /**
     * Метод проверяет может ли животное проплыть дистанцию.
     * @param swim - длина заплыва.
     * @return boolean - true - может, false - не может.
     */
    public Boolean swim(int swim) {
        return swim <= this.swim;
    }

    /**
     * Метод проверяет может ли животное прибежать данную дистанцию.
     * @param run - дистанция бега.
     * @return boolean - true - может, false - не может.
     */
    public Boolean run(int run) {
        return run <= this.run;
    }

    /**
     * Метод выводит сообщение о ошибке если введённое значение больше чем животное может выполнить.
     * @param maxValue - текущее максимальное значение.
     */
    private void outError (int maxValue) {
        System.err.println("Введённое значение больше максимальность допустимого. Максимальное значение: " + maxValue );
    }

    /**
     * Метод печатает текущую информацию о животном.
     */
    public void printOutInfo () {
        System.out.println("Ограничения! Бег:" + this.maxRunDistance +"; Плаванье:" + this.maxSwimDistance +"; Прыжок:" + this.maxJumpDistance + "; Текущие значения. Бег: " +this.run + "; Плаванье:" + this.swim+ "; Прыжок:"  + this.jump  + "; Аппетит:"  + this.appetiteValue +  "; Животное сыотое:"  +this.notHungry);
    }

    /**
     * Метод получения значения аппетита.
     * @return int - аппетит животного.
     */
    public int getAppetiteValue() {
        return appetiteValue;
    }

    /**
     * Метод получения текущего состояния голода.
     * @return boolean - false - животное сыто. true - животное голодное.
     */
    public boolean getHungry() {
        return notHungry;
    }

    /**
     * Метод меняет состояние голода.
     * @param hungry - false - животное сыто, true - животное голодное.
     */
    public void setHungry(boolean hungry) {
        this.notHungry = hungry;
    }
}

