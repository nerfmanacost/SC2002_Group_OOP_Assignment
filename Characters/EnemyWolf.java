public class EnemyWolf extends MainEnemy{
    private static final int BASE_HEALTH = 40;
    private static final int BASE_ATTACK = 35;
    private static final int BASE_DEFENSE = 15;
    private static final int BASE_SPEED = 25;

    public EnemyWolf(String name){
        super(name, BASE_HEALTH, BASE_ATTACK, BASE_DEFENSE, BASE_SPEED);
        this.entitytype = TypeofEntity.ENE_WOLF;
    }
    public int basicattack(MainEntity defender){
        return Math.max(0, this.attack - defender.getDefense());
    }
}
