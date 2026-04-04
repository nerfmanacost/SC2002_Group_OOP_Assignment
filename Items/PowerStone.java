package Items;

import Characters.MainEnemy;
import Characters.PlayerWarrior;
import Characters.PlayerWizard;

public class PowerStone extends Item{
    public PowerStone() {
        super("Power Stone");
    }

    public void listEffect(){
        System.out.println("Cast an ability regardless of it's current cooldown. Does not affect cooldown timers.");
    }

    public void effect(PlayerWarrior warrior, MainEnemy enemy){
        int damage, stunDuration = 2;
        damage = warrior.specialskill(enemy, true);
        enemy.setStun(stunDuration);
        enemy.takeDamage(damage);
        System.out.println(String.format("xxx has taken %d damage and is stunned for %d turns.", damage, stunDuration));
    }

    public void effect(PlayerWizard wizard, MainEnemy[] enemies) {
        int damage;
        damage = wizard.specialskill(enemies, true);
        System.out.println(String.format("Enemies take a combined %d damage.", damage));
    }
}
