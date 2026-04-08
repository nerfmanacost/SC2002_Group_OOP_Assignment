import java.util.Scanner;

import Characters.MainEnemy;
import Characters.MainPlayer;
import Characters.PlayerWarrior;
import Characters.PlayerWizard;
import Difficulty.Difficulty;
import Items.Inventory;
import Items.Item;
import Items.Potion;
import Items.SmokeBomb;
import Items.PowerStone;

//The playthrough itself.
public class GameSession {

    private BattleUI battleUI;
    private Difficulty difficulty;
    private MainPlayer player;
    private Inventory inv;

    // Constructor
    public GameSession(Difficulty gameDifficulty, MainPlayer player, Inventory inv) {
        this.difficulty = gameDifficulty;
        this.player = player;
        this.inv = inv;
        this.battleUI = new BattleUI();
    }

    private int currentTurn = 1;
    // if game over, we print game over screen.
    // see how we want to implement user select after game over.
    private boolean isGameOver = false;

    public void startGame() {
        Wave wave = new Wave(this.difficulty.getInitialSpawn());
        int waveCounter = 1;
        System.out.println("New Game Start!");
        System.out.println("Incoming wave: ");
        boolean gameWon = false, changeWave = false;
        int enemySpeed = 0;
        int playerSpeed = player.getSpeed();
        for(MainEnemy enemy : wave.getWave()){
            if(enemy.getSpeed() > enemySpeed){
                enemySpeed = enemy.getSpeed();
            }
        }
        if(playerSpeed >= enemySpeed){
            System.out.println(String.format("%s is faster and starts first.", player.getName()));
        } else {
            System.out.println("Enemies are faster and they start first.");
        }
        while (!isGameOver) {
            // main game logic
            if (changeWave && this.difficulty.hasBackupSpawn()) {
                wave.changeWave(this.difficulty.getBackupSpawn());
                waveCounter++;
            } else if (changeWave && !this.difficulty.hasBackupSpawn()) {
                gameWon = true;
                break;
            }

            if (waveCounter == 3) {
                gameWon = true;
                break;
            }


            if(playerSpeed >= enemySpeed){
                changeWave = playerTurn(player, wave, changeWave);
                isGameOver = enemyTurn(player, wave);
            } else {
                isGameOver = enemyTurn(player, wave);
                if (isGameOver) {
                    gameWon = false;
                    continue;
                }
                changeWave = playerTurn(player, wave, changeWave);
            }


            if (isGameOver) {
                gameWon = false;
            }
            currentTurn++;
        }

        // Game Over
        if (gameWon == true) {
            battleUI.displayVictoryScreen(player);
        } else {
            battleUI.displayGameOverScreen(player);
            // see if need to let user to retry, to redirect to loading screen
        }
    }

    private boolean playerTurn(MainPlayer player, Wave wave, boolean changeWave) {

        int userChoice = 0;
        boolean result;
        Scanner sc = new Scanner(System.in);

        // tick all player statuses
        if (player instanceof PlayerWarrior warrior) {
            warrior.tickAll();
        } else if (player instanceof PlayerWizard wizard) {
            wizard.tickAll();
        }
        if(changeWave){
            battleUI.printNextWaveHeader();
        }
        while (true) {
            System.out.println("====== ENEMIES ======");
            wave.printWaveInfo();
            battleUI.displayCurrentTurnNumber(currentTurn);
            //print user info per turn
            battleUI.displayPlayerBattleStats(player);
            battleUI.displayUserActions(player);
            if (sc.hasNextInt()) {
                userChoice = sc.nextInt();
            }
            sc.nextLine();
            if (userChoice >= 1 && userChoice <= 4) {
                int cooldown = 0;
                if(userChoice == 3){
                    if(player instanceof PlayerWarrior warrior){
                        cooldown = warrior.getskillcooldown();
                    } else if(player instanceof PlayerWizard wizard){
                        cooldown = wizard.getskillcooldown();
                    }
                    if(cooldown != 0){
                        System.out.println("Skill is on cooldown!");
                        continue;
                    }
                } else if (userChoice == 4 && inv.getSize() == 0){
                    inv.printInventory();
                    continue;
                }
                break;
            } else {
                System.out.println("Please enter a number between 1 and 4.");
            }
        }

        switch (userChoice) {
            case 1:
                // Attack
                while (true) {
                    System.out.println(String.format("Choose an enemy to attack(1 - %d): ", wave.totalEnemies()));
                    if (sc.hasNextInt()) {
                        userChoice = sc.nextInt();
                        sc.nextLine();
                        if (userChoice < 1 || userChoice > wave.totalEnemies()) {
                            System.out.println(String.format("Enter a number between 1 and %d. ", wave.totalEnemies()));
                            continue;
                        }
                    }
                    result = wave.enemyTakeBasicAttackDamage(player, userChoice);
                    if (result) {
                        break;
                    }
                }
                break;
            case 2:
                //Defend
                player.defendSkill();
                System.out.println(String.format("%s raises defense to %d for 2 turns.", player.getName(), player.effectiveDefense()));
                break;            
            case 3:
                //Skill usage
                if (player instanceof PlayerWarrior warrior) {
                    while (true) {
                        System.out.println(String.format("Choose an enemy to Shield Bash(1 - %d): ", wave.totalEnemies()));
                        if (sc.hasNextInt()) {
                            userChoice = sc.nextInt();
                            sc.nextLine();
                            if (userChoice < 1 || userChoice > wave.totalEnemies()) {
                                System.out.println(String.format("Enter a number between 1 and %d. ", wave.totalEnemies()));
                                continue;
                            }
                        }
                        result = wave.enemyTakeSkillDamage(warrior, userChoice);
                        if(result){
                            break;
                        }
                    }
                } else if(player instanceof PlayerWizard wizard){
                    wave.enemyTakeSkillDamage(wizard);
                }
                break;
            case 4:
                //Item usage
                inv.printInventory();
                System.out.println("Choose item to use:");
                while(true){
                    if(sc.hasNextInt()){
                        userChoice = sc.nextInt();
                    }
                    sc.nextLine();
                    if(userChoice <= inv.getSize() && userChoice >= 1){
                        break;
                    } else {
                        System.out.println(String.format("Please enter a number between 1 and %d.", inv.getSize()));
                    }
                }
                Item item = inv.getiItem(userChoice - 1);
                System.out.println(String.format("%s selected.", item.getName()));
                if(item instanceof Potion potion){
                    potion.effect(player);
                    battleUI.displayPlayerHealth(player);
                } else if (item instanceof SmokeBomb sb){
                    sb.effect(player);
                } else if (item instanceof PowerStone){
                    if(player instanceof PlayerWarrior warrior){
                        while (true) {
                            System.out.println(String.format("Choose an enemy to Shield Bash(1 - %d): ", wave.totalEnemies()));
                            if (sc.hasNextInt()) {
                                userChoice = sc.nextInt();
                                sc.nextLine();
                                if (userChoice < 1 || userChoice > wave.totalEnemies()) {
                                    System.out.println(String.format("Enter a number between 1 and %d. ", wave.totalEnemies()));
                                    continue;
                                }
                            }
                            wave.powerstone(warrior, userChoice);
                            break;
                        }
                    } else if (player instanceof PlayerWizard wizard){
                        wave.powerstone(wizard);
                    }
                }
                inv.removeFromInventory(userChoice - 1);
                break;
        }
        if (wave.enemiesRemaining() == 0) {
            return true;
        }
        return false;
    }

    private boolean enemyTurn(MainPlayer player, Wave wave) {
        System.out.println("====== ENEMY TURN ======");
        wave.enemyDealBasicAttackDamage(player);
        wave.enemyUpkeep();
        if (player.getHealth() == 0) {
            return true;
        }
        return false;
    }
}
