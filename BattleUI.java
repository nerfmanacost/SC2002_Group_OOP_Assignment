import java.util.ArrayList;
import java.util.List;

import Characters.MainPlayer;
import Characters.PlayerWizard;

public class BattleUI {
  //this should be the UI stuff to display.

  //displays user health and important stats  
  public void displayPlayerBattleStats(MainPlayer player){
    List<String> statuses = new ArrayList<>();
    if (player.smokeStatus()) statuses.add("Inside Smoke Bomb");
    String statusText = statuses.isEmpty() ? "" : " [" + String.join(", ", statuses) + "]";
    //gui health bar
    int barLength = 20;
    int filled = (int)((double) player.getHealth() / player.getBaseHealth() * barLength);
    String bar = "[" + "=".repeat(filled) + "-".repeat(barLength - filled) + "]";
    System.out.println(player.getName() + statusText);
    System.out.println("HP: " + bar + " " + player.getHealth() + "/" + player.getBaseHealth());

    System.out.println("DEF: " + player.effectiveDefense());

    System.out.println("SPD: " + player.getSpeed());

  }
  //needed for user to see potion health increment.
  public void displayPlayerHealth(MainPlayer player){
    int barLength = 20;
    int filled = (int)((double) player.getHealth() / player.getBaseHealth() * barLength);
    String bar = "[" + "=".repeat(filled) + "-".repeat(barLength - filled) + "]";
    System.out.println("HP: " + bar + " " + player.getHealth() + "/" + player.getBaseHealth());
  }

  //Displays current turn number
  public void displayCurrentTurnNumber(int currentTurnNumber){
    System.out.println("====== Turn " + currentTurnNumber + " ======"); 
  }

  public void printNextWaveHeader(){
    System.out.println("====== NEXT WAVE ======");
  }

  //Displays users available actions per turn.
  public void displayUserActions(MainPlayer player){
    System.out.println("Enter your choice:");
    if(player instanceof PlayerWizard wizard){
      System.out.println("1. Basic Attack for: " + wizard.effectiveAttack() + "dmg");
    } else {
      System.out.println("1. Basic Attack for: " + player.getAttack() + "dmg");
    }
    System.out.println("2. Defend");
    System.out.print("3. Use Skill: ");
    player.displayUniqueSkill();
    System.out.println("4. Use Item");
  }

  //Shift the game over, game Victory messages function here
  public void displayGameOverScreen(MainPlayer player){
    System.out.println("====== YOU DIED ======");
    System.out.println("====== GAME OVER ======");
  }

  public void displayVictoryScreen(MainPlayer player){
    System.out.println("====== YOU WIN! ======");
    System.out.println(player.getName() + " is victorous!");
  }
}
