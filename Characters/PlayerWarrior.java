public class PlayerWarrior extends MainPlayer{
    //use of static vars because we want the changes to reflect as the game continues
    private static final int BASE_HEALTH = 260;
    private static final int BASE_ATTACK = 40;
    private static final int BASE_DEFENSE = 20;
    private static final int BASE_SPEED = 30;
    private int defendTurnRemaining = 0;
    private int skillcooldown = 0;
    private int stunDur = 0;


    public PlayerWarrior(String name){
        super(name, BASE_HEALTH, BASE_ATTACK, BASE_DEFENSE, BASE_SPEED);
        this.entitytype = TypeofEntity.PLAY_WAR;
    }

    public int basicAttack(MainEntity defender){return Math.max(0, this.attack - defender.getDefense());}
    
    private void defendTick(){if (defendTurnRemaining>0) defendTurnRemaining--;}
    public void activateDefend(){defendTurnRemaining = 2;}
    public int defendSkill(){
        //hard code turn count so it becomes easier
        activateDefend();
        return this.defense + 10;
    }

    public int specialskill(MainEntity enemy){
        if (skillcooldown > 0){
            System.out.println("Skill on cooldown");
            return 0;
        }
        activateSkill();
        setStun();
        return basicAttack(enemy);
    }

    public int getskillcooldown(){return skillcooldown;}
    private void tickCooldown(){if (skillcooldown > 0) skillcooldown--;}
    public void activateSkill(){skillcooldown = 3;}

    public int getStunWindow(){return stunDur;}
    public void setStun(){stunDur = 2;}

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

    @Override
    public void showStats(){
        System.out.println("Warrior: ");
        System.out.println("HP: "+this.health);
        System.out.println("ATK: "+this.attack);
        System.out.println("DEF: "+this.defense);
        System.out.println("SPD: "+this.speed);
    }

    //for resetting of all base stats at the end of the game
    public void gameReset(){
        this.health = BASE_HEALTH;
        this.attack = BASE_ATTACK;
        this.defense = BASE_DEFENSE;
        this.speed = BASE_SPEED;
    }
}