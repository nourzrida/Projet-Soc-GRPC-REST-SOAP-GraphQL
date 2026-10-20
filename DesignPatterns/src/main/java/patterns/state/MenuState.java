package patterns.state;

import controller.GameController;

public class MenuState implements GameState {
    @Override
    public void enter(GameController controller) {
        controller.showMenu();
    }

    @Override
    public String name() {
        return "MENU";
    }
}

