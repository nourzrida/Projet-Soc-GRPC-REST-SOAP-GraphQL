package patterns.state;

import controller.GameController;

public class PlayingState implements GameState {
    @Override
    public void enter(GameController controller) {
        controller.startGameLoop();
    }

    @Override
    public String name() {
        return "PLAYING";
    }
}

