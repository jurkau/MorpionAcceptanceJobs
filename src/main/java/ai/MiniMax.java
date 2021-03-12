package ai;

import javafx.scene.Scene;
import morpion.Plateau;
import morpion.Symbole;

import javax.swing.plaf.synth.SynthLabelUI;

import static morpion.Symbole.*;

public class MiniMax {

    private static final int MAX_DEPTH = 12;

    private MiniMax() {

    }

    public static int miniMax(Plateau board, int depth, int alpha, int beta, boolean isMax) {

        int boardVal = evaluateBoard(board, depth);

        if(Math.abs(boardVal) > 0 || depth == 0
        || !board.anyMovesAvailable()) {
            return boardVal;
        }
        // Maximising player, find the maximum attainable value.
        if (isMax) {
            int highestVal = Integer.MIN_VALUE;
            for (int row = 0; row < board.getWidth(); row++) {
                for (int col = 0; col < board.getWidth(); col++) {
                    if (!board.isTileMarked(row, col)) {
                        board.setMarkAt(row, col, X);
                        highestVal = Math.max(highestVal, miniMax(board,
                                depth - 1, alpha, beta, false));
                        board.setMarkAt(row, col, BLANK);
                        alpha = Math.max(alpha, highestVal);
                        if (alpha >= beta) {
                            return highestVal;
                        }
                    }
                }
            }
            return highestVal;
            // Minimising player, find the minimum attainable value;
        } else {
            int lowestVal = Integer.MAX_VALUE;
            for (int row = 0; row < board.getWidth(); row++) {
                for (int col = 0; col < board.getWidth(); col++) {
                    if (!board.isTileMarked(row, col)) {
                        board.setMarkAt(row, col, O);
                        lowestVal = Math.min(lowestVal, miniMax(board,
                                depth - 1, alpha, beta, true));
                        board.setMarkAt(row, col, BLANK);
                        beta = Math.min(beta, lowestVal);
                        if (beta <= alpha) {
                            return lowestVal;
                        }
                    }
                }
            }
            return lowestVal;
        }
    }

    public static int[] getBestMove(Plateau board){
        int[] bestMove = new int[]{-1,-1};
        int bestValue  = Integer.MIN_VALUE;

        for (int row = 0; row < board.getWidth(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                if (!board.isTileMarked(row, col)) {
                    board.setMarkAt(row, col, X);
                    int moveValue = miniMax(board, MAX_DEPTH, Integer.MIN_VALUE,
                            Integer.MAX_VALUE, false);
                    board.setMarkAt(row, col, BLANK);
                    if (moveValue > bestValue) {
                        bestMove[0] = row;
                        bestMove[1] = col;
                        bestValue = moveValue;
                    }
                }
            }
        }
        return bestMove;
    }

    private static int evaluateBoard(Plateau board, int depth) {
        int rowSum = 0;
        int bWidth  = board.getWidth();
        int Xwin = X.getSymbole() * bWidth;
        int Owin = O.getSymbole() * bWidth;

        // Check rows for winner
        for (int row = 0; row < bWidth; row++) {
            for (int col = 0; col < bWidth; col++) {
                rowSum += board.getMarkAt(row, col).getSymbole();
            }
            if (rowSum == Xwin) {
                return 10 + depth;
            } else if (rowSum == Owin) {
                return -10 - depth;
            }
            rowSum = 0;
        }

        // Check colums for winner
        rowSum = 0;
        for (int col = 0; col < bWidth; col++) {
            for (int row = 0; row < bWidth; row++) {
                rowSum += board.getMarkAt(row, col).getSymbole();
            }
            if (rowSum == Xwin) {
                return 10 + depth;
            } else if (rowSum == Owin) {
                return -10 - depth;
            }
            rowSum = 0;
        }

        // Check diagonal for winner.
        // Top-left to bottom rigut diagonal
        rowSum = 0;
        for (int i = 0; i < bWidth; i++) {
            rowSum += board.getMarkAt(i, i).getSymbole();
        }
        if (rowSum == Xwin) {
            return 10 + depth;
        } else if (rowSum == Owin) {
            return -10 - depth;
        }

        // Top-right to bottom-left diagonal.
        rowSum = 0;
        int indexMax = bWidth - 1;
        for (int i = 0; i <= indexMax; i++) {
            rowSum += board.getMarkAt(i, indexMax - i).getSymbole();
        }
        if (rowSum == Xwin) {
            return 10 + depth;
        } else if (rowSum == Owin) {
            return -10 - depth;
        }

        return 0;

    }
}
