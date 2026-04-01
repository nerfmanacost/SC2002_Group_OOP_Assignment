package Characters;


public class PlayerWarrior extends MainPlayer{
    //use of static vars because we want the changes to reflect as the game continues
    private static final int BASE_HEALTH = 260;
    private static final int BASE_ATTACK = 40;
    private static final int BASE_DEFENSE = 20;
    private static final int BASE_SPEED = 30;
    private int defendTurnRemaining = 0;
    private int skillcooldown = 0;


    public PlayerWarrior(String name){
        super(name, BASE_HEALTH, BASE_ATTACK, BASE_DEFENSE, BASE_SPEED);
        this.entitytype = TypeofEntity.PLAY_WAR;
    }

    public int basicAttack(MainEntity defender){return Math.max(0, effectiveAttack() - defender.effectiveDefense());}
    
    private void defendTick(){if (defendTurnRemaining>0) defendTurnRemaining--;}
    private void activateDefend(){defendTurnRemaining = 2;}

    public void defendSkill(){activateDefend();}

    //if defendturnremaining > 0 return this.def + 10
    public int effectiveDefense(){return defendTurnRemaining>0 ? this.defense + 10 : this.defense;}
    public int effectiveAttack(){return this.attack;}

    public int getBaseHealth(){return BASE_HEALTH;}
    public String getName(){return name;}
    

    public int specialskill(MainEnemy enemy){
        if (skillcooldown > 0){
            System.out.println("Skill on cooldown");
            return 0;
        }
        activateSkill();
        return basicAttack(enemy);
    }

    public int getskillcooldown(){return skillcooldown;}
    private void tickCooldown(){if (skillcooldown > 0) skillcooldown--;}
    private void activateSkill(){skillcooldown = 3;}


    public void healHealth(int heal){this.health = heal;}

    @Override
    public void tickAll(){defendTick(); tickCooldown();}

    @Override
    public void showStats(){
        System.out.println("Warrior: ");
        System.out.println("HP: "+this.health);
        System.out.println("ATK: "+this.attack);
        System.out.println("DEF: "+this.defense);
        System.out.println("SPD: "+this.speed);
    }

    @Override 
    public void displayUniqueSkill(){
        System.out.println("Shield Bash");
    } 

    //for resetting of all base stats at the end of the game
    public void gameReset(){
        this.health = BASE_HEALTH;
        this.attack = BASE_ATTACK;
        this.defense = BASE_DEFENSE;
        this.speed = BASE_SPEED;
    }
}