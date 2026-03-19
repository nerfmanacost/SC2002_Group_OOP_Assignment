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

    String userName;

    while (true){
      System.out.println("Enter your name, stranger...");

      userName = scanner.nextLine();

      if (userName.trim().isEmpty() ){
        System.out.println("Name cannot be empty.");
      }else if (userName.length() > 20){
        System.err.println("Name too long! 20 characers max.");
      }else {
        System.out.println("Welcome to the game, " + userName);
        break;
      }
    }

    //pass it to Warrior/Wizard object, not Main Player
    MainPlayer player = null;

    while(true){
      System.out.println("Select your class...");
      System.out.println("Warrior: 'Big Sword go Swoosh' has powerful attacks, higher health but lacks AOE | Press 1 and Enter.");
      System.out.println("Wizard: 'Explosionnnnn!!!' has slower Speed, lesser Health but has AOE and cool spells | Press 2 and Enter.");
      int userInput = scanner.nextInt();
      
      if (userInput != 1 && userInput != 2){
        System.out.println("Invalid choice, Enter 1 or 2");
      }else if (userInput == 1){
        System.out.println("Selected Warrior!");
        player = new PlayerWarrior(userName); 
        System.out.println("Your stats/attributes are:");
        player.showStats();
        //print out attributes
        break;
      }else if (userInput == 2){
        System.out.println("Selected Wizard!");
        player = new PlayerWizard(userName);
        System.out.println("Your stats/attributes are:");
        player.showStats();
        //print out attributes.
        break;
      }
    }
    //need to list out Players Attributes, along with spells.

    //option to pick items, so we need to show the list of items available.

    //user now selects Difficulty, Easy Medium or Hard, enemy needs to show their attributes too.

    while (true){
      System.out.println("Choose your difficulty");

      System.out.println("Easy:");
      //print out enemies attributes and waves info
      
      System.out.println("Medium:");
      //print out enemies attributes and waves info

      System.out.println("Hard:");
      //print out enemies attributes and waves info


      System.out.println("Enter your choice: (E, M, H)");
      String userChoice = scanner.nextLine();

      if (userChoice != "E"){
        System.out.println("Please enter a valid choice: E, M or H and press enter.");
      }else {
        break;
      }
    }
  }
}