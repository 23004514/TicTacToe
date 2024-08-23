import java.util.Scanner;

public class TicTacToe{
    
    public char[][] board;
    public char currentPlayer;

    public TicTacToe(){
        
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
        
    }

    
    public void initializeBoard(){
        
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                
                board[i][j] = '-';
                
            }
        }
        
    }

    
    public void printBoard(){
        
        System.out.println("-------------");
        
        for(int i = 0; i < 3; i++){
            
            System.out.print("| ");
            
            for(int j = 0; j < 3; j++){
                
                System.out.print(board[i][j] + " | ");
                
            }
            
            System.out.println();
            System.out.println("-------------");
            
        }
    }

    
    public void playGame(){
        
        boolean gameWon = false;
        boolean gameDraw = false;

        while(!gameWon && !gameDraw){
            printBoard();
            playerMove();
            gameWon = checkWinner();
            if(!gameWon){
                
                gameDraw = checkDraw();
                
                if(!gameDraw){
                    
                    changePlayer();
                    
                }
            }
        }

        printBoard();
        if(gameWon){
            
            System.out.println("Congradulations the Player " + currentPlayer + " wins the game");
            
        } 
        else if(gameDraw){
            
            System.out.println("End of the game: DRAW");
            
        }
    }

     
    public void playerMove(){
        
        Scanner scanner = new Scanner(System.in);
        int row, col;

        while(true){
            
            System.out.println("Player " + currentPlayer + ", enter your move (row [1-3] and column [1-3]): ");
            
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;

            if(row >= 0 && col >= 0 && row < 3 && col < 3 && board[row][col] == '-'){
                board[row][col] = currentPlayer;
                break;
            } else {
                System.out.println("invalid move, try again ");
            }
        }
        
    }

    
    public boolean checkWinner(){
        
        for(int i = 0; i < 3; i++){
            
            if((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)){
                    
                return true;
                
            }
            
        }

        
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
            (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
                
            return true;
            
        }

        return false;
        
    }

    
    public boolean checkDraw(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (board[i][j] == '-'){
                    
                    return false;
                    
                }
            }
        }
        
        return true;
        
    }

    
    public void changePlayer(){
        
        if (currentPlayer == 'X'){
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
        
    }

    public static void main(String[]args){
        
        TicTacToe game = new TicTacToe();
        
        game.playGame();
        
    }
    
}