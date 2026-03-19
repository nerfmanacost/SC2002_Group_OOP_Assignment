public abstract class MainPlayer extends MainEntity {
    public MainPlayer(String name, int health, int defense, int attack, int speed){
        super(name, health, defense, attack, speed);
    }
    
    public void setName(String name){this.name = name;}

    public String getName(){return this.name;}

    public abstract int specialskill();
    public abstract void showStats();
    public abstract int defend();
}   
