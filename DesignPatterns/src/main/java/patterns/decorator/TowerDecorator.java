package patterns.decorator;

public abstract class TowerDecorator implements Tower {
    protected final Tower wrapped;

    protected TowerDecorator(Tower t) { this.wrapped = t; }

    @Override
    public void attack() { wrapped.attack(); }

    @Override
    public int getDamage() { return wrapped.getDamage(); }

    @Override
    public double getRange() { return wrapped.getRange(); }

    @Override
    public String getName() { return wrapped.getName(); }
}

