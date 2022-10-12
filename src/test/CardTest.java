package src.test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import src.main.models.Card;

public class CardTest {

    Card card;
    Card card2;

    @Before
    public void setup() {
        card = new Card("Clubs", "5");
        card2 = new Card("Diamonds", "King");
    }

    @Test
    public void testValueOfCard() {
        Assert.assertEquals(5, card.valueOfCard(card));
        Assert.assertEquals(10, card2.valueOfCard(card2));
    }

    @Test
    public void testSuitOfCard() {
        Assert.assertEquals("Clubs", card.getSuit(card));
    }
}