package src.main.models;

public class Card {
    
    private String suit;
    private String value;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    public Card(Card source) {
        this.suit = source.suit;
        this.value = source.value;
    }

    public String toString() {
        return this.value + " of " + this.suit + "\n";
    }
    
    public void setSuit(String suit) {
        this.suit = suit;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public String getSuit(Card card) {
        return this.suit;
    }
    public String getValue(Card card) {
        return this.value;
    }

    public int valueOfCard(Card card) {
        String value = getValue(card);
        switch (value) {
            case "Ace": return 11;
            case "2": return 2;
            case "3": return 3;
            case "4": return 4;
            case "5": return 5;
            case "6": return 6;
            case "7": return 7;
            case "8": return 8;
            case "9": return 9;
            case "10": case "Jack": case "Queen": case "King": return 10;
            default: return 404;
        }
    }
}
