
//The playthrough itself.
public class GameSession {

  //use of static as per Josh's philoshpy.
  private static char difficulty;
  
  //need to bring the Item[] array that contains the list (2 items) that user has chosen here.

  //private static 
  
  //we have to get the MainClass here....


  //Constructor
  public GameSession(char difficulty ){
    this.difficulty = difficulty;

  }

  //we will start from turn 0, then when its our turn we increment, so we alr increment by 1 from the start.
  private int currentTurn = 0;
  //if game over, we print game over screen. 
  //see how we want to implement user select after game over.
  private boolean isGameOver = false;

  public void startGame(){
    System.out.println("New Game Start!");

    boolean gameWon = false;

    while(!isGameOver){
      //main game logic
      
      //I need to implement takeTurn() func which allows BOTH enemy and user to perform an in game turn.

      // i will pass the currentTurn into MainPlayer, tehn call the setter function that sets teh players current turn in the player object.


      //set gameWon.
      gameWon = true;
    }

    //Game Over
    if (gameWon == true){
      System.out.println("You have conquered the dungeon, ");//add playerName
      System.out.println("You Win!!");
    }else {
      System.out.println("YOU DIED");
      System.out.println("Game Over!!");
      //see if need to let user to retry, to redirect to loading screen
    }
  }
}
