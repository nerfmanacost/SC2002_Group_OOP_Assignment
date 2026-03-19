public class PlayerWizard extends MainPlayer{
    private static final int BASE_HEALTH = 200;
    private static final int BASE_ATTACK = 50;
    private static final int BASE_DEFENSE = 10;
    private static final int BASE_SPEED = 20;
    private int defendTurnRemaining = 0;
    private int skillcooldown = 0;
    private boolean defensebuff = false;
    private int killcount = 0;

    public PlayerWizard(String name){
        super(name, BASE_HEALTH, BASE_ATTACK, BASE_DEFENSE, BASE_SPEED);
        this.entitytype = TypeofEntity.PLAY_WIZ;
    }

    public int basicattack(MainEntity defender){
        int damage = Math.max(0, this.attack - defender.getDefense());
        return damage;
    }

    public void specialskill(MainEntity[] enemies){
        if (skillcooldown > 0){
            System.out.println("Skill on cooldown");
            return;
        }
        activateSkill();
        for (MainEntity enemy : enemies){
            int damage = basicattack(enemy);
            enemy.takeDamage(damage);
        }
    }

    public int takeDamage(int damage){
        if (this.health <= 0){ 
            System.out.println("You are already dead.");
            return 0;
        }  
        this.health = Math.max(0, this.health - damage);
        if (this.health == 0){
            System.out.println("You are dead");
        }
        return damage;
    }

    public int getskillcooldown(){return skillcooldown;}
    public void activateSkill(){skillcooldown = 3;}
    public void tickCooldown(){if (skillcooldown > 0) skillcooldown--;}

    public int skillbuff(){
        //track how many enemies killed
        return this.attack + 10 * killcount;
    }

    public void activateDefend(){
        defensebuff = true;
        defendTurnRemaining = 2;
    }

    public void registerKill(){
        killcount++;
    }

    public int defend(){
        //hard code turn count so it becomes easier
        if (defensebuff && defendTurnRemaining > 0){
            defendTurnRemaining --;
            if (defendTurnRemaining == 0) defensebuff = false;
            return this.defense + 10;
        }
        return this.defense;
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