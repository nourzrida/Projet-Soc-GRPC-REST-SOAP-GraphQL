package patterns.composite;

import model.Enemy;
import util.GameLogger;

public class EnemyLeaf implements EnemyComponent {
    private final Enemy enemy;

    public EnemyLeaf(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void spawn() {
        GameLogger.getInstance().log("WAVE", "Spawn enemy " + enemy.getId());
        // spawn logic minimal
    }

    @Override
    public int count() {
        return 1;
    }
}

