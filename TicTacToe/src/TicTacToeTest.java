public class TicTacToeTest {
    public static void main(String[] args) {
        // Moves: X(1,1), O(1,2), X(2,1), O(2,2), X(3,1) -> X wins first column
        String moves = "1 1 1 2 2 1 2 2 3 1";
        TicTacToe game = new TicTacToe();
        java.util.Scanner scanner = new java.util.Scanner(moves);
        game.playGame(scanner);
        scanner.close();

        if (!game.checkWinner()) {
            throw new AssertionError("Expected a winner but none found");
        }
        if (game.getCurrentPlayer() != 'X') {
            throw new AssertionError("Expected winner X but got " + game.getCurrentPlayer());
        }
        System.out.println("TicTacToeTest: passed");
    }
}
