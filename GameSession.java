
//The playthrough itself.
public class GameSession {
  //we will start from turn 1.
  private int currentTurn = 1;
  //if game over, we print game over screen. 
  //see how we want to implement user select after game over.
  private boolean isGameOver = false;

  public void startGame(){
    System.out.println("New Game Start!");

    while(!isGameOver){
      //main game logic
      
      //I need to implement takeTurn() func which allows BOTH enemy and user to perform an in game turn.
    }

    //Game Over
    System.out.println("Game Over!!");
  }
}
