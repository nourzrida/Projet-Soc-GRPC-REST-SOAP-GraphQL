package patterns.state;

import controller.GameController;
import util.GameLogger;

public class GameStateManager {
    private GameState current;
    private final GameController controller;

    public GameStateManager(GameController controller) {
        this.controller = controller;
    }

    public void setState(GameState state) {
        this.current = state;
        if (state != null) {
            GameLogger.getInstance().log("STATE", "Changement d'état -> " + state.name());
            state.enter(controller);
        }
    }

    public GameState getState() {
        return current;
    }
}

