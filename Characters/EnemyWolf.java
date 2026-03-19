public class EnemyWolf extends MainEnemy{
    private static final int BASE_HEALTH = 40;
    private static final int BASE_ATTACK = 45;
    private static final int BASE_DEFENSE = 5;
    private static final int BASE_SPEED = 35;
    private static final String NAME = "Wolf";

    public EnemyWolf(){
        super(BASE_HEALTH, BASE_ATTACK, BASE_DEFENSE, BASE_SPEED);
        this.entitytype = TypeofEntity.ENE_WOLF;
    }
    
    @Override
    public void showStats(){
        System.out.println(NAME);
        System.out.println("HP: "+this.health);
        System.out.println("ATK: "+this.attack);
        System.out.println("DEF: "+this.defense);
        System.out.println("SPD: "+this.speed);
    }

    public int basicattack(MainEntity defender){
        return Math.max(0, this.attack - defender.getDefense());
    }
}
