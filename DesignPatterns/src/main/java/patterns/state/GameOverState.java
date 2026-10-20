package patterns.state;

import controller.GameController;

public class GameOverState implements GameState {
    @Override
    public void enter(GameController controller) {
        controller.gameOver();
    }

    @Override
    public String name() {
        return "GAME_OVER";
    }
}

