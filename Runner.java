public class Runner {
    public static void main(String[] args) {
        Board board = new Board();
        ConnectFour game = new ConnectFour(board);
        game.setPlayer1(new HumanPlayer('R', board, "Roy"));
        // game.setPlayer2(new HumanPlayer('B', board, "Bob"));
        game.setPlayer2(new AIPlayer('A', board, "AI"));
        game.playGame();
    }
}
