import java.util.Scanner; 

public class HumanPlayer extends Player {

    public HumanPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);
    }
    
    public void makeMove(Board board) {
        Scanner sc = new Scanner(System.in);
        System.out.print(name + ", please input your move: ");
        int humanMove = sc.nextInt();                               // takes the user input and stores it 

        while (board.columnFullCheck(humanMove)) {
            System.out.print("Invalid input, please input a valid move: ");
            humanMove = sc.nextInt();                                           // if the column is full, asks for another input
        }

        board.putMove(humanMove, symbol);           // place the move on the board

    }

    
}
