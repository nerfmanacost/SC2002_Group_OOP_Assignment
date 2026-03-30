import Characters.EnemyGoblin;
import Characters.EnemyWolf;
import Characters.MainEnemy;
import Characters.MainPlayer;
import Characters.PlayerWarrior;
import Characters.PlayerWizard;

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
        if (result == 0) {
            return false;
        }
        System.out.println(String.format("%s has dealt %d damage to xxx.", player.getName(), damage));
        return true;
    }

    // subject enemies to skills
    public boolean enemyTakeSkillDamage(PlayerWarrior warrior, int target) {
        int damage, stunDuration = 2;
        MainEnemy enemy = enemies[target - 1];
        damage = warrior.specialskill(enemy);
        enemy.setStun(stunDuration);
        enemy.takeDamage(damage);
        System.out.println(String.format("xxx has taken %d damage and is stunned for %d turns.", damage, stunDuration));
        return true;
    }

    public void enemyTakeSkillDamage(PlayerWizard wizard) {
        int damage;
        damage = wizard.specialskill(enemies);
        System.out.println(String.format("Enemies take a combined %d damage.", damage));
    }

    // enemies in wave attack player
    public void enemyDealBasicAttackDamage(MainPlayer player) {
        int damage, totalDamage = 0;
        for (MainEnemy enemy : enemies) {
            damage = enemy.basicAttack(player);
            totalDamage += damage;
            if(damage != 0){
                System.out.println(String.format("xxx deals %d damage to %s.", damage, player.getName()));
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
