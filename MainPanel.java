import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
// Random was previously used for image selection; no longer needed.

/**
 * MainPanel - single clean implementation (restored from original provided
 * content)
 */
public class MainPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JButton exitButton;
    private JButton addThreeButton;
    private int cardWidth;
    private int cardHeight;
    private int cardSpacing;
    private static final int MAX_CARDS_DISPLAYED = 18;
    private transient final Game game = new Game();

    public MainPanel() {
        cardWidth = 80;
        cardHeight = 120;
        cardSpacing = 10;

        exitButton = new JButton("EXIT");
        exitButton.setPreferredSize(new Dimension(80, 40));
        exitButton.setBounds(230, 600, 100, 40);
        exitButton.setFont(new Font("Serif", Font.BOLD, 20));

        // Register listeners after construction to avoid "this" escaping during
        // initialization
        SwingUtilities.invokeLater(() -> exitButton.addActionListener(this));

        addThreeButton = new JButton("ADD 3");
        addThreeButton.setPreferredSize(new Dimension(80, 40));
        addThreeButton.setBounds(420, 600, 150, 80);
        addThreeButton.setFont(new Font("Serif", Font.BOLD, 20));
        SwingUtilities.invokeLater(() -> addThreeButton.addActionListener(this));

        setPreferredSize(new Dimension(750, 550));
        drawGrid();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Serif", Font.BOLD, 40));
        String text = "Cards Remaining: " + game.getDeck().cardsRemaining();
        g2.drawString(text, 150, 450);
    }

    public static final String[] IMAGE_SOURCES = new String[] {
            "1_GREEN_DIAMOND_HATCHED.png",
            "1_GREEN_DIAMOND_OUTLINE.png",
            "1_GREEN_DIAMOND_SOLID.png",
            "1_GREEN_OVAL_HATCHED.png",
            "1_GREEN_OVAL_OUTLINE.png",
            "1_GREEN_OVAL_SOLID.png",
            "1_GREEN_SQUIGGLE_HATCHED.png",
            "1_GREEN_SQUIGGLE_OUTLINE.png",
            "1_GREEN_SQUIGGLE_SOLID.png",
            "1_PRUPLE_DIAMOND_HATCHED.png",
            "1_PURPLE_DIAMOND_OUTLINE.png",
            "1_PURPLE_DIAMOND_SOLID.png",
            "1_PURPLE_OVAL_HATCHED.png",
            "1_PURPLE_OVAL_OUTLINE.png",
            "1_PURPLE_OVAL_SOLID.png",
            "1_PURPLE_SQUIGGLE_HATCHED.png",
            "1_PURPLE_SQUIGGLE_OUTLINE.png",
            "1_PURPLE_SQUIGGLE_SOLID.png",
            "1_RED_DIAMOND_HATCHED.png",
            "1_RED_DIAMOND_OUTLINE.png",
            "1_RED_DIAMOND_SOLID.png",
            "1_RED_OVAL_HATCHED.png",
            "1_RED_OVAL_OUTLINE.png",
            "1_RED_OVAL_SOLID.png",
            "1_RED_SQUIGGLE_HATCHED.png",
            "1_RED_SQUIGGLE_OUTLINE.png",
            "1_RED_SQUIGGLE_SOLID.png",
            "2_GREEN_DIAMOND_HATCHED.png",
            "2_GREEN_DIAMOND_OUTLINE.png",
            "2_GREEN_DIAMOND_SOLID.png",
            "2_GREEN_OVAL_HATCHED.png",
            "2_GREEN_OVAL_OUTLINE.png",
            "2_GREEN_OVAL_SOLID.png",
            "2_GREEN_SQUIGGLE_HATCHED.png",
            "2_GREEN_SQUIGGLE_OUTLINE.png",
            "2_GREEN_SQUIGGLE_SOLID.png",
            "2_PRUPLE_DIAMOND_HATCHED.png",
            "2_PURPLE_DIAMOND_OUTLINE.png",
            "2_PURPLE_DIAMOND_SOLID.png",
            "2_PURPLE_OVAL_HATCHED.png",
            "2_PURPLE_OVAL_OUTLINE.png",
            "2_PURPLE_OVAL_SOLID.png",
            "2_PURPLE_SQUIGGLE_HATCHED.png",
            "2_PURPLE_SQUIGGLE_OUTLINE.png",
            "2_PURPLE_SQUIGGLE_SOLID.png",
            "2_RED_DIAMOND_HATCHED.png",
            "2_RED_DIAMOND_OUTLINE.png",
            "2_RED_DIAMOND_SOLID.png",
            "2_RED_OVAL_HATCHED.png",
            "2_RED_OVAL_OUTLINE.png",
            "2_RED_OVAL_SOLID.png",
            "2_RED_SQUIGGLE_HATCHED.png",
            "2_RED_SQUIGGLE_OUTLINE.png",
            "2_RED_SQUIGGLE_SOLID.png",
            "3_GREEN_DIAMOND_HATCHED.png",
            "3_GREEN_DIAMOND_OUTLINE.png",
            "3_GREEN_DIAMOND_SOLID.png",
            "3_GREEN_OVAL_HATCHED.png",
            "3_GREEN_OVAL_OUTLINE.png",
            "3_GREEN_OVAL_SOLID.png",
            "3_GREEN_SQUIGGLE_HATCHED.png",
            "3_GREEN_SQUIGGLE_OUTLINE.png",
            "3_GREEN_SQUIGGLE_SOLID.png",
            "3_PRUPLE_DIAMOND_HATCHED.png",
            "3_PURPLE_DIAMOND_OUTLINE.png",
            "3_PURPLE_DIAMOND_SOLID.png",
            "3_PURPLE_OVAL_HATCHED.png",
            "3_PURPLE_OVAL_OUTLINE.png",
            "3_PURPLE_OVAL_SOLID.png",
            "3_PURPLE_SQUIGGLE_HATCHED.png",
            "3_PURPLE_SQUIGGLE_OUTLINE.png",
            "3_PURPLE_SQUIGGLE_SOLID.png",
            "3_RED_DIAMOND_HATCHED.png",
            "3_RED_DIAMOND_OUTLINE.png",
            "3_RED_DIAMOND_SOLID.png",
            "3_RED_OVAL_HATCHED.png",
            "3_RED_OVAL_OUTLINE.png",
            "3_RED_OVAL_SOLID.png",
            "3_RED_SQUIGGLE_HATCHED.png",
            "3_RED_SQUIGGLE_OUTLINE.png",
            "3_RED_SQUIGGLE_SOLID.png"
    };

    public void drawGrid() {
        Board board = game.getBoard();

        int rows = board.numRows();
        int cols = board.numInRows();

        JPanel cards = new JPanel(new GridLayout(rows, cols, cardSpacing, cardSpacing));

        BufferedImage img = null;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                try {
                    // Use the card from the game board so redrawing the grid
                    // always displays the actual cards (no duplicates from
                    // random selection). Construct filename from Card.toString().
                    Card card = board.getCard(r, c);
                    String fileName = "cards/" + card.toString() + ".png";
                    img = ImageIO.read(new File(fileName));
                } catch (IOException e) {
                    System.err.println("Could not load card image for position " + r + "," + c + ".");
                    img = null;
                }

                CardPanel cp = new CardPanel(r, c, img, cardSpacing, this, game);
                cp.setPreferredSize(new Dimension(cardWidth, cardHeight));
                cards.add(cp);
            }
        }
        // Remove existing components and re-add updated grid and buttons
        removeAll();
        add(cards);
        add(addThreeButton);
        add(exitButton);

        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            System.exit(0);
        }

        if (e.getSource() == addThreeButton) {
            System.out.println("I'll add 3 cards when this button is pushed.");
            int totalCards = game.getBoard().totalCards();
            if (game.getDeck().cardsRemaining() >= 3 && totalCards < MAX_CARDS_DISPLAYED) {
                game.add3();
                drawGrid();
                repaint();
            }

        }
    }
}
