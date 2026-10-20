package view;

import controller.GameController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import patterns.state.PlayingState;

public class MainApp extends Application {
    private GameController controller = new GameController();
    private Label scoreLabel = new Label("Score: 0");
    private Label hpLabel = new Label("PV: 100");

    @Override
    public void start(Stage primaryStage) {
        controller.init();

        BorderPane root = new BorderPane();
        HBox menu = new HBox(10);
        menu.setPadding(new Insets(10));
        Button playBtn = new Button("Play");
        Button pauseBtn = new Button("Pause");
        Button exitBtn = new Button("Exit");
        menu.getChildren().addAll(playBtn, pauseBtn, exitBtn);

        GridPane grid = new GridPane();
        grid.setPrefSize(600, 400);
        grid.setStyle("-fx-background-color: lightgreen; -fx-grid-lines-visible: true;");
        for (int r=0;r<10;r++) for (int c=0;c<15;c++) grid.add(new Region(){ { setPrefSize(40,40); } }, c, r);

        HBox hud = new HBox(20, scoreLabel, hpLabel);
        hud.setPadding(new Insets(10));

        root.setTop(menu);
        root.setCenter(grid);
        root.setBottom(hud);

        playBtn.setOnAction(e -> controller.getStateManager().setState(new PlayingState()));
        pauseBtn.setOnAction(e -> controller.getStateManager().setState(new patterns.state.PauseState()));
        exitBtn.setOnAction(e -> controller.exit());

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Tower Defense - Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}

