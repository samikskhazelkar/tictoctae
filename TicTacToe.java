import java.util.Scanner;

public class TicTacToe {
    private static final int BOARD_SIZE = 3;
    private static final char EMPTY = '-';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = PLAYER_X;

        // Initialize the board with empty cells
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    public void play() {
        boolean gameOver = false;
        int moves = 0;

        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            printBoard();

            // Get the current player's move
            System.out.print("Player " + currentPlayer + ", enter your move (row [0-2] column [0-2]): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            // Make the move if it's valid
            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer;
                moves++;

                // Check if the current player wins
                if (hasWon(currentPlayer, row, col)) {
                    printBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameOver = true;
                }
                // Check if it's a draw
                else if (moves == BOARD_SIZE * BOARD_SIZE) {
                    printBoard();
                    System.out.println("It's a draw!");
                    gameOver = true;
                }
                // Switch to the next player
                else {
                    currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        scanner.close();
    }

    private void printBoard() {
        System.out.println("---------");
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println("---------");
    }

    private boolean isValidMove(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            return false;
        }
        return board[row][col] == EMPTY;
    }

    private boolean hasWon(char player, int lastRow, int lastCol) {
        // Check row
        boolean win = true;
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (board[lastRow][col] != player) {
                win = false;
                break;
            }
        }
        if (win) {
            return true;
        }

        // Check column
        win = true;
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (board[row][lastCol] != player) {
                win = false;
                break;
            }
        }
        if (win) {
            return true;
        }

        // Check diagonal
        if (lastRow == lastCol) {
            win = true;
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (board[i][i] != player) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return true;
            }
        }

        // Check anti-diagonal
        if (lastRow + lastCol == BOARD_SIZE - 1) {
            win = true;
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (board[i][BOARD_SIZE - 1 - i] != player) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.play();
    }
}
