import java.util.Scanner;
import Characters.MainPlayer;
import Characters.PlayerWarrior;
import Characters.PlayerWizard;
import Difficulty.Difficulty;

//The playthrough itself.
public class GameSession {

    private BattleUI battleUI;

    // use of static as per Josh's philoshpy.
    private static final int BASE_ACTIONS = 1;

    private int actions;
    private Difficulty difficulty;
    private MainPlayer player;

    // need to bring the Item[] array that contains the list (2 items) that user has
    // chosen here.

    // private static

    // we have to get the MainClass here....

    // Constructor
    public GameSession(Difficulty gameDifficulty, MainPlayer player) {
        this.difficulty = gameDifficulty;
        this.player = player;
        this.actions = BASE_ACTIONS;
    }

    // we will start from turn 0, then when its our turn we increment, so we alr
    // increment by 1 from the start.
    private int currentTurn = 0;
    // if game over, we print game over screen.
    // see how we want to implement user select after game over.
    private boolean isGameOver = false;

    public void startGame() {
        Wave wave = new Wave(this.difficulty.getInitialSpawn());
        int waveCounter = 1;
        System.out.println("New Game Start!");
        System.out.println("Incoming wave: ");
        boolean gameWon = false, changeWave = false;

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

            changeWave = playerTurn(player, wave);
            isGameOver = enemyTurn(player, wave);
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

    private boolean playerTurn(MainPlayer player, Wave wave) {

        int userChoice = 0;
        boolean result;
        Scanner sc = new Scanner(System.in);

        // tick all player statuses
        if (player instanceof PlayerWarrior warrior) {
            warrior.tickAll();
        } else if (player instanceof PlayerWizard wizard) {
            wizard.tickAll();
        }

        System.out.println("Enemies:");
        wave.printWaveInfo();
        while (true) {
            System.out.println("Enter your choice:\n1. Attack\n2. Defend\n3. Use special skill\n4. Use item");
            if (sc.hasNextInt()) {
                userChoice = sc.nextInt();
                sc.nextLine();
            }
            if (userChoice >= 1 && userChoice <= 4) {
                int cooldown = 0;
                if(userChoice == 3){
                    if(player instanceof PlayerWarrior warrior){
                        cooldown = warrior.getskillcooldown();
                    } else if(player instanceof PlayerWizard wizard){
                        cooldown = wizard.getskillcooldown();
                    }
                    if(cooldown != 0){
                        System.out.println("Skill on cooldown.");
                        continue;
                    }
                }
                break;
            } else {
                System.out.println("Please enter a number between 1 and 4.");
            }
        }

        switch (userChoice) {
            // Attack
            case 1:
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

            // Defend
            case 2:
                player.defendSkill();
                System.out.println(String.format("%s raises defense to %d for 2 turns.", player.getName(), player.getDefense()));
                break;

            // skill
            case 3:
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
                        if (result) {
                            break;
                        }
                    }
                } else if(player instanceof PlayerWizard wizard){
                    wave.enemyTakeSkillDamage(wizard);
                }
                break;
            case 4:
                // item
                break;
        }

        // reset action count
        actions = BASE_ACTIONS;

        if (wave.enemiesRemaining() == 0) {
            return true;
        }
        return false;

    }

    private boolean enemyTurn(MainPlayer player, Wave wave) {
        wave.enemyDealBasicAttackDamage(player);
        wave.enemyUpkeep();
        if (player.getHealth() == 0) {
            return true;
        }
        return false;
    }
}
