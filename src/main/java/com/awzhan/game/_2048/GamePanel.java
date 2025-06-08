package com.awzhan.game._2048;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JPanel;

import com.awzhan.game._2048.model.Card;

public class GamePanel extends JPanel {
    private static final int ROWS = 4;
    private static final int COLS = 4;
    private final Random random = new Random();

    private Card[][] cards;

    public GamePanel() {
        this.setLayout(null);
        this.setOpaque(false);

        initCards();

        createRandom();
    }

    private void initCards() {
        this.cards = new Card[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                int[] coordinates = Card.getCoordinates(i, j);
                cards[i][j] = new Card(coordinates[0], coordinates[1], 0);
            }
        }
    }

    private void createRandom() {
        if (isPanelFull()) {
            return;
        }
        
        int temp = random.nextInt(5);
        int value = temp == 0 ? 4 : 2;
        final Card card = getRandomCard();
        card.setValue(value);
    }

    private Card getRandomCard() {
        int i = random.nextInt(ROWS);
        int j = random.nextInt(COLS);
        if (cards[i][j].getValue() == 0) {
            return cards[i][j];
        }
        return getRandomCard();
    }

    private boolean isPanelFull() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (cards[i][j].getValue() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Arrays.stream(cards)
                .flatMap(Arrays::stream)
                .forEach(card -> card.draw(graphics));
    }
}
