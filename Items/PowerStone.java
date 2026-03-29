package Items;

public class PowerStone extends Item{
    public PowerStone() {
        super("Power Stone");
    }

    public void listEffect(){
        System.out.println("Cast an ability regardless of it's current cooldown. Does not affect cooldown timers.");
    }
}
