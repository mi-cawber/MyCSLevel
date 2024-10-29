import java.util.Scanner;
import java.io.*;

public class MyCSLevel {
    public static void main(String[] args)
            throws IOException {
        //Scanner object
        Scanner sc = new Scanner(System.in);

        //new file writer for level
        PrintWriter levelWriter = new PrintWriter(new FileWriter("level.txt", true));
        //now one for totalHours
        PrintWriter totalHoursWriter = new PrintWriter(new FileWriter("totalHours.txt", true));


        //basic variables
        double inputHours;
        double multiplier = 5.0;

        //tracked, should be loaded in each runtime
        int level = 1; //this should be changed
        int totalHours = 0; //should be changed
        double hoursToNextLevel;
        double hoursThisLevel = 0;


        //read in level from level.txt
        try (BufferedReader levelReader = new BufferedReader(new FileReader("level.txt"))) {

            //variables for looping
            String levelIterator;
            String levelString = null;

            //iterate until last line
            while ((levelIterator = levelReader.readLine()) != null) {
                //capture string of last line
                levelString = levelIterator;
            }

            //convert into int for level assignment
            level = Integer.parseInt(levelString);

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace(); //handle any errors (file not found, invalid number)
        }

        //read totalHours from totalHours.txt
        try (BufferedReader totalHoursReader = new BufferedReader(new FileReader("totalHours.txt"))) {

            //variables for looping
            String totalHoursIterator;
            String totalHoursString = null;

            //update string value until last line (when null is achieved)
            while ((totalHoursIterator = totalHoursReader.readLine()) != null) { //update until null
                totalHoursString = totalHoursIterator;
            }

            //convert the last line into int value for totalHours
            totalHours = Integer.parseInt(totalHoursString);

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace(); // Handle any errors (file not found, invalid number)
        }

        //the holy formula for leveling
        hoursToNextLevel = multiplier * Math.log(level + 1);

        //capture additional hours
        System.out.print("input hours: ");
        inputHours = sc.nextDouble();
        //add to hours this level
        hoursThisLevel += inputHours;
        //add to total count
        totalHours += inputHours;
        //add to total hours file
        totalHoursWriter.println(totalHours);
        totalHoursWriter.close();


        //level up decision structure
        while (hoursThisLevel >= hoursToNextLevel) {
            level++;
            levelWriter.println(level); //write into file
            levelWriter.close(); //close to take data out of buffer
            hoursThisLevel -=  hoursToNextLevel;
            System.out.printf("You have risen to %d\n", level);
            hoursToNextLevel = multiplier * Math.log(level + 1);
        }

        //report next milestone
        System.out.printf("%.1f hours needed to rise to level %d", hoursToNextLevel - hoursThisLevel, level + 1);


        //write level, hoursToNext, totalHours to files
    }
}

