package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Input command: ");
            String[] parts = scanner.nextLine().trim().split("\\s+");

            if (parts.length == 0) {
                continue;
            }

            String command = parts[0];

            switch (command) {
                case "start" -> {
                    if (parts.length != 3) {
                        System.out.println("Bad parameters!");
                        continue;
                    }

                    Player playerX = createPlayer(parts[1], 'X');
                    Player playerO = createPlayer(parts[2], 'O');

                    if (playerX == null || playerO == null) {
                        System.out.println("Bad parameters!");
                        continue;
                    }

                    runGame(playerX, playerO);
                }
                case "exit" -> {
                    return;
                }
                default -> System.out.println("Bad parameters!");
            }
        }
    }

    private static Player createPlayer(String type, char symbol) {
        return switch (type) {
            case "user" -> new HumanPlayer(symbol);
            case "easy" -> new EasyAIPlayer(symbol);
            case "medium" -> new MediumAIPlayer(symbol);
            case "hard" -> new HardAIPlayer(symbol);
            default -> null;
        };
    }



    private static void runGame(Player playerX, Player playerO) {
        Board board = new Board();
        board.printBoard();

        Player current = playerX;

        while (true) {
            current.makeMove(board);
            board.printBoard();

            String state = board.gameState();
            if (!state.equals("Game not finished")) {
                System.out.println(state);
                break;
            }

            current = (current == playerX) ? playerO : playerX;
        }
    }
}
