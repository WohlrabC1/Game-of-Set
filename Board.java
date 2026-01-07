import java.util.ArrayList;

public class Board {
    private ArrayList<ArrayList<BoardSquare>> board;

    public Board(Deck deck) {
        board = new ArrayList<>();
        for (int row = 0; row < 3; row++) {
            board.add(new ArrayList<BoardSquare>());
            for (int col = 0; col < 4; col++) {
                Card c = deck.getTopCard();
                BoardSquare square = new BoardSquare(c, row, col);
                board.get(row).add(square);
            }
        }
    }

    /**
     * replaceCard() replaces a card in the board with a new one
     * 
     * @param c   is the card replacing the old one
     * @param row is the row the card is going to
     * @param col is the column the card is going to
     */
    public void replaceCard(Card c, int row, int col) {
        board.get(row).set(col, new BoardSquare(c, row, col));
    }

    /**
     * getBoardSquare() finds a specific board square
     * 
     * @return is the board square that is found
     */
    public BoardSquare getBoardSquare(int row, int col) {
        return board.get(row).get(col);
    }

    /**
     * add3(deck deck) adds 3 new cards to the right side of the board
     * 
     * @param Deck deck is the deck of cards used for the game
     */
    public void add3(Deck deck) {
        // Add exactly three cards total. Place at most one card per row,
        // iterating rows until we've added three cards. This prevents
        // adding an entire extra column for each iteration.
        int added = 0;
        for (int row = 0; row < board.size() && added < 3; row++) {
            Card c = deck.getTopCard();
            int colInd = board.get(row).size();

            BoardSquare square = new BoardSquare(c, row, colInd);
            board.get(row).add(square);
            added++;
        }
    }

    /**
     * getCard() returns the card at a certain board Square
     * 
     * @return is the card at that board square
     */
    public Card getCard(int row, int col) {
        return board.get(row).get(col).getCard();
    }

    /**
     * numRows() returns the number of rows in the board
     * 
     * @return the number of rows in the board
     */
    public int numRows() {
        return board.size();
    }

    /**
     * numInRows() finds the number of squares in a row
     * 
     * @return the number of squares in a row
     */
    public int numInRows() {
        return board.get(0).size();
    }

    /**
     * getRow() finds the specific row
     * 
     * @returns the whole row from the board
     */
    public ArrayList<BoardSquare> getRow(int row) {
        return board.get(row);
    }

    /**
     * totalCards() calculates the number of cards in the board
     * 
     * @return the number of cards on the board
     */
    public int totalCards() {
        int total = 0;
        for (ArrayList<BoardSquare> row : board) {
            total += row.size();
        }
        return total;
    }

    /**
     * toString() to properly format variables
     * 
     * @return formatted output
     */
    @Override
    public String toString() {
        String s = "";
        for (ArrayList<BoardSquare> row : board) {
            for (BoardSquare b : row) {

                s += b.getCard();
                // for (
                s += "  ";
            }
            s += "\n";
        }
        return s;
    }
}
