package morpion;

import ai.MiniMax;
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

    public static void main(String[] args) { launch(args); }


    public void start(Stage primaryStage) throws Exception {
        root = new BorderPane();

        root.setCenter(generateGUI());
        root.setTop(initialiseMenu());

        Scene scene = new Scene(root);
        primaryStage.setTitle("Morpion");
        primaryStage.setScene(scene);

        runGameLoop();

        primaryStage.show();
    }

    private static GridPane generateGUI() {
        gameBoard = new GridPane();
        board = new Plateau();
        gameBoard.setAlignment(Pos.CENTER);

        for(int row = 0; row < board.getWidth(); row++){
            for(int col = 0; col < board.getWidth(); col ++){
                Tuile tuile = new Tuile(row,col, board.getMarkAt(row,col));
                GridPane.setConstraints(tuile, col, row);
                gameBoard.getChildren().add(tuile);
            }
        }
        return gameBoard;
    }

    private MenuBar initialiseMenu() {
        menuBar = new MenuBar();
        gameMenu = new Menu("game");
        newGameOption = new MenuItem("New Game");

        gameMenu.getItems().add(newGameOption);
        menuBar.getMenus().add(gameMenu);
        newGameOption.setOnAction(e -> {
            resetGame();
        });
        return menuBar;
    }

    private void runGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (board.isGameOver()) {
                    endGame();
                } else {
                    if (board.isCrossTurn()){
                        playAI();
                    }
                }
            }
        };
        gameTimer.start();
    }

    private static void playAI(){
        int[] move = MiniMax.getBestMove(board);
        int row = move[0];
        int col = move[1];
        board.placeSymbole(row, col);
        for (Node child : gameBoard.getChildren()) {
            if (GridPane.getRowIndex(child) == row
                    && GridPane.getColumnIndex(child) == col) {
                Tuile t = (Tuile) child;
                t.update();
                return;
            }
        }
    }

    private void resetGame() {
        root.setCenter(generateGUI());
        runGameLoop();
    }

    private void endGame() {
        gameTimer.stop();
        Alert gameOverAlert = new Alert(AlertType.INFORMATION, "", new ButtonType("New Game"));
        Symbole winner = board.getWinningMark();

        gameOverAlert.setTitle("Game Over");
        gameOverAlert.setHeaderText(null);
        if (winner == Symbole.BLANK) {
            gameOverAlert.setContentText("Draw!");
        } else {
            gameOverAlert.setContentText(winner + " victoire!");
        }
        gameOverAlert.setOnHidden(e -> {
            gameOverAlert.close();
            resetGame();
        });
        gameOverAlert.show();
    }
}
