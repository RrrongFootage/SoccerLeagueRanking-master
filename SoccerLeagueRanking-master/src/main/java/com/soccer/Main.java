package com.soccer;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;

public class Main {
    @Option(name = "-f", usage = "Upload a txt file with fixture results separated by commas ")
    private File file;

    public static void main(String[] args) {

        try {
            new Main().doMain(args);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void doMain(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            // parse the arguments.
            parser.parseArgument(args);

            if (file != null) {
                LeaderBoard leaderBoard = new LeaderBoard();
                leaderBoard.parseFixtureResultsFile(file);
                leaderBoard.displayLeaderBoard();
            } else {
                parser.printUsage(System.err);
            }


        } catch (CmdLineException e) {
            parser.printUsage(System.err);
        }
    }
}
