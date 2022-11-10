package com.soccer;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;

public class Team {

    @Getter
    @Setter
    private int points;

    @Getter
    @Setter
    private String name;

    public Team(String name) {
        this.name = name;
    }

    public void incrementPoints(int points) {
        this.points += points;
    }


    public static Comparator<Team> TeamComparator
            = new Comparator<Team>() {

        public int compare(Team team1, Team team2) {

            int team1Points = team1.getPoints();
            int team2Points = team2.getPoints();

            int difference = team2Points - team1Points;

            if (!(difference == 0)) {
                return difference;
            }
            return team1.getName().compareTo(team2.getName());
        }

    };
}
