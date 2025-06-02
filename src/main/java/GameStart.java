public class GameStart {

    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        GamePanel gamePanel = new GamePanel(gameFrame);
        gameFrame.add(gamePanel);
    }
}
