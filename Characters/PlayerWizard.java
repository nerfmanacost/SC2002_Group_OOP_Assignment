package Characters;

public class PlayerWizard extends MainPlayer{
    private static final int BASE_HEALTH = 200;
    private static final int BASE_ATTACK = 50;
    private static final int BASE_DEFENSE = 10;
    private static final int BASE_SPEED = 20;

    private int defendTurnRemaining = 0;
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
    
    public int getHealth(){return this.health;}
    public void healHealth(int heal){}


    //defense skill activation and tick cooldown
    private void defendTick(){if (defendTurnRemaining>0) defendTurnRemaining--;}
    private void activateDefend(){defendTurnRemaining = 2;}
    public void defendSkill(){
        //hard code turn count so it becomes easier
        activateDefend();
    }

    //special skill wizard is going to attack aoe all enemies => check for all enemies if their health is 0 after they
    //take damage == 0, wizard will get a buff, currently idk whether the buffs checking is correct, only can tell when doing in the main program
    public int specialskill(MainEnemy[] enemies){
        int totaldamage = 0;
        if (this.skillcooldown > 0){
            System.out.println("Skill on cooldown");
            return 0;
        }
        activateSkill();
        for (MainEnemy enemy : enemies){
            int damage = basicAttack(enemy);
            totaldamage += enemy.takeDamage(damage);
            if (enemy.getHealth() == 0){
                registerKill();
            }
        }
        skillbuff();
        resetKillCount();
        return totaldamage;
    }

    //all wizard attack buffs are only active for one round
    public int skillbuff(){return attackBuff + 10 * killcount;}
    public int effectiveAttack(){return this.attack + attackBuff;}
    private void resetAttackBuff(){attackBuff = 0;}
    
    //call reset after each use of skill, want to check eveyrtime whether wizard kills or not
    private void resetKillCount(){killcount = 0;}
    private void registerKill(){killcount++;}

    //getter setter methods for skillcooldowns 
    public int getskillcooldown(){return skillcooldown;}
    private void activateSkill(){skillcooldown = 3;}
    private void tickCooldown(){if (skillcooldown > 0) skillcooldown--;}

    //effective defense to add another layer of encapsulation
    public int effectiveDefense(){return defendTurnRemaining>0 ? this.defense + 10 : this.defense;}
    public int getBaseHealth(){return BASE_HEALTH;}

    public int takeDamage(int damage){
        if (this.health <= 0){ 
            System.out.println(name+" is already dead.");
            return 0;
        }
        //damage taken is strictly basic attack damage only
        //everyone has effective defense added to their basic attack (defense is already accounted for)
        this.health = Math.max(0, this.health - damage);
        if (this.health == 0){
            System.out.println("You have been slain");
        }
        return damage;
    }

    @Override
    public void tickAll(){defendTick(); tickCooldown();}

    //wizard buff only resets at the end of the level (technically can be considered perm buff for the wave)
    public void onLevelEnd(){resetAttackBuff();}

    @Override
    public void showStats(){
        System.out.println("Wizard: ");
        System.out.println("HP: "+this.health);
        System.out.println("ATK: "+this.attack);
        System.out.println("DEF: "+this.defense);
        System.out.println("SPD: "+this.speed);
    }

    public void gameReset(){
        this.health = BASE_HEALTH;
        this.attack = BASE_ATTACK;
        this.defense = BASE_DEFENSE;
        this.speed = BASE_SPEED;
    }
    private Inventory[] inventory;
    public void getInventory(){accessInventory(inventory);}
    private void accessInventory(Inventory[] inventory){
        for (Inventory item: inventory){
            System.out.println(item);
        }
    }
}