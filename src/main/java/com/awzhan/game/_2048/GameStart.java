package com.awzhan.game._2048;

public class GameStart {

    public static void main(String[] args) {
        final GameFrame gameFrame = new GameFrame();
        final GamePanel gamePanel = new GamePanel(gameFrame);
        gameFrame.add(gamePanel);
        gameFrame.setVisible(true);
    }
}
