package tictactoe;

public class HardAIPlayer implements Player {
    private final char symbol;

    public HardAIPlayer(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void makeMove(Board board) {
        System.out.println("Making move level \"hard\"");

        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = null;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isCellEmpty(i, j)) {
                    board.makeMove(i, j, symbol);
                    int score = minimax(board, false);
                    board.makeMove(i, j, ' '); // zurücksetzen

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new int[]{i, j};
                    }
                }
            }
        }

        if (bestMove != null) {
            board.makeMove(bestMove[0], bestMove[1], symbol);
        }
    }

    private int minimax(Board board, boolean isMaximizing) {
        String state = board.gameState();
        char opponent = (symbol == 'X') ? 'O' : 'X';

        if (state.equals(symbol + " wins")) return 10;
        if (state.equals(opponent + " wins")) return -10;
        if (state.equals("Draw")) return 0;

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isCellEmpty(i, j)) {
                        board.makeMove(i, j, symbol);
                        int score = minimax(board, false);
                        board.makeMove(i, j, ' ');
                        bestScore = Math.max(bestScore, score);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            char opponentSymbol = (symbol == 'X') ? 'O' : 'X';
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isCellEmpty(i, j)) {
                        board.makeMove(i, j, opponentSymbol);
                        int score = minimax(board, true);
                        board.makeMove(i, j, ' ');
                        bestScore = Math.min(bestScore, score);
                    }
                }
            }
            return bestScore;
        }
    }
}
