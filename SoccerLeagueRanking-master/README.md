# SoccerLeagueRanking
A command-line application that will calculate the ranking table for a soccer league

**Software requirements**

 - Java JDK 1.8 or higher
 - [Maven 3](https://maven.apache.org/install.html)    

**Installation Instructions**
1. Clone the *SoccerLeagueRanking* or download the zip file from github
2. Navigate to the project root folder *SoccerLeagueRanking* and open terminal/command line from there
3. Build the project by executing the following command : `

> mvn clean compile assembly:single`

4. On build success, you should see a new folder `target` inside the project root.
5. Inside the target folder is a jar file `soccer-league-1.0-SNAPSHOT-jar-with-dependencies.jar`
6. The *SoccerLeagueRanking* app expects a comma separated *txt* file, have that ready before running the app.
7. Inorder to startup the app,edit the run configuration CLI arguments to:

>   -f  `{{pathToInputFile}}`
8. Run Main
9. App should display the list of teams ranked according to most number of points earned


