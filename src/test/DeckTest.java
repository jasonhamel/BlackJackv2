package src.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import src.main.models.Card;
import src.main.models.Deck;

public class DeckTest {
    Deck deck;

    @Before
    public void setup() {
        deck = new Deck();
        deck.addCard(new Card("Spades", "Ace"));
        deck.addCard(new Card("Clubs", "2"));
        deck.addCard(new Card("Hearts", "10"));
        deck.addCard(new Card("Diamonds", "King"));
    }

    @Test
    public void testDeal() {
        Card card1 = deck.deal();
        Assert.assertTrue("Spades".equals(card1.getSuit(card1)) || "Clubs".equals(card1.getSuit(card1)) ||
        "Hearts".equals(card1.getSuit(card1)) || "Diamonds".equals(card1.getSuit(card1)));
        Assert.assertTrue("Ace".equals(card1.getValue(card1)) || "2".equals(card1.getValue(card1)) ||
        "10".equals(card1.getValue(card1)) || "King".equals(card1.getValue(card1)));
    }

    @Test
    public void testRemoveFromDeck() {
        Card card1 = deck.deal();
        Assert.assertFalse(deck.removeFromDeck(card1));
    }
}