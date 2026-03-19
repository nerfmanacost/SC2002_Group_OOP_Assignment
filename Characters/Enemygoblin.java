public class Enemygoblin extends MainEnemy{
    private static final int BASE_HEALTH = 55;
    private static final int BASE_ATTACK = 35;
    private static final int BASE_DEFENSE = 15;
    private static final int BASE_SPEED = 25;
    private static final String NAME = "Goblin";

    public Enemygoblin(int health, int attack, int defense, int speed){
        super(BASE_HEALTH, BASE_ATTACK, BASE_DEFENSE, BASE_SPEED);
        this.entitytype = TypeofEntity.ENE_GOB;
    }

    public void showStats(){
        System.out.println(NAME);
        System.out.println("HP: "+BASE_HEALTH);
        System.out.println("ATK: "+BASE_ATTACK);
        System.out.println("DEF: "+BASE_DEFENSE);
        System.out.println("SPD: "+BASE_SPEED);
    }

    public int basicattack(MainEntity defender){
        return Math.max(0, this.attack - defender.getDefense());
    }
}
