package dk.rbyte;

import dk.rbyte.objects.GameBoard;
import dk.rbyte.objects.Pile;

import java.util.ArrayList;

public class GameController {
    private GameBoard gameBoard;
    private int score;
    private int turns;

    public GameController(){
        gameBoard = new GameBoard();
        score = 0;
        turns = 0;
    }

    public boolean executeCommand(String command) throws Exception {
        String[] exe = command.split(" ");
        switch (exe[0]){
            case "move": case "m":
                if (exe.length == 3){
                    String x = exe[1];
                    String y = exe[2];
                    if (gameBoard.move(x, y)){ //will be false if a king from a king move is tried to be moved
                        score += 5;
                    }
                    turns += 1;
                    return true;
                } else if (exe.length == 4) {
                    String x = exe[1];
                    String y = exe[2];
                    String n = exe[3];
                    gameBoard.move(x, y, n);
                    turns += 1;
                    return true;
                }
                break;
            case "draw": case "d":
                if (gameBoard.draw()) turns += 1;
                return true;
            case "score": case "s":
                if (exe.length == 2) {
                    if (gameBoard.score(exe[1])){
                        score += 10;
                    }
                    turns += 1;
                    return true;
                }
                break;
            case "return": case "r":
                if (exe.length == 3){
                    if (gameBoard.remove(exe[1], exe[2])){
                        score -= 10;
                    }
                    turns += 1;
                    return true;
                }
                break;
        }

        throw new Exception("command not found");
    }



    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public int getScore() {
        return score;
    }

    public int getTurns() {
        return turns;
    }
}
