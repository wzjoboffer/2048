package com.awzhan.game._2048;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.awzhan.game._2048.model.Card;

public class GamePanel extends JPanel {
    private static final int ROWS = 4;
    private static final int COLS = 4;
    private final Random random = new Random();

    private final JFrame frame;
    private boolean gameFinish;
    private Card[][] cards;

    public GamePanel(JFrame frame) {
        this.frame = frame;
        this.gameFinish = false;
        this.setLayout(null);
        this.setOpaque(false);

        initCards();

        createRandomCard();

        bindKeyListener();
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

    private void createRandomCard() {
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

    private void bindKeyListener() {
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if (gameFinish) {
                    return;
                }
                switch (event.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        System.out.println("up");
                        moveUp();
                        break;
                    case KeyEvent.VK_DOWN:
                        System.out.println("down");
                        moveDown();
                        break;
                    case KeyEvent.VK_LEFT:
                        System.out.println("left");
                        moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        System.out.println("right");
                        moveRight();
                        break;
                    default:
                        System.out.println("other key pressed");
                }
            }
        });
    }

    private void moveUp() {
        CardUtils.cleanup(cards);
        for (int i = 1; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (cards[i][j].getValue() == 0) {
                    continue;
                }
                CardUtils.moveUp(cards, i, j);
            }
        }
        createRandomCard();
        repaint();
    }

    private void moveDown() {
        repaint();
    }

    private void moveLeft() {
        repaint();
    }

    private void moveRight() {
        CardUtils.cleanup(cards);
        for (int i = 0; i < ROWS; i++) {
            for (int j = COLS - 2; j >= 0; j--) {
                if (cards[i][j].getValue() == 0) {
                    continue;
                }
                CardUtils.moveRight(cards, i, j);
            }
        }
        createRandomCard();
        repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Arrays.stream(cards)
                .flatMap(Arrays::stream)
                .forEach(card -> card.draw(graphics));
    }
}
