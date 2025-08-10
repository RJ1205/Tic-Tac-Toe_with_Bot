package tictactoe;

public class Board {
    private final char[][] board = new char[3][3];

    public Board() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public boolean isCellEmpty(int row, int col) {
        return board[row][col] == ' ';
    }

    public boolean isInRange(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3;
    }

    public void makeMove(int row, int col, char player) {
        board[row][col] = player;
    }

    public String gameState() {
        if (checkWin('X')) return "X wins";
        if (checkWin('O')) return "O wins";
        if (hasEmptyCells()) return "Game not finished";
        return "Draw";
    }

    private boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player)
                || (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    private boolean hasEmptyCells() {
        for (char[] r : board) {
            for (char c : r) {
                if (c == ' ') return true;
            }
        }
        return false;
    }
}
