package Items;

public class Potion extends Item{
    private static final int HEAL_VALUE = 100;

    public Potion() {
        super("Potion");
    }

    public void listEffect(){
        System.out.println("Heal for 100hp. Does not overheal.");
    }
}
