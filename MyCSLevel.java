import java.util.Scanner;
import java.io.*;

public class MyCSLevel {
    public static void main(String[] args)
            throws Exception {
        //Scanner object
        Scanner sc = new Scanner(System.in);

        //new file writer for level
        PrintWriter levelWriter = new PrintWriter(new FileWriter("level.txt", true));
        //now one for totalHours
        PrintWriter totalHoursWriter = new PrintWriter(new FileWriter("totalHours.txt", true));
        //and one for hoursThisLevel
        PrintWriter hoursThisLevelWriter = new PrintWriter(new FileWriter("hoursThisLevel.txt", true));


        //basic variables
        double inputHours;
        double multiplier = 5.0;

        //tracked, should be loaded in each runtime
        int level = 1; //this should be changed
        double totalHours = 0; //should be changed
        double hoursToNextLevel = 0;
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
            totalHours = Double.parseDouble(totalHoursString);

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace(); // Handle any errors (file not found, invalid number)
        }

        //read hoursThisLevel from hoursThisLevel.txt
        try (BufferedReader hoursThisLevelReader = new BufferedReader(new FileReader("hoursThisLevel.txt"))) {

            //variables for looping
            String hoursThisLevelIterator;
            String hoursThisLevelString = null;

            //update string value until last line (when null is achieved)
            while ((hoursThisLevelIterator = hoursThisLevelReader.readLine()) != null) { //update until null
                hoursThisLevelString = hoursThisLevelIterator;
            }

            //convert the last line into int value for totalHours
            hoursThisLevel = Double.parseDouble(hoursThisLevelString);

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace(); // Handle any errors (file not found, invalid number)
        }



        //need for initial value
        hoursToNextLevel = multiplier * Math.log(level + 1);

        //capture additional hours
        System.out.print("input hours: ");
        inputHours = sc.nextDouble();

        //add to hours this level
        hoursThisLevel += inputHours;
        hoursThisLevelWriter.printf("%.2f\n", hoursThisLevel); //print to 2 dec
        hoursThisLevelWriter.flush(); //flush so can use later

        //add to total count
        totalHours += inputHours;
        //add to total hours file
        totalHoursWriter.printf("%.2f\n", totalHours); //print to 2 dec
        totalHoursWriter.close(); //done for runtime


        //level up structure
        while (hoursThisLevel >= hoursToNextLevel) {
            level++;
            levelWriter.println(level); //write into file
            levelWriter.close(); //close to take data out of buffer
            hoursThisLevel -=  hoursToNextLevel; //reset hoursThisLevel
            hoursThisLevelWriter.printf("%.2f\n", hoursThisLevel); //record in file
            hoursThisLevelWriter.close(); //close to take out of buffer
            System.out.printf("You have risen to %d\n", level); //print level up
            hoursToNextLevel = multiplier * Math.log(level + 1); //holy formula for next level
        }

        //report next level up
        System.out.printf("%.1f hours needed to rise to level %d", hoursToNextLevel - hoursThisLevel, level + 1);

    }
}

