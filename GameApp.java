import java.io.*;
import java.util.Scanner;

//This is the main file that will run the game itself.
public class GameApp {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    try (BufferedReader reader = new BufferedReader(new FileReader("gametitle.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading title file.");
        }

    System.out.println("Welcome to Generic Text-Based Game 1111!");
    System.out.println("Will you conquer the dungeon? Or fall like the rest of the previous adventurers?");

    System.out.println("Enter your name, stranger...");
    String userName = scanner.nextLine();
  }


}
