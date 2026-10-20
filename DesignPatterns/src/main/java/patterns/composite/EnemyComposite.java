package patterns.composite;

import java.util.ArrayList;
import java.util.List;
import util.GameLogger;

public class EnemyComposite implements EnemyComponent {
    private final List<EnemyComponent> children = new ArrayList<>();
    private final String name;

    public EnemyComposite(String name) {
        this.name = name;
    }

    public void add(EnemyComponent c) { children.add(c); }
    public void remove(EnemyComponent c) { children.remove(c); }

    @Override
    public void spawn() {
        GameLogger.getInstance().log("WAVE", "Spawning group " + name);
        for (EnemyComponent c : children) c.spawn();
    }

    @Override
    public int count() {
        return children.stream().mapToInt(EnemyComponent::count).sum();
    }
}

