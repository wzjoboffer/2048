import java.awt.Color;
import javax.swing.JFrame;

public class GameFrame extends JFrame {

    public GameFrame() {
        this.setTitle("Game 2048");
        this.setSize(370, 420);
        this.getContentPane().setBackground(new Color(66, 136, 83));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
}
