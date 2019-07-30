package Lesson_1.Marathon.competitors;

import Lesson_1.Marathon.competitors.Cat;
import Lesson_1.Marathon.competitors.Competitor;
import Lesson_1.Marathon.competitors.Dog;
import Lesson_1.Marathon.competitors.Human;

public class Team {
    private Competitor[] competitors;
    public String teamName;
    public Team () {
        this.competitors = new Competitor[] {new Human("Боб"), new Cat("Барсик"), new Dog("Бобик")};
        this.teamName = "Лапухи";
    }

    public Team ( String teamName, Competitor...competitorArray) {
        this.competitors = competitorArray;
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public Competitor[] getCompetitors() {
        Competitor[] returnArray = this.competitors.clone();
        return returnArray;
    }
}
