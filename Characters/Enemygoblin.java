public class Enemygoblin extends MainEnemy{
    private static final int BASE_HEALTH = 55;
    private static final int BASE_ATTACK = 35;
    private static final int BASE_DEFENSE = 15;
    private static final int BASE_SPEED = 25;
    private static final String NAME = "Goblin";

    public Enemygoblin(){
        super(BASE_HEALTH, BASE_ATTACK, BASE_DEFENSE, BASE_SPEED);
        this.entitytype = TypeofEntity.ENE_GOB;
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
