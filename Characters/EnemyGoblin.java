package Characters;

public class EnemyGoblin extends MainEnemy{
    private static final int BASE_HEALTH = 55;
    private static final int BASE_ATTACK = 35;
    private static final int BASE_DEFENSE = 15;
    private static final int BASE_SPEED = 25;
    private static final String NAME = "Goblin";
    private int stunTurn = 0;

    public EnemyGoblin(){
        super(BASE_HEALTH, BASE_ATTACK, BASE_DEFENSE, BASE_SPEED);
        this.entitytype = TypeofEntity.ENE_GOB;
    }

    public int setStun(int duration){stunTurn = duration; return stunTurn;}
    public boolean stunStatus(){return stunTurn>0;}
    private void stunTick(){if (stunTurn>0) stunTurn--;}

    public int basicAttack(MainEntity defender){
        if (stunStatus()){
            System.out.println(NAME + " is stunned, unable to take action.");
            return 0;
        }else if (getHealth() <= 0){
            System.out.println(NAME + " is already dead, unable to take action.");
            return 0;
        }
        return Math.max(0, effectiveAttack() - defender.effectiveDefense());
    }

    public void tickAll(){stunTick();}

    public String getName(){return NAME;}
    public int effectiveDefense(){return this.defense;}
    public int effectiveAttack(){return this.attack;}
    public int getBaseHealth(){return BASE_HEALTH;}
    

    //resetting for level (in case)
    public void gameReset(){
        this.health = BASE_HEALTH;
        this.attack = BASE_ATTACK;
        this.defense = BASE_DEFENSE;
        this.speed = BASE_SPEED;
    }
    
    @Override
    public void showStats(){
        System.out.println(NAME);
        System.out.print("HP: "+this.health+"\t");
        System.out.print("ATK: "+this.attack+"\t");
        System.out.print("DEF: "+this.defense+"\t");
        System.out.println("SPD: "+this.speed);
    }
}