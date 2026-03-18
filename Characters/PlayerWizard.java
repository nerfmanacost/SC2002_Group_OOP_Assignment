public class PlayerWizard extends MainPlayer{
    private static final int BASE_HEALTH = 200;
    private static final int BASE_ATTACK = 50;
    private static final int BASE_DEFENSE = 10;
    private static final int BASE_SPEED = 20;

    public PlayerWizard(String name){
        super(name, BASE_HEALTH, BASE_DEFENSE, BASE_ATTACK, BASE_SPEED);
        this.entitytype = TypeofEntity.PLAY_WIZ;
    }

    public int basicattack(MainEntity defender){
        return Math.max(0, this.attack - defender.getDefense());
    }

    public int specialskill(){return 0;};

    public int defend(){return 0;}

    public void showStats(){
        System.out.println("Warrior: ");
        System.out.println("HP: "+BASE_HEALTH);
        System.out.println("ATK: "+BASE_ATTACK);
        System.out.println("DEF: "+BASE_DEFENSE);
        System.out.println("SPD: "+BASE_SPEED);
    }
}
