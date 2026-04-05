package Items;

import Characters.MainPlayer;

public class Potion extends Item{
    private static final int HEAL_VALUE = 100;

    public Potion() {
        super("Potion");
    }

    public void listEffect(){
        System.out.println("Heal for 100hp. Does not overheal.");
    }

    public void effect(MainPlayer player){
        player.healHealth(HEAL_VALUE);
        System.out.println(String.format("%s healed for %d health.", player.getName(), HEAL_VALUE));
    }
}
