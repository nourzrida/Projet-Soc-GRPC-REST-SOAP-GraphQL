package patterns.state;

import model.Enemy;

public class DeadState implements EnemyState {
    @Override
    public void apply(Enemy enemy) {
        enemy.setAlive(false);
    }

    @Override
    public String name() {
        return "DEAD";
    }
}

