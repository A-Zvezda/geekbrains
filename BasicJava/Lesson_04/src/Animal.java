import java.util.Random;
public abstract class  Animal {
    private int run;
    private int swim;
    private int jump;
    private int maxRunDistance;
    private int maxSwimDistance;
    private int maxJumpDistance;

    public Animal () {
        Random randomValue = new Random();
        maxRunDistance = randomValue.nextInt(700);
        maxSwimDistance = randomValue.nextInt(700);
        maxJumpDistance = randomValue.nextInt(700);
    }

    public void setMaxSwimDistans(int maxSwimDistans) {
        this.maxSwimDistance = maxSwimDistans;
    }

    public int getSwim() {
        return swim;
    }

    public int getRun() {
        return run;
    }
    public int getJump() {
        return jump;
    }

    public void setJump(int jump) {
        if (this.maxJumpDistance > jump ) {
            this.jump = jump;
        } else {
            outError(maxJumpDistance);
        }
    }

    public void setRun(int run) {
        if (this.maxRunDistance > jump ) {
            this.run = jump;
        } else {
            outError(maxRunDistance);
        }
    }

    public void setSwim(int swim) {
        if (this.maxSwimDistance> swim ) {
            this.swim = swim;
        } else {
            outError(maxSwimDistance);
        }
    }

    public Boolean jump(int jump) {
        return jump <= this.jump;
    }

    public Boolean swim(int swim) {
        return swim <= this.swim;
    }

    public Boolean run(int run) {
        return run <= this.run;
    }

    private void outError (int maxValue) {
        System.err.println("Введёное значение больше максмальо допустмого. Максмальное значение: " + maxValue );
    }
    public void printOutInfo () {
        System.out.println("Огранчия! Бег:" + this.maxRunDistance +"; Плавнье:" + this.maxSwimDistance +"; Прыжок:" + this.maxJumpDistance + "; Текущие значинея. Бег: " +this.run + "; Плаванье:" + this.swim+ "; Прыжок:"  + this.jump);
    }

}

