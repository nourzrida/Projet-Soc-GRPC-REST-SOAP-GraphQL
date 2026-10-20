package model;

import patterns.state.EnemyState;
import util.GameLogger;

public class Enemy {
    private String id;
    private int hp;
    private double speedMultiplier = 1.0;
    private boolean alive = true;
    private EnemyState state;

    public Enemy(String id, int hp) {
        this.id = id;
        this.hp = hp;
    }

    public void setState(EnemyState newState) {
        if (newState == null || newState.equals(this.state)) return;
        this.state = newState;
        newState.apply(this);
        GameLogger.getInstance().log("ENEMY_STATE", "Enemy " + id + " -> " + newState.name());
    }

    public void damage(int d) {
        if (d <= 0 || !alive) return;
        hp -= d;
        if (hp <= 0) setState(new patterns.state.DeadState());
    }

    public void setSpeedMultiplier(double s) { this.speedMultiplier = s; }
    public void setAlive(boolean a) { this.alive = a; }

    public String getId() { return id; }
    public int getHp() { return hp; }
    public boolean isAlive() { return alive; }
    public double getSpeedMultiplier() { return speedMultiplier; }
    public EnemyState getState() { return state; }
    public String getStateName() { return state != null ? state.name() : "NONE"; }
}
