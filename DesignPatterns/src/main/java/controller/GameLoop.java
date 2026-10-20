package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import util.GameLogger;

public class GameLoop {
    private final GameController controller;
    private Timeline timeline;

    public GameLoop(GameController controller) {
        this.controller = controller;
    }

    public void start() {
        GameLogger.getInstance().log("GAME", "GameLoop starting");
        timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> tick()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void tick() {
        controller.tick();
    }

    public void stop() {
        if (timeline != null) {
            timeline.stop();
            timeline = null;
            GameLogger.getInstance().log("GAME", "GameLoop stopped");
        }
    }
}

