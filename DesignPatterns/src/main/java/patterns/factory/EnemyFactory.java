package patterns.factory;

import model.Enemy;
import model.enemies.BasicEnemy;
import model.enemies.FastEnemy;

public class EnemyFactory {
    public static Enemy create(String type, String id) {
        return switch (type.toLowerCase()) {
            case "fast" -> new FastEnemy(id);
            default -> new BasicEnemy(id);
        };
    }
}

