package Difficulty;
import Characters.EnemyGoblin;
import Characters.EnemyWolf;
import Characters.MainEnemy;

public class Difficulty {
  private String name; //name is the string difficulty
  private MainEnemy[] initialSpawn;
  private MainEnemy[] backupSpawn; //just make this null for easy level where there is no backupSpawn`

  public Difficulty(String name, MainEnemy[] initialSpawn, MainEnemy[] backupSpawn){
    this.name = name;
    this.initialSpawn = initialSpawn;
    this.backupSpawn = backupSpawn;
  }

  public String getName() {return name;};
  public MainEnemy[] getInitialSpawn() { return initialSpawn; }
  //need to account for null
  public MainEnemy[] getBackupSpawn() {return backupSpawn; }
  //the function to check if there is backupSpawn
  public boolean hasBackupSpawn() {return backupSpawn != null;} 

  public void printWaveInfo() {
    System.out.println("  Initial Spawn:");
    printEnemyCounts(initialSpawn);

    if (hasBackupSpawn()) {
        System.out.println("  Backup Spawn:");
        printEnemyCounts(backupSpawn);
    } else {
        System.out.println("  Backup Spawn: None");
    }
  }


  private void printEnemyCounts(MainEnemy[] wave) {
    int goblins = 0, wolves = 0;

    for (MainEnemy e : wave) {
        if (e instanceof EnemyGoblin) goblins++;
        if (e instanceof EnemyWolf) wolves++;
    }

    if (goblins > 0) System.out.println("    - " + goblins + "x Goblin");
    if (wolves > 0)  System.out.println("    - " + wolves + "x Wolf");
  }
}
