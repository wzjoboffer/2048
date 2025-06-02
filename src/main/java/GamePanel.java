import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private final JFrame frame;

    public GamePanel(JFrame frame) {
        this.frame = frame;
        this.setLayout(null);
        this.setOpaque(false);
    }
}
