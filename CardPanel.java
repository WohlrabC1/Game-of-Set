import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The CardPanel class represents an individual card on the
 * game board within the Game of Set GUI. Each CardPanel
 * displays an image of a Card, tracks whether it is selected,
 * and responds to mouse clicks by communicating with the Game
 * and MainPanel.
 *
 * When clicked, the CardPanel toggles its selection state,
 * adds or removes itself from the game's selected cards, and triggers
 * a visual update. If three cards are selected, the game checks whether
 * they form a valid set.
 *
 * Each panel knows its position (row and column) on the board and
 * draws a blue overlay when selected.
 */
public class CardPanel extends JPanel implements MouseListener {
    private static final long serialVersionUID = 1L;

    /** The card image displayed in this panel. */
    private transient BufferedImage image;
    private transient Game game;

    /** Whether this card is currently selected. */
    private boolean selected;

    /** The color of the selection highlight (blue). */
    private Color shadowColor;

    /** Padding between the image edge and the panel border. */
    private int spacing;

    /** The row index of this card on the board. */
    private int row;

    /** The column index of this card on the board. */
    private int col;

    /** Reference to the containing {@link MainPanel}. */
    private MainPanel containing;

    /**
     * Constructs a new CardPanel for a specific position on the board.
     *
     * @param g       the Game instance managing game logic
     * @param r       the row index of this card
     * @param c       the column index of this card
     * @param image   the BufferedImage to display for this card
     * @param spacing the space (in pixels) between the image and panel edge
     * @param panel   the MainPanel that contains this CardPanel
     */
    public CardPanel(int r, int c, BufferedImage image, int spacing, MainPanel panel, Game game) {

        row = r;
        col = c;
        containing = panel;
        shadowColor = Color.BLUE;
        this.image = image;
        selected = false;
        this.spacing = spacing;
        this.game = game;
        // Add the mouse listener after construction to avoid leaking "this" during
        // construction
        SwingUtilities.invokeLater(() -> addMouseListener(this));
    }

    /**
     * Marks this card as selected, enabling its highlight overlay.
     */
    public void select() {
        selected = true;
        repaint();
    }

    /**
     * Deselects this card, removing its highlight overlay.
     */
    public void deselect() {
        selected = false;
        repaint();
    }

    /**
     * Paints the card image and a blue highlight if the card is selected.
     * The highlight is drawn first as a filled rectangle, followed by
     * the scaled card image centered with uniform padding.
     *
     * @param g the Graphics context used for drawing
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (selected) {
            g.setColor(shadowColor);
            g.fillRect(0, 0, getPreferredSize().width, getPreferredSize().height);
        }
        if (image != null) {
            g.drawImage(image,
                    spacing / 2,
                    spacing / 2,
                    getPreferredSize().width - spacing,
                    getPreferredSize().height - spacing,
                    this);
        }
    }

    /**
     * Handles mouse click events for selecting or deselecting this card.
     * -If the card is already selected, it is deselected and removed from the
     * selection list.
     * -If the card is unselected and not null, it is added to the game’s selection
     * list.
     * -If three cards are selected, the game checks whether they form a valid set
     * and redraws the grid.
     *
     * @param e the MouseEvent triggered when the card is clicked
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        selected = !selected;
        if (selected) {
            game.addToSelected(row, col);
            System.out.println("selected: " + game.numSelected());

            if (game.numSelected() == 3) {
                game.testSelected();
                // After the game updates its selected list (and may have
                // replaced cards if it's a set), redraw the whole grid so
                // CardPanels are recreated with the correct selection state.
                // This clears highlights both for the 'set' and 'not a set'
                // cases without introducing new fields or methods.
                containing.drawGrid();
                System.out.println("selected: " + game.numSelected());

            }
        }

        if (!selected) {
            game.removeSelected(row, col);
            repaint();
        }

        revalidate();
        repaint();

    }

    /**
     * Unused mouse listener method. Required by MouseListener.
     *
     * @param e the mouse event
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Unused mouse listener method. Required by MouseListener.
     *
     * @param e the mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Unused mouse listener method. Required by MouseListener.
     *
     * @param e the mouse event
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Unused mouse listener method. Required by MouseListener.
     *
     * @param e the mouse event
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

}
