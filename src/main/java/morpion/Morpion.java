package morpion;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Morpion  extends Application {

    private static GridPane gameBoard;
    private static Plateau board;
    private AnimationTimer gameTimer;
    private MenuBar menuBar;
    private Menu gameMenu;
    private MenuItem newGameOption;
    private BorderPane root;

    public final static class Tuile extends Button {

        private final int row;
        private final int col;
        private Symbole mark;

        public Tuile(int initRow, int initCol, Symbole initMark)
        {
            row = initRow;
            col = initCol;
            mark = initMark;
            initialiseTuile();
        }

        private void initialiseTuile() {
            this.setOnMouseClicked(e -> {
                if (!board.isCrossTurn()) {
                    board.placeSymbole(this.row, this.col);
                    this.update();
                }
            });
            this.setStyle("-fx-font-style: 70");
            this.setTextAlignment(TextAlignment.CENTER);
            this.setMinSize(150.0, 150.0);
            this.setText("" + this.mark);
        }

        public void update() {
            this.mark = board.getMarkAt(this.row, this.col);
            this.setText("" + mark);
        }
    }

    public void start(Stage stage) throws Exception {

    }
}
