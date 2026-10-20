package patterns.state;

import controller.GameController;

public interface GameState {
    void enter(GameController controller);
    String name();
}

