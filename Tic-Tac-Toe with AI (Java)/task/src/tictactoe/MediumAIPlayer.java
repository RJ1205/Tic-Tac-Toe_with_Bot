package tictactoe;

import java.util.Random;

public class MediumAIPlayer implements Player {
    private final Random random = new Random();
    private final char symbol;

    public MediumAIPlayer(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void makeMove(Board board) {
        System.out.println("Making move level \"medium\"");

        int[] winMove = findWinningMove(board, symbol);
        if (winMove != null) {
            board.makeMove(winMove[0], winMove[1], symbol);
            return;
        }

        char opponent = (symbol == 'X') ? 'O' : 'X';
        int[] blockMove = findWinningMove(board, opponent);
        if (blockMove != null) {
            board.makeMove(blockMove[0], blockMove[1], symbol);
            return;
        }

        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!board.isCellEmpty(row, col));

        board.makeMove(row, col, symbol);
    }

    private int[] findWinningMove(Board board, char player) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isCellEmpty(i, j)) {
                    board.makeMove(i, j, player);
                    boolean win = board.gameState().equals(player + " wins");
                    board.makeMove(i, j, ' '); // zurücksetzen
                    if (win) {
                        return new int[]{i, j};
                    }
                }
            }
        }
        return null;
    }
}
