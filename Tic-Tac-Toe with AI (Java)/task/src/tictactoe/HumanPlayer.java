package tictactoe;

import java.util.Scanner;

public class HumanPlayer extends Player {
    private final Scanner scanner = new Scanner(System.in);

    public HumanPlayer(char symbol) {
        super(symbol);
    }

    @Override
    public void makeMove(Board board) {
        while (true) {
            System.out.print("Enter the coordinates: ");
            String input = scanner.nextLine();
            String[] parts = input.trim().split("\\s+");

            if (parts.length != 2 || !parts[0].matches("\\d+") || !parts[1].matches("\\d+")) {
                System.out.println("You should enter numbers!");
                continue;
            }

            int row = Integer.parseInt(parts[0]) - 1;
            int col = Integer.parseInt(parts[1]) - 1;

            if (!board.isInRange(row, col)) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (!board.isCellEmpty(row, col)) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            board.makeMove(row, col, symbol);
            break;
        }
    }
}
