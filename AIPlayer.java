import java.util.Random;
public class AIPlayer extends Player {

    public AIPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);
    }

    public void makeMove(Board board) {
        if (board.checkHorizontal(symbol)) {
            int aiWin = board.winHoriztonal(symbol);
            board.putMove(aiWin, symbol);
        } else if (board.checkVertical(symbol)) {
            int aiWin1 = board.winVertical(symbol);
            board.putMove(aiWin1, symbol);
        } else if (board.checkDiagonalOne(symbol)) {            // first checks if there is a win, if not, checks if there is block,
            int aiWin2 = board.winDiagonalOne(symbol);          // if not, then places a random move. continues this until game is over.
            board.putMove(aiWin2, symbol);
        } else if (board.checkDiagonalTwo(symbol)) {            // if there is a win, will make wiining move, if there is a block, will
            int aiWin3 = board.winDiagonalTwo(symbol);          // make block, otherwise will make a random move.
            board.putMove(aiWin3, symbol);
        } else if (board.checkHorizontalBlock(symbol)) {
            int aiBlock = board.blockHorizontal(symbol);
            board.putMove(aiBlock, symbol);
        } else if (board.checkVerticalBlock(symbol)) {
            int aiBlock1 = board.blockVertical(symbol);
            board.putMove(aiBlock1, symbol);
        } else if (board.checkDiagonalOneBlock(symbol)) {
            int aiBlock2 = board.blockDiagonalOne(symbol);
            board.putMove(aiBlock2, symbol);
        } else if (board.checkDiagonalTwoBlock(symbol)) {
            int aiBlock3 = board.blockDiagonalTwo(symbol);
            board.putMove(aiBlock3, symbol);
        } else {
            int positionRandom = (int) ((Math.random() * (7 - 1)) + 1);
            while (board.columnFullCheck(positionRandom)) {
                positionRandom = (int) ((Math.random() * (7 - 1)) + 1);
            }
            board.putMove(positionRandom, symbol);
        }
    }
    

}
