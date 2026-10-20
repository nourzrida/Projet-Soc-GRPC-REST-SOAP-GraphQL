package controller;

import patterns.state.*;
import util.GameLogger;
import javafx.application.Platform;
import patterns.composite.*;
import patterns.factory.EnemyFactory;
import model.Enemy;

public class GameController {
    private final GameStateManager stateManager;
    private int score = 0;
    private int hp = 100;
    // boucle de jeu
    private GameLoop loop;

    public GameController() {
        this.stateManager = new GameStateManager(this);
    }

    public void init() {
        stateManager.setState(new MenuState());
    }

    public void showMenu() {
        // UI handled in MainApp
    }

    public void startGameLoop() {
        GameLogger.getInstance().log("GAME", "Démarrage du jeu");
        // exemple de création de vague
        EnemyComposite wave = new EnemyComposite("Wave1");
        wave.add(new EnemyLeaf(EnemyFactory.create("basic", "E1")));
        wave.add(new EnemyLeaf(EnemyFactory.create("fast", "E2")));
        wave.spawn();
        // démarrer la boucle si nécessaire
        if (loop == null) loop = new GameLoop(this);
        loop.start();
    }

    public void pauseGame() {
        GameLogger.getInstance().log("GAME", "Jeu en pause");
        if (loop != null) {
            loop.stop();
        }
    }

    public void gameOver() {
        GameLogger.getInstance().log("GAME", "Game Over");
        if (loop != null) {
            loop.stop();
            loop = null;
        }
    }

    public GameStateManager getStateManager() { return stateManager; }

    public void exit() {
        Platform.exit();
    }

    // --- Nouveaux éléments: état simple du jeu (score / hp) et tick() pour la boucle ---
    public int getScore() { return score; }
    public int getHp() { return hp; }

    public void addScore(int s) {
        score += s;
        GameLogger.getInstance().log("GAME", "Score += " + s + " -> " + score);
    }

    public void reduceHp(int d) {
        hp = Math.max(0, hp - d);
        GameLogger.getInstance().log("GAME", "HP -= " + d + " -> " + hp);
        if (hp <= 0) {
            getStateManager().setState(new GameOverState());
        }
    }

    // méthode appelée périodiquement par la boucle de jeu
    public void tick() {
        // logique minimale de test: petite variation aléatoire
        // éviter import java.util.Random pour simplicité ; on décrémente HP si score multiple
        GameLogger.getInstance().log("TICK", "Tick effectué");
        // exemple: si score multiple de 50, réduire un peu de HP
        if (score > 0 && score % 50 == 0) {
            reduceHp(1);
        }
    }
}
