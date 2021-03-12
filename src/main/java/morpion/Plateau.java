package morpion;

import static morpion.Symbole.*;

public class Plateau {

    private final Symbole[][] board;
    private Symbole winningMark;
    private final int BOARD_WITH = 3;
    private boolean crossTurn, gameOver;
    private int availableMoves = BOARD_WITH * BOARD_WITH;

    public Plateau(Symbole[][] board, Symbole[][] board1) {
        this.board = new Symbole[BOARD_WITH][BOARD_WITH];
        crossTurn = true;
        gameOver = false;
        winningMark = BLANK;
        initialiseBoard();
    }

    private void initialiseBoard()
    {
        for (int row = 0; row < BOARD_WITH; row++) {
            for (int col = 0; col < BOARD_WITH; col++){
                board[row][col] = BLANK;
            }
        }
    }

    public boolean placeSymbole(int row, int col){
        if(row < 0 || row >= BOARD_WITH || col < 0 || col >= BOARD_WITH || isTileMarked(row, col) || gameOver) {
            return false;
        }
        availableMoves--;
        board[row][col] = crossTurn ? X : O;
        togglePlayer();
        checkWin(row,col);
        return true;
    }

    private void checkWin(int row, int col) {
        int rowSum = 0;
        // Check row for winner.
        for(int i = 0; i< BOARD_WITH; i++){
            rowSum += getMarkAt(row,i).getSymbole();
        }
        if(calcWinner(rowSum) != BLANK){
            System.out.println(winningMark + "à gagner sur la ligne " + row);
            return;
        }

        //Check col for winner
        rowSum = 0;
        for(int r = 0; r < BOARD_WITH; r++){
            rowSum += getMarkAt(r,col).getSymbole();
        }
        if(calcWinner(rowSum) != BLANK){
            System.out.println(winningMark + "à gagner sur la col " + col);
            return;
        }

        // Top-Left to bottom-right diagonal
        rowSum = 0;
        for (int i = 0; i < BOARD_WITH; i++) {
            rowSum += getMarkAt(i, i).getSymbole();
        }
        if (calcWinner(rowSum) != BLANK) {
            System.out.println(winningMark + " wins on the top-left to "
                    + "bottom-right diagonal");
            return;
        }

        // Top-right to bottom-left diagonal
        rowSum = 0;
        int indexMax = BOARD_WITH - 1;
        for(int i =0; i <= indexMax; i++){
            rowSum += getMarkAt(i, indexMax - i).getSymbole();
        }
        if(calcWinner(rowSum) != BLANK) {
            System.out.println(winningMark + " wins on the top-right to "
                    + "bottom-left diagonal.");
            return;
        }

        if(!anyMovesAvailable()){
            gameOver = true;
            System.out.println("Egalité");
        }
    }

    private Symbole calcWinner(int rowSum) {
        int Xwin = X.getSymbole() * BOARD_WITH;
        int Owin = O.getSymbole() * BOARD_WITH;
        if (rowSum == Xwin) {
            gameOver = true;
            winningMark = X;
            return X;
        } else if (rowSum == Owin) {
            gameOver = true;
            winningMark = O;
            return O;
        }
        return BLANK;
    }

    public Symbole getMarkAt(int row, int column) {
        return board[row][column];
    }


    public boolean isTileMarked(int row, int column) {
        return board[row][column].isMarked();
    }

    public void setMarkAt(int row, int column, Symbole newMark) {
        board[row][column] = newMark;
    }

    @Override
    public String toString() {
        StringBuilder strBldr = new StringBuilder();
        for (Symbole[] row : board) {
            for (Symbole tile : row) {
                strBldr.append(tile).append(' ');
            }
            strBldr.append("\n");
        }
        return strBldr.toString();
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean anyMovesAvailable() {
        return availableMoves > 0;
    }


    private void togglePlayer() {
        crossTurn = !crossTurn;
    }

    public boolean isCrossTurn() {
        return crossTurn;
    }

    public int getWidth() {
        return BOARD_WITH;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Symbole getWinningMark(){
        return winningMark;
    }

}
