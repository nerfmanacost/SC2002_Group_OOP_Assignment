public class PlayerWizard extends MainPlayer{
    private static final int BASE_HEALTH = 200;
    private static final int BASE_ATTACK = 50;
    private static final int BASE_DEFENSE = 10;
    private static final int BASE_SPEED = 20;
    private int defendTurnRemaining = 0;
    private int skillcooldown = 0;

    public PlayerWizard(String name){
        super(name, BASE_HEALTH, BASE_DEFENSE, BASE_ATTACK, BASE_SPEED);
        this.entitytype = TypeofEntity.PLAY_WIZ;
    }

    public int basicattack(MainEntity defender){
        return Math.max(0, this.attack - defender.getDefense());
    }

    public void specialskill(MainEntity[] enemies){
        if (skillcooldown > 0){
            System.out.println("Skill on cooldown");
        }
        activateSkill();
        for (MainEntity enemy : enemies){
            basicattack(enemy);
        }
    }

    public int getskillcooldown(){return skillcooldown;}
    public void activateSkill(){skillcooldown = 3;}
    public void tickCooldown(){if (skillcooldown > 0) skillcooldown--;}

    public int skillbuff(){
        //track how many enemies killed
        return this.attack; //+ 10 * num of enemies killed
    }

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
        System.out.println("Wizard: ");
        System.out.println("HP: "+this.health);
        System.out.println("ATK: "+this.attack);
        System.out.println("DEF: "+this.defense);
        System.out.println("SPD: "+this.speed);
    }
}