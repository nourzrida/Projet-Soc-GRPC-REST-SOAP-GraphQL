package patterns.state;

import model.Enemy;

public class AttackingState implements EnemyState {
    @Override
    public void apply(Enemy enemy) {
        enemy.setSpeedMultiplier(0.0);
    }

    @Override
    public String name() {
        return "ATTACKING";
    }
}

