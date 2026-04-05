package Items;

import Characters.MainPlayer;
import Characters.PlayerWarrior;
import Characters.PlayerWizard;

public class SmokeBomb extends Item{
    private static final int DURATION = 2;

    public SmokeBomb(){
        super("Smoke Bomb");
    }
    public void listEffect(){
        System.out.println("Enemy attacks no longer deal damage. Lasts for 2 turns.");
    }

    public void effect(MainPlayer player){
        if(player instanceof PlayerWarrior warrior){
            warrior.setSmoke(DURATION);
        } else if (player instanceof PlayerWizard wizard){
            wizard.setSmoke(DURATION);
        }
        System.out.println(String.format("Smoke bomb used, %s is now invulnerable for %d turns.", player.getName(), DURATION));
    }
}