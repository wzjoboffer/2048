package com.awzhan.game._2048;

import java.awt.Graphics;
import java.util.Arrays;
import javax.swing.JPanel;

import com.awzhan.game._2048.model.Card;

public class GamePanel extends JPanel {
    private static final int ROWS = 4;
    private static final int COLS = 4;

    private Card[][] cards;

    public GamePanel() {
        this.setLayout(null);
        this.setOpaque(false);

        initCard();
    }

    private void initCard() {
        this.cards = new Card[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                int[] coordinates = Card.getCoordinates(i, j);
                cards[i][j] = new Card(coordinates[0], coordinates[1], 0);
            }
        }
        System.out.println("Cards created: " + Arrays.deepToString(cards));
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        System.out.println("called");
        Arrays.stream(cards)
                .flatMap(Arrays::stream)
                .forEach(card -> card.draw(graphics));
    }
}
