import Characters.MainPlayer;

public class BattleUI {
  //this should be the UI stuff to display.

  //only display 
  public void displayPlayerBattleStats(MainPlayer player, int currentTurnNumber){
    //HAVE to display the users current item EVERY TURN, cuz user cannot backtrack option.
    
    System.out.println("=== " + currentTurnNumber + "==="); 

    //gui health bar
    int barLength = 20;
    int filled = (int)((double) player.getHealth() / player.maxHP * barLength);
    String bar = "[" + "█".repeat(filled) + "-".repeat(barLength - filled) + "]";
    System.out.println("HP: " + bar + " " + currentHP + "/" + maxHP);

    System.out.println("DEF: " + player.getDefense());

    System.out.println("SPD: " + player.getSpeed());

  }

  //Shift the game over, game Victory messages function here

  public void displayGameOverScreen(MainPlayer player){
    System.out.println(player.getName() + " has been slain...");
    System.out.println("=== YOU DIED ===");
    System.out.println("=== GAME OVER ===");
  }

  public void displayVictoryScreen(MainPlayer player){
    System.out.println("=== YOU WIN! ===");
    System.out.println(player.getName() + " is victorous!");
  }
}
