package Difficulty;

public class Difficulty {
  private String name; 
  private String level;
  //private Enemy[] initialSpawn;
  //private Enemy[] backupSpawn; //just make this null for easy.

  public Difficulty(String name, String level){
    this.name = name;
    this.level = level;
  }

  public String getName() {return name;};
  //need to account for null
  //public Enemy[] getInitialSpawn() { return initialSpawn; }

}
