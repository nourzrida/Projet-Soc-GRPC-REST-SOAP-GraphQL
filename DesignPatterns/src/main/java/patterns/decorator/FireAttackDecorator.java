package patterns.decorator;

import util.GameLogger;

public class FireAttackDecorator extends TowerDecorator {
    private final int extraDamage = 5;

    public FireAttackDecorator(Tower t) {
        super(t);
        GameLogger.getInstance().log("DECORATOR", "Ajout FireAttack à " + t.getName());
    }

    @Override
    public void attack() {
        // effet feu ajouté
        super.attack();
    }

    @Override
    public int getDamage() {
        return super.getDamage() + extraDamage;
    }
}

