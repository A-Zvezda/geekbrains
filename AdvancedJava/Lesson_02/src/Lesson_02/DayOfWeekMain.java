package Lesson_02;

public class Enum {

    public static void main(final String[] args) {
        System.out.println(getWorkingHours(DayOfWeek.WEDNESDAY));
    }

    public static String getWorkingHours (DayOfWeek dayOfWeek) {

        int sumWorkHour = 0;
        for (int i = dayOfWeek.ordinal(); i < 7; i++) {
            sumWorkHour += DayOfWeek.values()[i].getWorkHours();
        }
        return String.valueOf(sumWorkHour);
    }
}

enum DayOfWeek {
    MONDAY("Понедельник", 8), TUESDAY("Вторник", 8), WEDNESDAY("Среда", 8), THURSDAY("Четверг", 8), FRIDAY("Пятница", 8), SATURDAY("Суббота", 0), SUNDAY("Воскресенье", 0);

    private String rus;
    private int workHours;

    DayOfWeek(String rus, int workHours) {
        this.rus = rus;
        this.workHours = workHours;
    }

    public String getRus() {
        return rus;
    }

    public int getWorkHours() {
        return workHours;
    }
}

