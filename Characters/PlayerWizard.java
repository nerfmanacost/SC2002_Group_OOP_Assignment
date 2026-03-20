public class PlayerWizard extends MainPlayer{
    private static final int BASE_HEALTH = 200;
    private static final int BASE_ATTACK = 50;
    private static final int BASE_DEFENSE = 10;
    private static final int BASE_SPEED = 20;

    private int defendTurnRemaining = 0;
    private int skillcooldown = 0;
    private int killcount = 0;

    public PlayerWizard(String name){
        super(name, BASE_HEALTH, BASE_ATTACK, BASE_DEFENSE, BASE_SPEED);
        this.entitytype = TypeofEntity.PLAY_WIZ;
    }

    public int basicAttack(MainEntity defender){
        int damage = Math.max(0, this.attack - defender.getDefense());
        return damage;
    }

    private void defendTick(){if (this.defendTurnRemaining>0) this.defendTurnRemaining--;}
    public void activateDefend(){this.defendTurnRemaining = 2;}
    public int defendSkill(){
        //hard code turn count so it becomes easier
        activateDefend();
        return this.defense + 10;
    }

    public int specialskill(MainEntity[] enemies){
        int totaldamage = 0;
        if (this.skillcooldown > 0){
            System.out.println("Skill on cooldown");
            return 0;
        }
        activateSkill();
        for (MainEntity enemy : enemies){
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
    public int skillbuff(){return this.attack + 10 * killcount;}
    private void resetAttackBuff(){this.attack = BASE_ATTACK;}
    
    public void resetKillCount(){killcount = 0;}
    public void registerKill(){killcount++;}

    public int getskillcooldown(){return skillcooldown;}
    public void activateSkill(){skillcooldown = 3;}
    private void tickCooldown(){if (skillcooldown > 0) skillcooldown--;}

    public int takeDamage(int damage){
        if (this.health <= 0){ 
            System.out.println(name+" is already dead.");
            return 0;
        }
        //damage taken is strictly basic attack damage only
        this.health = Math.max(0, this.health - damage);
        if (this.health == 0){
            System.out.println("You have been slain");
        }
        return damage;
    }

    public void onTurnEnd(){defendTick(); tickCooldown();}
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
}