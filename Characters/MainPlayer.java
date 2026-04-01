package Characters;

public abstract class MainPlayer extends MainEntity implements PlayerBuff{
    public MainPlayer(String name, int health, int attack, int defense, int speed){
        super(name, health, attack, defense, speed);
    }
    
    public void setName(String name){this.name = name;}
    public String getName(){return this.name;}

    public abstract void gameReset();
    public abstract int getBaseHealth();
    public abstract void healHealth(int heal);

    public abstract void displayUniqueSkill();
}