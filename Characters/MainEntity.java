public abstract class MainEntity {
    public enum TypeofEntity{PLAY_ENTI, PLAY_WAR, PLAY_WIZ, ENE_WOLF, ENE_GOB};
    protected int health, defense, attack, speed;
    protected String name;
    protected TypeofEntity entitytype;

    public MainEntity(String name, int health, int defense, int attack, int speed){
        this.attack = attack;
        this.defense = defense;
        this.health = health;
        this.speed = speed;
        this.name = name;
        this.entitytype =  TypeofEntity.PLAY_ENTI;
    }
    
    public int getAttack(){return attack;}
    public int getDefense(){return defense;}
    public int getSpeed(){return speed;}

    public abstract int basicattack(MainEntity defender);


    
}
