package Characters;

public class EnemyWolf extends MainEnemy{
    private static final int BASE_HEALTH = 40;
    private static final int BASE_ATTACK = 45;
    private static final int BASE_DEFENSE = 5;
    private static final int BASE_SPEED = 35;
    private static final String NAME = "Wolf";
    private int stunTurn = 0;

    public EnemyWolf(){
        super(BASE_HEALTH, BASE_ATTACK, BASE_DEFENSE, BASE_SPEED);
        this.entitytype = TypeofEntity.ENE_WOLF;
    }
    
    public int basicAttack(MainEntity defender){
        if (stunStatus()){
            System.out.println(NAME + " is stunned, unable to take action.");
            return 0;
        }
        return Math.max(0, effectiveAttack() - defender.effectiveDefense());
    }

    //warrior skill cooldown will be longer than the stunwindow for mobs
    //pass player.getStunWindow() through setstun
    public int setStun(int duration){stunTurn = duration; return stunTurn;}
    public boolean stunStatus(){return stunTurn>0;}
    private void stunTick(){if (stunTurn>0) stunTurn--;}

    public String getName(){return NAME;}
    public int effectiveDefense(){return this.defense;}
    public int effectiveAttack(){return this.attack;}
    public int getBaseHealth(){return BASE_HEALTH;}

    public void tickAll(){stunTick();}

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
        System.out.println("SPD: "+this.speed+"\t");
    }
}