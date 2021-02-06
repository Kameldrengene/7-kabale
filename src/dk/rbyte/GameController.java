package dk.rbyte;

import dk.rbyte.objects.GameBoard;
import dk.rbyte.objects.Pile;

import java.util.ArrayList;

public class GameController {
    private GameBoard gameBoard;

    public GameController(){
        gameBoard = new GameBoard();
    }

    public boolean executeCommand(String command) throws Exception {
        String[] exe = command.split(" ");
        switch (exe[0]){
            case "move":
                if (exe.length == 3){
                    String x = exe[1];
                    String y = exe[2];
                    gameBoard.move(x, y);
                    return true;
                } else if (exe.length == 4) {
                    String x = exe[1];
                    String y = exe[2];
                    String n = exe[3];
                    gameBoard.move(x, y, n);
                    return true;
                }
                break;
            case "draw": case "d":
                gameBoard.draw();
                return true;
            case "score":
                if (exe.length == 2) {
                    gameBoard.score(exe[1]);
                    return true;
                }
                break;
            case "return":
                if (exe.length == 3){
                    gameBoard.remove(exe[1], exe[2]);
                    return true;
                }
                break;
        }

        throw new Exception("command not found");
    }



    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
