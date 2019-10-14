package Lesson_05.homework_my;

public class Main {
    public static void main (String[] args) {
        System.out.println(myPower(4,10));
        System.out.println(myPowerQ(4,10));
    }

    public static int myPowerQ(int x, int n) {
        if (n == 0) {
            return 1;
        } else if ((n & 1) == 1) {
            return x * myPowerQ(x, n / 2) * myPowerQ(x, n / 2);
        }
        return myPowerQ(x, n / 2) * myPowerQ(x, n / 2);
    }

    public static int myPower(int x, int n) {
        if (n == 0) {
            return 1;
        }
            return x * myPower(x, n - 1);
        }
}

