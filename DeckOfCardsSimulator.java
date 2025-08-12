import java.util.*;

class Card {
    private String suit;
    private String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

class Deck {
    private List<Card> cards = new ArrayList<>();
    private final String[] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
    private final String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

    public Deck() {
        initializeDeck();
    }

    public final void initializeDeck() {
        cards.clear();
        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void drawCard() {
        if (cards.isEmpty()) {
            System.out.println("Deck is empty! No cards left to draw.");
            return;
        }
        Random rand = new Random();
        Card drawnCard = cards.remove(rand.nextInt(cards.size()));
        System.out.println("You drew: " + drawnCard);
    }


    public void shuffle() {
        Collections.shuffle(cards);
        System.out.println("Deck shuffled!");
    }
    public void sortDeck() {
        cards.sort(Comparator
                .comparing(Card::getSuit)
                .thenComparing(Card::getRank));
        System.out.println("Deck sorted!");
    }

    public void printDeck() {
        if (cards.isEmpty()) {
            System.out.println("No cards in the deck.");
            return;
        }
        for (Card card : cards) {
            System.out.println(card);
        }
    }

    public void resetDeck() {
        initializeDeck();
        System.out.println("Deck reset to original state!");
    }
}

public class DeckOfCardsSimulator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Deck deck = new Deck();

        while (true) {
            System.out.println("\n--- Deck of Cards Menu ---");
            System.out.println("1. Draw a card");
            System.out.println("2. Shuffle deck");
            System.out.println("3. Sort deck");
            System.out.println("4. Print deck");
            System.out.println("5. Reset deck");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> deck.drawCard();
                case 2 -> deck.shuffle();
                case 3 -> deck.sortDeck();
                case 4 -> deck.printDeck();
                case 5 -> deck.resetDeck();
                case 6 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
