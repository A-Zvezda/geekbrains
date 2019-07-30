package Lesson_1.Marathon.actions;

import Lesson_1.Marathon.competitors.Team;
import Lesson_1.Marathon.obstacle.Obstacle;
import Lesson_1.Marathon.obstacle.Wall;
import Lesson_1.Marathon.competitors.Competitor;

public class Course {
    private Obstacle[] cross;

    public Course () {
        this.cross = new Obstacle[] {new Cross(80), new Wall(2), new Wall(1), new Cross(120)};
    }
    public Course (Obstacle...ObstacleArray) {
        this.cross = ObstacleArray.clone();
    }
    public Obstacle[] getCompetitors() {
        Obstacle[] returnArray = cross.clone();
        return returnArray;
    }

    public void doIt (Team team) {

        for (Competitor c : team.getCompetitors()) {
            for (Obstacle o : this.cross) {
                o.doIt(c);
                if (!c.isOnDistance()) break;
            }
        }

    }
    public void showResults (Team team) {
        for (Competitor c : team.getCompetitors()) {
            c.info();
        }
    }
}