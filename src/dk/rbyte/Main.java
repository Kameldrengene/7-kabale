package dk.rbyte;

import dk.rbyte.objects.Card;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
	// write your code here
        View view = new View();
        view.startGame();
        /*
        HashMap<Integer, ArrayList<Card>> finSpaces = new HashMap<>();
        finSpaces.put(0, new ArrayList<Card>());
        finSpaces.get(0).add(new Card(0, 1));
        finSpaces.put(1, new ArrayList<Card>());
        finSpaces.get(1).add(new Card(1, 7));
        finSpaces.put(2, new ArrayList<Card>());
        finSpaces.get(2).add(new Card(2, 10));
        finSpaces.put(3, new ArrayList<Card>());
        finSpaces.get(3).add(new Card(3, 12));
        view.getGame().getGameBoard().setFinSpaces(finSpaces);
        view.printTopLine();*/
    }
}
