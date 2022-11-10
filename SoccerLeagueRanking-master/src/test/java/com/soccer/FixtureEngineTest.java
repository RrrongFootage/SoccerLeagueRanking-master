package com.soccer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

public class FixtureEngineTest {

    File file;

    @Before
    public void setup() {
        ClassLoader classLoader = getClass().getClassLoader();
        file = new File(classLoader.getResource("input.txt").getFile());
    }

    @Test
    public void shouldParseFixtureResultFromFileCorrectly() throws Exception {
        Assert.assertTrue(file.exists());
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
        String str;
        while ((str = in.readLine()) != null) {
            FixtureEngine fixtureEngine = new FixtureEngine();
            fixtureEngine.processResultPerLine(str);
            Assert.assertEquals(2, fixtureEngine.teamsFixtured.size());
            Assert.assertEquals(2, fixtureEngine.getScores().size());
        }

    }

    @Test
    public void shouldRecordPoints() {
        String sampleLineText = "Tarantulas 3, Snakes 1";
        FixtureEngine fixtureEngine = new FixtureEngine();
        fixtureEngine.processResultPerLine(sampleLineText);
        fixtureEngine.recordPoints();
        Assert.assertEquals(2, fixtureEngine.getTeamsFixtured().size());
        Assert.assertEquals("Tarantulas", fixtureEngine.getTeamsFixtured().get(0).getName());
        Assert.assertEquals("Snakes", fixtureEngine.getTeamsFixtured().get(1).getName());
        Assert.assertEquals(3, fixtureEngine.getTeamsFixtured().get(0).getPoints());
        Assert.assertEquals(0, fixtureEngine.getTeamsFixtured().get(1).getPoints());
    }

    @Test
    public void shouldDisplayLeaderBoardInSortedOrder() throws Exception {
        Assert.assertTrue(file.exists());
        LeaderBoard leaderBoard = new LeaderBoard();
        leaderBoard.parseFixtureResultsFile(file);
        leaderBoard.sortTeams();
        List<Team> teamList = leaderBoard.getTeams();
        Assert.assertEquals(6, teamList.get(0).getPoints());
        Assert.assertEquals("Tarantulas", teamList.get(0).getName());
        Assert.assertEquals(5, teamList.get(1).getPoints());
        Assert.assertEquals("Lions", teamList.get(1).getName());
        Assert.assertEquals(1, teamList.get(2).getPoints());
        Assert.assertEquals("FC Awesome", teamList.get(2).getName());
        Assert.assertEquals(1, teamList.get(3).getPoints());
        Assert.assertEquals("Snakes", teamList.get(3).getName());
        Assert.assertEquals(0, teamList.get(4).getPoints());
        Assert.assertEquals("Grouches", teamList.get(4).getName());
    }
}
