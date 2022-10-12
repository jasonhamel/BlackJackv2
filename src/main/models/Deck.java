package src.main.models;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;

    public Deck() {
        this.deck = new ArrayList<Card>();
    }
    
    public Card getCard(int index) {
        return new Card(this.deck.get(index));
    }
    public void setCard(int index, Card card) {
        this.deck.set(index, new Card(card));
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < deck.size(); i++) {
            Card card = getCard(i);
            temp += "Card: " + (i + 1) + " is the " + card;
        }
        return temp;
    }

    public void addCard(Card card) {
        this.deck.add(new Card(card));
    }

    public Card deal() {
        int cardToDeal = (int)(Math.random() * deck.size());
        Card cardToReturn = deck.get(cardToDeal);
        removeFromDeck(cardToReturn);
        return cardToReturn;
    }

    public boolean removeFromDeck(Card card) {
        deck.remove(card);
        return deck.size() == 4;
    }
}
