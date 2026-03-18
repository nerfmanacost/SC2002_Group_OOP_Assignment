public class PlayerWarrior extends MainPlayer{
    //use of static vars because we want the changes to reflect as the game continues
    private static final int BASE_HEALTH = 260;
    private static final int BASE_ATTACK = 40;
    private static final int BASE_DEFENSE = 20;
    private static final int BASE_SPEED = 30;


    public PlayerWarrior(String name){
        super(name, BASE_HEALTH, BASE_DEFENSE, BASE_ATTACK, BASE_SPEED);
        this.entitytype = TypeofEntity.PLAY_WAR;
    }

    public int basicattack(MainEntity defender){
        return Math.max(0, this.attack - defender.getDefense());
    }
    
    public int specialskill(){
        return 0;
    }

    public int defend(){return 0;}

    public void showStats(){
        System.out.println("Warrior: ");
        System.out.println("HP: "+BASE_HEALTH);
        System.out.println("ATK: "+BASE_ATTACK);
        System.out.println("DEF: "+BASE_DEFENSE);
        System.out.println("SPD: "+BASE_SPEED);
    }
}
