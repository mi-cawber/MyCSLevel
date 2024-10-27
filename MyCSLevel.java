import java.util.Scanner;

public class MyCSLevel {
    public static void main(String[] args) {
        //Scanner object
        Scanner sc = new Scanner(System.in);

        //basic variables
        int level = 50;
        int totalHours = 0, inputHours;
        double hoursToNextLevel, hoursNeeded;
        double multiplier = 5.0;
        double hoursThisLevel = 0;


        //the holy formula for leveling
        hoursToNextLevel = multiplier * Math.log(level + 1);

        //capture additional hours
        System.out.print("input hours: ");
        inputHours = sc.nextInt();
        //add to hours this level
        hoursThisLevel += inputHours;

        if (hoursThisLevel >= hoursToNextLevel) {
            level++;
            hoursToNextLevel = 0;
            System.out.printf("You have risen to %d", level);
        } else {
            System.out.printf("Hours needed %f", hoursToNextLevel - hoursThisLevel);
        }



    }
}

