package Items;

public class SmokeBomb extends Item{
    public SmokeBomb(){
        super("Smoke Bomb");
    }
    public void listEffect(){
        System.out.println("Enemy attacks no longer deal damage. Lasts for 2 turns.");
    }
}