package Characters;

public interface EntityAction {
    int basicAttack(MainEntity defender);
    int effectiveAttack();
    int effectiveDefense();
    void showStats();
    void gameReset();
}