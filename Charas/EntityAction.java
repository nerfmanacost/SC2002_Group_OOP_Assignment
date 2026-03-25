package Charas;

public interface EntityAction {
    int basicAttack(MainEntity defender);
    int takeDamage(int damage);
    int effectiveAttack();
    int effectiveDefense();
    void onTurnEnd();
    void onLevelEnd();
    void showStats();
    void gameReset();
}
