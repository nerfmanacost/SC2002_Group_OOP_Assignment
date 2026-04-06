package Characters;

public class PlayerWizard extends MainPlayer{
    private static final int BASE_HEALTH = 200;
    private static final int BASE_ATTACK = 50;
    private static final int BASE_DEFENSE = 10;
    private static final int BASE_SPEED = 20;

    private int defendTurnRemaining = 0;
    private int smokeTurnRemaining = 0;
    private int skillcooldown = 0;
    private int killcount = 0;
    private int attackBuff = 0;

    public PlayerWizard(String name){
        super(name, BASE_HEALTH, BASE_ATTACK, BASE_DEFENSE, BASE_SPEED);
        this.entitytype = TypeofEntity.PLAY_WIZ;
    }

    //basic attack takes defender defense
    public int basicAttack(MainEntity defender){
        int damage = Math.max(0, effectiveAttack() - defender.effectiveDefense());
        return damage;
    }
    
    public void healHealth(int heal){this.health = Math.min(this.health + heal, BASE_HEALTH);}


    //defense skill activation and tick cooldown
    private void defendTick(){if (defendTurnRemaining>0) defendTurnRemaining--;}
    private void activateDefend(){defendTurnRemaining = 2;}
    public void defendSkill(){
        //hard code turn count so it becomes easier
        activateDefend();
    }

    // special skill damages all enemies and raises wizard effectiveatk by 10 for every enemy killed 
    public int specialskill(MainEnemy[] enemies, boolean usedPowerStone){
        int totaldamage = 0;
        if (usedPowerStone) {
            System.out.println("Power Stone used, free use of skill!");
            for (MainEnemy enemy : enemies){
                if(enemy.getHealth() == 0){
                    continue;
                }
                int damage = basicAttack(enemy);
                totaldamage += enemy.takeDamage(damage);
                if (enemy.getHealth() == 0){
                    registerKill();
                }
            }
            skillbuff();
            resetKillCount();
            return totaldamage;
        } else if (this.skillcooldown > 0){
            System.out.println("Skill is on cooldown!");
            return 0;
        } else {
            activateSkill();
            for (MainEnemy enemy : enemies){
                int damage = basicAttack(enemy);
                totaldamage += enemy.takeDamage(damage);
                if (enemy.getHealth() <= 0){
                    registerKill();
                }
            }
            skillbuff();
            resetKillCount();
            return totaldamage;
        }
    }

    public void skillbuff(){attackBuff += 10 * killcount;}
    public int effectiveAttack(){return this.attack + attackBuff;}
    private void resetAttackBuff(){attackBuff = 0;}
    public String getName(){return name;}
    
    //call reset after each use of skill, want to check everytime whether wizard kills or not
    private void resetKillCount(){killcount = 0;}
    private void registerKill(){killcount++;}

    //getter setter methods for skillcooldowns 
    public int getskillcooldown(){return skillcooldown;}
    private void activateSkill(){skillcooldown = 3;}
    private void tickCooldown(){if (skillcooldown > 0) skillcooldown--;}

    //effective defense to add another layer of encapsulation
    public int effectiveDefense(){return defendTurnRemaining>0 ? this.defense + 10 : this.defense;}
    public int getBaseHealth(){return BASE_HEALTH;}

    public boolean smokeStatus(){return smokeTurnRemaining>0;}
    public void setSmoke(int duration){smokeTurnRemaining = duration;}

    @Override
    public void tickAll(){defendTick(); tickCooldown();}

    //wizard buff only resets at the end of the level
    public void onLevelEnd(){resetAttackBuff();tickAll();}

    @Override
    public void showStats(){
        System.out.println("Wizard: ");
        System.out.println("HP: "+this.health);
        System.out.println("ATK: "+this.attack);
        System.out.println("DEF: "+this.defense);
        System.out.println("SPD: "+this.speed);
    }

    @Override 
    public void displayUniqueSkill(){
        System.out.println("Arcane Blast");
    } 

    public void gameReset(){
        this.health = BASE_HEALTH;
        this.attack = BASE_ATTACK;
        this.defense = BASE_DEFENSE;
        this.speed = BASE_SPEED;
    }
}