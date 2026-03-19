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

    public int basicattack(MainEntity defender){return Math.max(0, this.attack - defender.getDefense());}
    
    public int specialskill(MainEntity enemy){
        if (skillcooldown > 0){
            System.out.println("Skill on cooldown");
            return 0;
        }
        activateSkill();
        activateStun();
        return basicattack(enemy);
    }

    public int getskillcooldown(){return skillcooldown;}
    public int getStunWindow(){return stunDur;}

    public void activateSkill(){skillcooldown = 3;}
    public void activateStun(){stunDur = 2;}
    public void tickCooldown(){if (skillcooldown > 0) skillcooldown--;}

    public int defend(){
        //hard code turn count so it becomes easier
        if (defendTurnRemaining > 0){
            defendTurnRemaining --;
            return this.defense + 10;
        }
        return this.defense;
    }

    public void activateDefend(){
        defendTurnRemaining = 2;
    }

    public int ActionValue(){
        return 1000/BASE_SPEED;
    }

    @Override
    public void showStats(){
        System.out.println("Warrior: ");
        System.out.println("HP: "+this.health);
        System.out.println("ATK: "+this.attack);
        System.out.println("DEF: "+this.defense);
        System.out.println("SPD: "+this.speed);
    }
}