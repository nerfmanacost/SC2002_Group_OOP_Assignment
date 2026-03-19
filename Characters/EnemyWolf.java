public class EnemyWolf extends MainEnemy{
    private static final int BASE_HEALTH = 40;
    private static final int BASE_ATTACK = 45;
    private static final int BASE_DEFENSE = 5;
    private static final int BASE_SPEED = 35;
    private static final String NAME = "Wolf";

    public EnemyWolf(int health, int attack, int defense, int speed){
        super(BASE_HEALTH, BASE_ATTACK, BASE_DEFENSE, BASE_SPEED);
        this.entitytype = TypeofEntity.ENE_WOLF;
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
