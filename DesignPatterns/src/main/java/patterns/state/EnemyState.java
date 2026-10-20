package patterns.state;

import model.Enemy;

public interface EnemyState {
    void apply(Enemy enemy);
    String name();
}

