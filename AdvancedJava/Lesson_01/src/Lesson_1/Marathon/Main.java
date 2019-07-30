package Lesson_1.Marathon;

import Lesson_1.Marathon.actions.Course;
import Lesson_1.Marathon.competitors.Team;

public class Main {
    public static void main(String[] args) {
        Team team  = new Team();
        Course c = new Course();
        c.doIt(team);
        c.showResults(team);
    }
}