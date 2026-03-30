package Characters;

public abstract class MainEnemy extends MainEntity implements EnemyDebuff{
    public MainEnemy(int health, int attack, int defense, int speed){
        super(health, attack, defense, speed);
    }
}