import Characters.EnemyGoblin;
import Characters.EnemyWolf;
import Characters.MainEnemy;
import Characters.MainPlayer;
import Characters.PlayerWarrior;
import Characters.PlayerWizard;
import Items.PowerStone;

public class Wave {
    private int enemyCount;
    private MainEnemy[] enemies;

    public Wave(MainEnemy[] enemies) {
        this.enemies = enemies;
    }

    // enemies alive in wave
    public int enemiesRemaining() {
        enemyCount = 0;
        for (MainEnemy enemy : enemies) {
            if (enemy.getHealth() != 0) {
                enemyCount++;
            }
        }
        return enemyCount;
    }

    // total enemies in wave
    public int totalEnemies(){
        return enemies.length;
    }

    public MainEnemy[] getWave() {
        return enemies;
    }

    public void printWaveInfo() {
        int i = 1;
        for (MainEnemy enemy : enemies) {
            System.out.println(String.format("Enemy %d:", i));
            enemy.showStats();
            i++;
        }
    }

    public void changeWave(MainEnemy[] enemies) {
        this.enemies = enemies;
    }

    // enemies take basic attack damage
    public boolean enemyTakeBasicAttackDamage(MainPlayer player, int target) {
        int damage, result;
        MainEnemy enemy = enemies[target - 1];
        damage = player.basicAttack(enemy);
        result = enemy.takeDamage(damage);
        if (result == -1) {
            return false;
        }
        System.out.println(String.format("%s has dealt %d damage to %s.", player.getName(), damage, enemy.getName()));
        return true;
    }

    // subject enemies to skills
    public boolean enemyTakeSkillDamage(PlayerWarrior warrior, int target) {
        int result, damage, stunDuration = 2;
        MainEnemy enemy = enemies[target - 1];
        damage = warrior.specialskill(enemy, false);
        enemy.setStun(stunDuration);
        result = enemy.takeDamage(damage);
        if (result == -1){
            return false;
        }
        System.out.println(String.format("%s has taken %d damage and is stunned for %d turns.", enemy.getName(), damage, stunDuration));
        return true;
    }

    public void enemyTakeSkillDamage(PlayerWizard wizard) {
        int damage;
        damage = wizard.specialskill(enemies, false);
        System.out.println(String.format("Enemies take a combined %d damage.", damage));
    }

    //powerstone usage
    public void powerstone(PlayerWarrior warrior, int target){
        MainEnemy enemy = enemies[target - 1];
        PowerStone.effect(warrior, enemy);
    }

    public void powerstone(PlayerWizard wizard){
        PowerStone.effect(wizard, enemies);
    }
    
    // enemies in wave attack player
    public void enemyDealBasicAttackDamage(MainPlayer player) {
        int damage, totalDamage = 0;
        boolean smoke = false;
        if(player instanceof PlayerWarrior warrior){
            smoke =  warrior.smokeStatus();
        } else if (player instanceof PlayerWizard wizard){
            smoke = wizard.smokeStatus();
        }
        if(smoke){
            System.out.println("Smoke Bomb active, enemies deal 0 damage.");
            return;
        }
        for (MainEnemy enemy : enemies) {
            damage = enemy.basicAttack(player);
            totalDamage += damage;
            if(damage != 0){
                System.out.println(String.format("%s has dealt %d damage to %s.", enemy.getName(), damage, player.getName()));
            }
        }
        player.takeDamage(totalDamage);
    }

    //ticks all enemy statuses
    public void enemyUpkeep() {
        for (MainEnemy enemy : enemies) {
            if (enemy instanceof EnemyGoblin goblin) {
                goblin.tickAll();
            } else if (enemy instanceof EnemyWolf wolf) {
                wolf.tickAll();
            }
        }
    }
}
