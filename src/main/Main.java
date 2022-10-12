package src.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import src.main.models.Deck;
import src.main.models.Card;

public class Main {
    static final String FILE_STRING = "src/main/cards.txt";
    static Deck deck = new Deck();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int playerScore = 0;
        int dealerScore = 0;
        int hiddenDealerScore = 0;
        Card hiddenCard = new Card("Ace", "Bullshit");
        int[] aceCheck = {0, 0};
        try {
            importCards();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Welcome to Black Jack.");
        System.out.println("You and the dealer will each be dealt a card face up.");
        System.out.println("You will then receive a second card face up.");
        System.out.println("The dealer will receive a second card face down.");
        System.out.println("The goal is to get as close to 21 as possible with going over.");
        System.out.print("Press enter/return to begin dealing...");
        scan.nextLine();
        for (int i = 0; i < 4; i++) {
            Card card = deal(i, aceCheck);
            int value = card.valueOfCard(card);
            if (i % 2 == 0) {
                System.out.print("\n\tYou draw the " + card);
                playerScore += value;
                System.out.print("...");
                scan.nextLine();
            } else if (i == 3) {
                hiddenCard = card;
                System.out.print("\n\tThe dealer places their second card face down.");
                dealerScore += value;
                System.out.print("\n...");
                scan.nextLine();
            } else {
                System.out.print("\n\tThe dealer draws the " + card);
                dealerScore += value;
                hiddenDealerScore = dealerScore;
                System.out.print("...");
                scan.nextLine();
            }
        }
        System.out.print("You have a " + playerScore + ". The dealer has a " + hiddenDealerScore);
        while (true) {
            System.out.print("\nWould you like to hit? Press y/n...");
            String response = scan.nextLine();
            if (response.equals("y")) {
                while (true) {
                    Card card = deal(0, aceCheck);
                    playerScore += card.valueOfCard(card);
                    if (playerScore > 21) {
                        if (aceCheck[0] > 0) {
                            playerScore = playerScore - 10;
                            System.out.print("\nYou went over 21, but had an ace! Flipped the ace to be worth 1 instead of 11. New score is " + playerScore);
                            aceCheck[0]--;
                            continue;
                        }
                        System.out.print("\n\tYou draw the " + card);
                        System.out.print(playerScore + ". You bust. Try again");
                        System.exit(0);
                    } else {
                        System.out.print("\n\tYou draw the " + card);
                        System.out.print("\nYour score is " + playerScore + ". The dealer has " + hiddenDealerScore);
                    }
                    break;
                }
            } else if (response.equals("n")) {
                break;
            } else {
                System.out.print("Invalid input, try again :)");
            }
        }
        System.out.print("\n\nIt's now the dealer's turn.");
        System.out.print("\nThe dealer flips their other card. It's the " + hiddenCard);
        System.out.print("They now have " + dealerScore);
        System.out.print("\nPress enter/return to continue...");
        scan.nextLine();
        if (dealerScore > playerScore) {
            System.out.print("\nThe dealer beats your " + playerScore + " with a " + dealerScore);
            System.out.print("\nBetter luck next time.");
            System.exit(0);
        }
        while (true) {
            if (aceCheck[1] > 0) {
                dealerScore = dealerScore - 10;
                System.out.print("\nThe dealer went over 21, but had an ace! Flipped the ace to be worth 1 instead of 11. New score is " + dealerScore);
                aceCheck[1]--;
                continue;
            }
            if (dealerScore > 22) {
                System.out.print("\nThe dealer has gone bust. You win!\n\n");
                break;
            } else if (dealerScore < playerScore) {
                System.out.print("\nThe dealer will draw another card. Press enter/return key...");
                scan.nextLine();
                Card card = deal(1, aceCheck);
                dealerScore += card.valueOfCard(card);
                System.out.print("\n\tThe Dealer has drawn the " + card);
                System.out.print("\nThe dealer now has " + dealerScore);
                if (dealerScore > playerScore && dealerScore <= 21) {
                    System.out.print("\nThe dealer beats your " + playerScore + " with a " + dealerScore);
                    System.out.print("\nBetter luck next time.\n\n");
                    break;
                }
            } else if (dealerScore == playerScore) {
                System.out.print("\nI'm sorry to say you've lost. A tie goes to the dealer.\n\n");
                System.exit(0);
            }
        }
        scan.close();
    }

    public static void importCards() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(FILE_STRING);
        Scanner scan = new Scanner(fis).useDelimiter(",");
        while (scan.hasNext()) {
            String[] cards = scan.nextLine().split(",");
            deck.addCard(new Card(cards[0], cards[1]));
        }
        scan.close();
    }

    public static Card deal(int player, int[] aceCheck) {
        Card card = deck.deal();
        if (card.getValue(card).equals("Ace")) {
            if (player % 2 == 0) {
                aceCheck[0]++;
            } else {
                aceCheck[1]++;
            }
        }
        return card;
    }
}