import java.util.Random;
import java.util.Scanner;

public class Codsoft_taskno1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 10;
        int score = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        boolean playAgain = true;
        while (playAgain) {
            int targetNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            System.out.println("\nI'm thinking of a number between " + minRange + " and " + maxRange);
            System.out.println("You have " + maxAttempts + " attempts to guess the number.");

            int attempts = 0;
            boolean guessedCorrect = false;

            while (attempts < maxAttempts) {
                System.out.print("\nEnter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the number " + targetNumber + " correctly!");
                    guessedCorrect = true;
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Too low! Try guessing higher.");
                } else {
                    System.out.println("Too high! Try guessing lower.");
                }
            }

            if (guessedCorrect) {
                score += maxAttempts - attempts + 1;
            }

            System.out.println("\nYour score: " + score);
            System.out.print("Play again? (yes/no): ");
            String playAgainResponse = scanner.next().toLowerCase();
            playAgain = playAgainResponse.equals("yes") || playAgainResponse.equals("y");
        }

        System.out.println("Thanks for playing the Number Guessing Game!");
        scanner.close();
    }
}