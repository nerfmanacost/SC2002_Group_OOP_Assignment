
//The playthrough itself.
public class GameSession {
  //we will start from turn 0, then when its our turn we increment, so we alr increment by 1 from the start.
  private int currentTurn = 0;
  //if game over, we print game over screen. 
  //see how we want to implement user select after game over.
  private boolean isGameOver = false;

  public void startGame(){
    System.out.println("New Game Start!");

    while(!isGameOver){
      //main game logic
      
      //I need to implement takeTurn() func which allows BOTH enemy and user to perform an in game turn.

      // i will pass the currentTurn into MainPlayer, tehn call the setter function that sets teh players current turn in the player object.
    }

    //Game Over
    System.out.println("Game Over!!");
  }
}
