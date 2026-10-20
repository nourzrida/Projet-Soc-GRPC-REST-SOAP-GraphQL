package view;

import controller.GameController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import util.GameLogger;

public class HUD {
    private final HBox node;
    private final Label scoreLabel;
    private final Label hpLabel;
    private final GameController controller;

    public HUD(GameController controller) {
        this.controller = controller;
        scoreLabel = new Label("Score: " + controller.getScore());
        hpLabel = new Label("PV: " + controller.getHp());
        node = new HBox(20, scoreLabel, hpLabel);
        node.setPadding(new Insets(10));
        GameLogger.getInstance().log("HUD", "HUD initialisé");
    }

    public HBox getNode() { return node; }

    public void refresh() {
        scoreLabel.setText("Score: " + controller.getScore());
        hpLabel.setText("PV: " + controller.getHp());
    }
}

