package Characters;

public abstract class MainEnemy extends MainEntity implements EnemyDebuff{
    public MainEnemy(int health, int attack, int defense, int speed){
        super(health, attack, defense, speed);
    }

    public int takeDamage(int damage){
        if (this.health == 0){
            if(this instanceof EnemyWolf){
                System.out.println("Wolf is dead.");
            } else if (this instanceof EnemyGoblin){
                System.out.println("Goblin is dead.");
            }
            return -1;
        }
        //damage taken is strictly basic attack damage only
        this.health = Math.max(0, this.health - damage);

        if (this.health == 0){
            if(this instanceof EnemyWolf){
                System.out.println("Wolf has been slain.");
            } else if (this instanceof EnemyGoblin){
                System.out.println("Goblin has been slain.");
            }
        }
        return damage;
    }
}