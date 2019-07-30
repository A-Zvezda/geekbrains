package Lesson_1.Marathon.obstacle;

import Lesson_1.Marathon.competitors.Competitor;
import Lesson_1.Marathon.obstacle.Obstacle;

public class Water extends Obstacle {
    int length;

    public Water(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.swim(length);
    }
}