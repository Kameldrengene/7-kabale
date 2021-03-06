package dk.rbyte.objects;

public class Card {
    private int     type;       //0: spar; 1: Hjerter; 2: Klør; 3: Ruder;
    private int     value;

    public Card(int type, int value){
        this.type = type;
        this.value = value;
    }

    public boolean isRed(){
        return 1 == this.type % 2;
    }

    public boolean isBlack(){
        return 0 == this.type % 2;
    }

    public boolean isDifferent(Card newCard){
        return newCard.getType() % 2 != this.type % 2;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
