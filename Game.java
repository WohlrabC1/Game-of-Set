/*
 * this module creates a game class that
 * Carly Wohlrab
 * CS2100
 */

import java.util.ArrayList;

public class Game {
    ArrayList<BoardSquare> selected = new ArrayList<>();
    private Board board;
    private Deck deck;

    public Game() {
        deck = new Deck();
        deck.shuffle();
        board = new Board(deck);
    }

    /**
     * addToSelected() finds a card and adds it to the array list
     * 
     * @param row is the row the card is in
     * @param col is the column the card is in
     */
    public void addToSelected(int row, int col) {
        boolean alreadyPicked = false;
        BoardSquare b = board.getBoardSquare(row, col);
        for (BoardSquare s : selected) {
            if (s.getRow() == row && s.getColumn() == col) {
                alreadyPicked = true;
            }
        }
        if (!alreadyPicked && selected.size() < 3) {
            selected.add(b);
        }
    }

    /**
     * getSelected() finds the array list of cards and returns it
     * 
     * @return the array list of cards
     */
    public ArrayList<BoardSquare> getSelected() {
        return selected;
    }

    /**
     * numSelected() finds the number of cards that are selected
     * 
     * @return the number of cards selected
     */
    public int numSelected() {
        return selected.size();
    }

    /** testSelected() tests the cards in the array list to find a set. */
    public boolean testSelected() {
        // Defensive: ensure there are exactly 3 selected squares before testing
        if (selected.size() < 3) {
            return false;
        }

        Card c1 = selected.get(0).getCard();
        Card c2 = selected.get(1).getCard();
        Card c3 = selected.get(2).getCard();

        boolean set = Card.isSet(c1, c2, c3);

        if (set) {
            for (BoardSquare b : selected) {
                Card c4 = deck.getTopCard();
                board.replaceCard(c4, b.getRow(), b.getColumn());
            }
        }

        selected.clear();
        return set;
    }

    /**
     * removeSelected() removes a card from the array list
     * 
     * @param row is the row the card is in
     * @param col is the column the card is in
     */
    public void removeSelected(int row, int col) {
        // Iterate backwards to avoid skipping elements when removing
        for (int i = selected.size() - 1; i >= 0; i--) {
            BoardSquare s = selected.get(i);
            if (s.getRow() == row && s.getColumn() == col) {
                selected.remove(i);
            }
        }
    }

    /** add3() adds 3 cards to the right side of the board */
    public void add3() {
        // Delegate to Board's add3 implementation which adds three cards to the right
        board.add3(deck);
    }

    /**
     * outOfCards() determines when the deck is out of cards
     * 
     * @return if the deck is empty
     */
    public boolean outOfCards() {
        return deck.isEmpty();
    }

    /**
     * getBoard() finds and returns the board.
     * 
     * @return the game board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * getDeck() finds and returns the deck.
     * 
     * @return the game deck
     */
    public Deck getDeck() {
        return deck;
    }

}
