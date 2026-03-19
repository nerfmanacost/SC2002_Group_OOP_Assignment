import java.io.*;
import java.util.Scanner;

//This is the main file that will run the game itself.
public class GameApp {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    //print game title
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

    //from this point in GameApp, this is counted as the loading screen. A GameSession will only be instantiated AFTER loading screen.
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
        //print out attributes
        player.showStats();
        break;
      }else if (userInput == 2){
        System.out.println("Selected Wizard!");
        player = new PlayerWizard(userName);
        System.out.println("Your stats/attributes are:");
        //print out attributes.
        player.showStats();
        break;
      }
    }

    //option to pick items, so we need to show the list of items available.

    System.out.println("Select items to aid your adventure, " + userName);
    //BASED ON PROJECT SPECS, we HAVE to let user pick 2 items, not one or other number, but 2.
    //we also have to print the number for each item.
    //Item[] allItems =

    //stores the user's choice for whichever items they want
    int[] userItemSelects = new int[2];
    int count = 0;

    System.out.println("Type in the number and press enter for each item, you can select 2 items before your run");
    while (count < 2){
      System.out.println("Pick Item " + (count + 1) + ":");
      int userSelect = scanner.nextInt(); 
      //duplicate items ARE allowed.
      userItemSelects[count] = userSelect;
      //Need to implement printing of item name
      System.out.println("You selected - ");
      count++;
    }

    //user now selects Difficulty, Easy Medium or Hard, enemy needs to show their attributes too.

    char userSelectDifficulty; 

    while (true){
      System.out.println("Choose your difficulty");

      System.out.println("Easy:");
      //print out enemies attributes and waves info
      
      System.out.println("Medium:");
      //print out enemies attributes and waves info

      System.out.println("Hard:");
      //print out enemies attributes and waves info


      System.out.println("Enter your choice: (E, M, H)");
      char userChoice = scanner.next().charAt(0);

      if (userChoice != 'E' || userChoice != 'M' || userChoice != 'H'){
        System.out.println("Please enter a valid choice: E, M or H and press enter.");
      }else {
        userSelectDifficulty = userChoice;
        break;
      }
    }

    //with these userSelected fields, we will construct a new GameSession.

  }
}
