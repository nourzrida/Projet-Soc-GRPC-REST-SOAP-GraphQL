package patterns.state;

import model.Enemy;

public class MovingState implements EnemyState {
    @Override
    public void apply(Enemy enemy) {
        enemy.setSpeedMultiplier(1.0);
    }

    @Override
    public String name() {
        return "MOVING";
    }
}

