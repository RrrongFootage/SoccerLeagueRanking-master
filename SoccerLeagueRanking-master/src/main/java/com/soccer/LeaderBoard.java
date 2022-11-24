package com.soccer;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class LeaderBoard {

    @Getter
    @Setter
    private List<Team> teams = new ArrayList<>();

    public void addTeam(Team team) {
        for (Team t : this.getTeams()) {
            if (t.getName().equalsIgnoreCase(team.getName())) {
                t.incrementPoints(team.getPoints());
                return;
            }
        }
        this.getTeams().add(team);
    }

    public void parseFixtureResultsFile(File resultsFile) {

        if (!resultsFile.exists()) {
            System.err.println("Error : file " + resultsFile.getName() + " cannot be found");
            System.exit(0);
        } else {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(Files.newInputStream(resultsFile.toPath()), StandardCharsets.UTF_8));
                String str;
                while ((str = in.readLine()) != null) {
                    FixtureEngine fixtureEngine = new FixtureEngine();
                    fixtureEngine.processResultPerLine(str);
                    fixtureEngine.recordPoints();
                    for (Team team : fixtureEngine.getTeamsFixtured()) {
                        this.addTeam(team);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public void sortTeams() {
        Collections.sort(teams, Team.TeamComparator);
    }

    public void displayLeaderBoard() {
        sortTeams();
        String pointsString;
        Map<Integer,Integer> pointsMap =new HashMap<>();
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            int position;
            Integer equalPoint=pointsMap.get(i-1);
            if (equalPoint == null || !(team.getPoints() == equalPoint)) {
                pointsMap.put(i, team.getPoints());
                position=i+1;
            }
           else{
                position=i;
            }
            pointsString = team.getPoints() == 1 ? " pt" : " pts";
            System.out.println(position + ". " + team.getName() + ", " + team.getPoints() + pointsString);
        }
    }
}
