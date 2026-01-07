import javax.swing.*;
import java.awt.*;

/**
 * The GameGUI class represents the main application window
 * for the Game of Set. It serves as the entry point to the graphical
 * user interface and is responsible for initializing the game frame,
 * adding the main panel, and displaying the interface.
 *
 * This class extends
 
 
 
 entire user interface. Upon construction, it
 * automatically creates and adds a MainPanel, sets frame
 * properties (such as size, close behavior, and visibility),
 * and launches the graphical game environment.
 *
 *
 */
public class GameGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new GameGUI window for the Game of Set.
     * This constructor initializes the game title, adds a
     * MainPanel, and configures default frame properties.
     *
     * The window is made visible automatically upon creation.
     */
    public GameGUI() {
        super("Game of Set!");
        // keep constructor minimal; actual UI wiring happens in init() on the EDT
    }

    /**
     * Initialize UI components and show the frame. Call on the EDT.
     */
    public void init() {
        this.add(new MainPanel());
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
    }

    /**
     * The main entry point for launching the Game of Set GUI.
     * Creates a GameGUI instance, which automatically
     * initializes and displays the main application window.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            GameGUI frame = new GameGUI();
            frame.init();
            frame.setVisible(true);
        });
    }
}
