import Characters.MainPlayer;

public class BattleUI {
  //this should be the UI stuff to display.

  //displays user health and important stats  
  public void displayPlayerBattleStats(MainPlayer player){
    //HAVE to display the users current item EVERY TURN, cuz user cannot backtrack option.

    //gui health bar
    int barLength = 20;
    int filled = (int)((double) player.getHealth() / player.getBaseHealth() * barLength);
    String bar = "[" + "█".repeat(filled) + "-".repeat(barLength - filled) + "]";
    System.out.println(player.getName());
    System.out.println("HP: " + bar + " " + player.getHealth() + "/" + player.getBaseHealth());

    System.out.println("DEF: " + player.getDefense());

    System.out.println("SPD: " + player.getSpeed());

  }

  //Displays current turn number
  public void displayCurrentTurnNumber(int currentTurnNumber){
    System.out.println("=== Turn " + currentTurnNumber + "==="); 
  }

  //Displays users available actions per turn.
  public void displayUserActions(MainPlayer player){
    System.out.println("Enter your choice:");
    System.out.println("1. Basic Attack for: " + player.getAttack() + "dmg");
    System.out.println("2. Defend");
    System.out.print("3. Use Skill: ");
    player.displayUniqueSkill();
    System.out.println("4. Use Item");
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
