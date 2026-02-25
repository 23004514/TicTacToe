import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe{
    private static final int SIZE = 3;
    private static final char EMPTY = '-';

    private char[][] board;
    private char currentPlayer;

    public TicTacToe(){
        
        board = new char[SIZE][SIZE];
        currentPlayer = 'X';
        initializeBoard();
        
    }

    
    public void initializeBoard(){
        
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
        
    }

    
    public void printBoard(){
        
        String sep = "+---+---+---+";
        System.out.println(sep);
        for (int i = 0; i < SIZE; i++) {
            System.out.print("|");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + board[i][j] + " |");
            }
            System.out.println();
            System.out.println(sep);
        }
    }

    
    public void playGame(Scanner scanner){
        
        boolean gameWon = false;
        boolean gameDraw = false;

        while (!gameWon && !gameDraw) {
            printBoard();
            playerMove(scanner);
            gameWon = checkWinner();
            if (!gameWon) {
                gameDraw = checkDraw();
                if (!gameDraw) {
                    changePlayer();
                }
            }
        }

        printBoard();
        if (gameWon) {
            System.out.println("Congratulations! Player " + currentPlayer + " wins the game.");
        } else if (gameDraw) {
            System.out.println("End of the game: DRAW");
        }
    }

     
    public void playerMove(Scanner scanner) {
        int row, col;

        while (true) {
            System.out.println("Player " + currentPlayer + ", enter your move (row [1-" + SIZE + "] and column [1-" + SIZE + "]): ");
            try {
                row = scanner.nextInt() - 1;
                col = scanner.nextInt() - 1;

                if (row >= 0 && col >= 0 && row < SIZE && col < SIZE && board[row][col] == EMPTY) {
                    board[row][col] = currentPlayer;
                    break;
                } else {
                    System.out.println("Invalid move or cell occupied, try again.");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Invalid input. Please enter two numbers.");
                scanner.next(); // discard invalid token
            }
        }
    }

    
    public boolean checkWinner(){
        
        for (int i = 0; i < SIZE; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer)
                    || (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }

        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer)
                || (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }

        return false;
        
    }

    
    public boolean checkDraw(){
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                if (board[i][j] == EMPTY){
                    return false;
                }
            }
        }

        return true;

    }

    
    private void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Attempt to place the current player's mark at (row,col). Returns true on success.
    public boolean makeMove(int row, int col) {
        if (row >= 0 && col >= 0 && row < SIZE && col < SIZE && board[row][col] == EMPTY) {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    // Advance to next player (exposed for UI)
    public void switchPlayer() {
        changePlayer();
    }

    // Reset board for a new game
    public void reset() {
        initializeBoard();
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        Scanner scanner = null;
        boolean fromArgs = args != null && args.length > 0;
        try {
            if (fromArgs) {
                scanner = new Scanner(String.join(" ", args));
            } else {
                scanner = new Scanner(System.in);
            }
            TicTacToe game = new TicTacToe();
            game.playGame(scanner);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    // Accessor for tests/demos
    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public char[][] getBoard() {
        return board;
    }
    
}