package tictactoe;

import java.util.Random;

public class EasyAIPlayer extends Player {
    private final Random random = new Random();

    public EasyAIPlayer(char symbol) {
        super(symbol);
    }

    @Override
    public void makeMove(Board board) {
        System.out.println("Making move level \"easy\"");
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!board.isCellEmpty(row, col));

        board.makeMove(row, col, symbol);
    }
}
