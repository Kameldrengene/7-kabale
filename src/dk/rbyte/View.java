package dk.rbyte;

import dk.rbyte.objects.Card;
import dk.rbyte.objects.Pile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class View {
    private GameController game;

    private String top         = "┌%s┐";
    private String line        = "──";
    private String topContinue = "├%s┤";
    private String mid         = "│%s│";
    private String side        = "│";
    private String end         = "└%s┘";
    private String empty       = "      ";

    private HashMap<Integer, String> valueMap;
    private HashMap<Integer, String> typeMap;


    public View(){
        game = new GameController();

        valueMap = new HashMap<>();
        valueMap.put(1, "A");
        valueMap.put(2, "2");
        valueMap.put(3, "3");
        valueMap.put(4, "4");
        valueMap.put(5, "5");
        valueMap.put(6, "6");
        valueMap.put(7, "7");
        valueMap.put(8, "8");
        valueMap.put(9, "9");
        valueMap.put(10, "10");
        valueMap.put(11, "J");
        valueMap.put(12, "Q");
        valueMap.put(13, "K");

        typeMap = new HashMap<>();
        typeMap.put(0, "♠");
        typeMap.put(1, "♥");
        typeMap.put(2, "♣");
        typeMap.put(3, "♦");
    }

    public void startGame(){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String msg = "" + game.getGameBoard().isWon();
        help();
        while (true){
            printCurrent();
            System.out.println("message: " + msg);
            msg = "";

            System.out.println(String.format("Score: %d   turns: %d", game.getScore(), game.getTurns()));

            System.out.println("Write instruction!");
            try {
                String command = in.readLine();
                if (command.equals("help")) help();
                else game.executeCommand(command);
            } catch (Exception e){
                msg = e.getMessage();
            }

        }
    }

    public void help(){
        System.out.println("Commands");
        System.out.println("'help'       displays this msg");
        System.out.println("'move x y'   moves all cards from pos x to pos y (x [q,0-6], y [0-6])");
        System.out.println("'move x y n' moves the n first cards from pos x to pos y (x [q,0-6], y [0-6], n [1-13])");
        System.out.println("'score x'    moves the first card from pos x to correct end spot (x [q,0-6])");
        System.out.println("'return k y' moves the first card from pile k to pos x (k [a-d], y [0-6])");
        System.out.println("'draw' / 'd' draws 1 card to pos q/resets deck if no cards available");
    }
    
    public void printCurrent(){
        printTopLine();
        printBottomLine();
    }
    
    public void printTopLine(){
        HashMap<Integer, ArrayList<Card>> finSpaces = game.getGameBoard().getFinSpaces();
        ArrayList<Card> deck = game.getGameBoard().getDeck();
        int deckPointer = game.getGameBoard().getDeckPointer();

        String startLine = "_________________________________________________\n" +
                "  a♠     b♥     c♣     d♦   " + empty + "   q     deck\n";
        String spaceLine = "│    │ │    │ │    │ │    │ " + empty + " │    │ │    │ \n";
        String out = startLine;
        String s;

        Card spade;
        Card heart;
        Card clover;
        Card ruby;

        if (finSpaces.get(0).size() != 0) spade = finSpaces.get(0).get(finSpaces.get(0).size()-1);
        else spade = new Card(0, -1);
        if (finSpaces.get(1).size() != 0) heart = finSpaces.get(1).get(finSpaces.get(1).size()-1);
        else heart = new Card(1, -1);
        if (finSpaces.get(2).size() != 0) clover = finSpaces.get(2).get(finSpaces.get(2).size()-1);
        else clover = new Card(2, -1);
        if (finSpaces.get(3).size() != 0) ruby = finSpaces.get(3).get(finSpaces.get(3).size()-1);
        else ruby  = new Card(3, -1);

        ArrayList<Card> shownFinCards = new ArrayList<>();
        shownFinCards.add(spade);
        shownFinCards.add(heart);
        shownFinCards.add(clover);
        shownFinCards.add(ruby);

        //First line

        for (Card card: shownFinCards) {
            if (card.getValue() == -1) s = line + line;
            else if (card.getValue() == 10) s = typeMap.get(card.getType()) + "10" + typeMap.get(card.getType());
            else s = valueMap.get(card.getValue()) + typeMap.get(card.getType()) + line;
            out += String.format(top, s) + " ";
        }

        out += empty + " ";

        if (deckPointer == -1) s = line + line;
        else {
            Card card = deck.get(deckPointer);
            if (card.getValue() == 10) s = typeMap.get(card.getType()) + "10" + typeMap.get(card.getType());
            else s = valueMap.get(card.getValue()) + typeMap.get(card.getType()) + line;
        }

        out += String.format(top, s) + " ";

        s = line + line;
        out += String.format(top, s) + " \n";


        //second line
        out += spaceLine;


        //third line
        for (Card card: shownFinCards) {
            if (card.getValue() == -1) s = "    ";
            else if (card.getValue() == 10) s = typeMap.get(card.getType()) + "10" + typeMap.get(card.getType());
            else s = " " + valueMap.get(card.getValue()) + typeMap.get(card.getType()) + " ";
            out += String.format(mid, s) + " ";
        }

        out += empty + " ";

        if (deckPointer == -1) s = "    ";
        else {
            Card card = deck.get(deckPointer);
            if (card.getValue() == 10) s = typeMap.get(card.getType()) + "10" + typeMap.get(card.getType());
            else s = " " + valueMap.get(card.getValue()) + typeMap.get(card.getType()) + " ";
        }

        out += String.format(mid, s) + " ";

        String cardsInDeck = String.valueOf(deck.size() - (deckPointer + 1));
        if (cardsInDeck.length() == 2) s = " " + cardsInDeck + " ";
        else s =  "  " + cardsInDeck + " ";

        out += String.format(mid, s) + " \n";


        //fourth line
        out += spaceLine;


        //Last line

        for (Card card: shownFinCards) {
            if (card.getValue() == -1) s = line + line;
            else if (card.getValue() == 10) s = typeMap.get(card.getType()) + "10" + typeMap.get(card.getType());
            else s = line + valueMap.get(card.getValue()) + typeMap.get(card.getType());
            out += String.format(end, s) + " ";
        }

        out += empty + " ";

        if (deckPointer == -1) s = line + line;
        else {
            Card card = deck.get(deckPointer);
            if (card.getValue() == 10) s = typeMap.get(card.getType()) + "10" + typeMap.get(card.getType());
            else s = line + valueMap.get(card.getValue()) + typeMap.get(card.getType());
        }

        out += String.format(end, s) + " ";

        s = line + line;
        out += String.format(end, s) + " \n";


        System.out.println(out);
    }
    
    public void printBottomLine(){
        String startLine = "   0      1      2      3      4      5      6  \n";
        ArrayList<ArrayList<String>> collected = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            ArrayList<String> prints = new ArrayList<>();
            collected.add(prints);
            Pile curr = game.getGameBoard().getSpaces().get(i);
            for (Card card : curr.getHiddenCards()){
                String used;
                if (prints.size() == 0) used = top;
                else used = topContinue;
                prints.add(String.format(used, line+line));
            }
            Card lastCard = null;
            for (Card card : curr.getShownCards()){
                lastCard = card;
                String used;
                if (prints.size() == 0) used = top;
                else used = topContinue;
                if (card.getValue() == 10) prints.add(String.format(used, typeMap.get(card.getType()) + valueMap.get(card.getValue()) + typeMap.get(card.getType())));
                else prints.add(String.format(used, valueMap.get(card.getValue()) + typeMap.get(card.getType()) +line));
            }
            prints.add(String.format(mid, "    "));
            if (lastCard == null) {
                for (int j = 0; j < 5; j++) {
                    prints.add(empty);
                }
            } else {

                if (lastCard.getValue() == 10)
                    prints.add(String.format(mid, typeMap.get(lastCard.getType()) + valueMap.get(lastCard.getValue()) + typeMap.get(lastCard.getType())));
                else
                    prints.add(String.format(mid, " " + valueMap.get(lastCard.getValue()) + typeMap.get(lastCard.getType()) + " "));

                prints.add(String.format(mid, "    "));

                if (lastCard.getValue() == 10)
                    prints.add(String.format(end, typeMap.get(lastCard.getType()) + valueMap.get(lastCard.getValue()) + typeMap.get(lastCard.getType())));
                else
                    prints.add(String.format(end, line + valueMap.get(lastCard.getValue()) + typeMap.get(lastCard.getType())));
            }
        }

        int x = 0;
        for (ArrayList a: collected){
            if (a.size() > x) x = a.size();
        }

        String out = startLine;
        int i = 0;
        while (i < x){
            for (ArrayList a: collected){
                if (i < a.size()){
                    out += a.get(i);
                } else {
                    out += empty;
                }
                out += " ";
            }
            out += "\n";

            i++;
        }

        System.out.println(out);
    }

    public GameController getGame() { //TESTING
        return game;
    }
}
