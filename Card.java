/**
 * this module creates a
 * CS2100
 * Carly Wohlrab
 */

public class Card {
    public enum Color {
        RED, GREEN, PURPLE;
    }

    private Color color;

    public enum Number {
        ONE, TWO, THREE;
    }

    private Number number;

    public enum Fill {
        OUTLINE, SOLID, HATCHED;
    }

    private Fill fill;

    public enum Shape {
        DIAMOND, OVAL, SQUIGGLE;
    }

    private Shape shape;

    /**
     * constructor to create an instance of the card class
     * 
     * @param color  is the color of the shape
     * @param number is the number of shapes on the card
     * @param shape  is the type of shape on the card
     * @param fill   is the type of fill of the shape
     */
    public Card(Number number, Color color, Shape shape, Fill fill) {
        this.color = color;
        this.number = number;
        this.shape = shape;
        this.fill = fill;
    }

    /**
     * getNumber() gets the number of shapes on the card
     * 
     * @return the number of shapes on the card
     */
    public Number getNumber() {
        return number;
    }

    /**
     * getShape() gets the shape on the card
     * 
     * @return the shape on the card
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * getFill() gets the type of fill of the shape
     * 
     * @return the fill of the shape
     */
    public Fill getFill() {
        return fill;
    }

    /**
     * getColor() gets color of the shape
     * 
     * @return the color of the shape
     */
    public Color getColor() {
        return color;
    }

    /**
     * toString() to properly format variables
     * 
     * @return formatted output
     */
    @Override
    public String toString() {
        return String.format("%d_%s_%s_%s", number.ordinal() + 1, color, shape, fill);
    }

    /**
     * isSet(c1, c2, c3) determines whether three cards form a set by determining if
     * they are all equal or all different
     * 
     * @param c1 is the first card
     * @param c2 is the second card
     * @param c3 is the third card
     */
    public static boolean isSet(Card c1, Card c2, Card c3) {
        boolean color_same = c1.color == c2.color && c2.color == c3.color;
        boolean color_diff = c1.color != c2.color && c2.color != c3.color && c1.color != c3.color;
        boolean number_same = c1.number == c2.number && c2.number == c3.number;
        // use logical AND (&&) for boolean comparisons
        boolean number_diff = c1.number != c2.number && c2.number != c3.number && c1.number != c3.number;
        boolean shape_same = c1.shape == c2.shape && c2.shape == c3.shape;
        boolean shape_diff = c1.shape != c2.shape && c2.shape != c3.shape && c1.shape != c3.shape;
        boolean fill_same = c1.fill == c2.fill && c2.fill == c3.fill;
        // last comparison should be != (all different)
        boolean fill_diff = c1.fill != c2.fill && c2.fill != c3.fill && c1.fill != c3.fill;

        if ((color_same || color_diff) && (number_same || number_diff) && (shape_same || shape_diff)
                && (fill_same || fill_diff))
            return true;
        else
            return false;

    }
}
