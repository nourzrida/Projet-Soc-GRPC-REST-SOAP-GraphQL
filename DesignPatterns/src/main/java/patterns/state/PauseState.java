package patterns.state;

import controller.GameController;

public class PauseState implements GameState {
    @Override
    public void enter(GameController controller) {
        controller.pauseGame();
    }

    @Override
    public String name() {
        return "PAUSE";
    }
}

