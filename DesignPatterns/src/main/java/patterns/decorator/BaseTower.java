package patterns.decorator;

public class BaseTower implements Tower {
    private final String name;
    private int damage = 10;
    private double range = 100;

    public BaseTower(String name) {
        this.name = name;
    }

    @Override
    public void attack() {
        util.GameLogger.getInstance().log("TOWER", name + " attaque avec " + damage + " dégâts à " + range + " unités");
    }

    @Override
    public int getDamage() { return damage; }

    @Override
    public double getRange() { return range; }

    @Override
    public String getName() { return name; }
}
