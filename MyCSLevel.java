import java.util.Scanner;
import java.io.*;

public class MyCSLevel {
    public static void main(String[] args) throws FileNotFoundException {
        //Scanner object
        Scanner sc = new Scanner(System.in);

        //reader to read different stuff?
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        //new file writer for level
        PrintWriter levelFile = new PrintWriter("level.txt");


        //basic variables
        double inputHours;
        double multiplier = 5.0;

        //tracked, should be loaded in each runtime
        int level = 1;
        int totalHours = 0;
        double hoursToNextLevel;
        double hoursThisLevel = 0;


        //the holy formula for leveling
        hoursToNextLevel = multiplier * Math.log(level + 1);

        //capture additional hours
        System.out.print("input hours: ");
        inputHours = sc.nextDouble();
        //add to hours this level
        hoursThisLevel += inputHours;
        //add to total count
        totalHours += inputHours;

        //level up decision structure
        while (hoursThisLevel >= hoursToNextLevel) {
            level++;
            levelFile.println(level);
            levelFile.close();
            hoursThisLevel -=  hoursToNextLevel;
            System.out.printf("You have risen to %d\n", level);
            hoursToNextLevel = multiplier * Math.log(level + 1);
        }

        //report next milestone
        System.out.printf("%.1f hours needed to rise to level %d", hoursToNextLevel - hoursThisLevel, level + 1);


        //write level, hoursToNext, totalHours to files
    }
}

