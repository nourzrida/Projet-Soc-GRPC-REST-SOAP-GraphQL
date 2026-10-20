package patterns.decorator;

import util.GameLogger;

public class RangeBoostDecorator extends TowerDecorator {
    private final double boost = 1.5;

    public RangeBoostDecorator(Tower t) {
        super(t);
        GameLogger.getInstance().log("DECORATOR", "Ajout RangeBoost à " + t.getName());
    }

    @Override
    public double getRange() {
        return super.getRange() * boost;
    }
}

