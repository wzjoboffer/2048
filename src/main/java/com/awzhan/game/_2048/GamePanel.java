package com.awzhan.game._2048;

import com.awzhan.game._2048.model.Card;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;

public class GamePanel extends JPanel {
    private static final int ROWS = 4;
    private static final int COLS = 4;
    private static final Set<Integer> VALID_KEY_CODES =
            Set.of(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);

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
                moveCard(event);
            }
        });
    }

    private void moveCard(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (!VALID_KEY_CODES.contains(keyCode)) {
            System.out.println("Other key pressed.");
            return;
        }

        CardUtils.cleanup(cards);

        if (KeyEvent.VK_UP == keyCode) {
            System.out.println("Up");
            moveUp();
        }
        else if (KeyEvent.VK_DOWN == keyCode) {
            System.out.println("Down");
            moveDown();
        }
        else if (KeyEvent.VK_LEFT == keyCode) {
            System.out.println("Left");
            moveLeft();
        }
        else if (KeyEvent.VK_RIGHT == keyCode) {
            System.out.println("Right");
            moveRight();
        }

        createRandomCard();
        repaint();

        checkGame();
    }

    private void moveUp() {
        for (int i = 1; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (cards[i][j].getValue() == 0) {
                    continue;
                }
                CardUtils.moveUp(cards, i, j);
            }
        }
    }

    private void moveDown() {
        for (int i = ROWS - 2; i >= 0; i--) {
            for (int j = 0; j < COLS; j++) {
                if (cards[i][j].getValue() == 0) {
                    continue;
                }
                CardUtils.moveDown(cards, i, j);
            }
        }
    }

    private void moveLeft() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 1; j < COLS; j++) {
                if (cards[i][j].getValue() == 0) {
                    continue;
                }
                CardUtils.moveLeft(cards, i, j);
            }
        }
    }

    private void moveRight() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = COLS - 2; j >= 0; j--) {
                if (cards[i][j].getValue() == 0) {
                    continue;
                }
                CardUtils.moveRight(cards, i, j);
            }
        }
    }

    private void checkGame() {
        if (reach2048()) {
            gameWin();
        }
        else if (isPanelFull() && !canMove()) {
            gameOver();
        }
    }

    private boolean reach2048() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (cards[i][j].getValue() == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canMove() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                int curr = cards[i][j].getValue();
                int up = (i == 0) ? 0 : cards[i-1][j].getValue();
                int down = (i == ROWS - 1) ? 0 : cards[i+1][j].getValue();
                int left = (j == 0) ? 0 : cards[i][j-1].getValue();
                int right = (j == COLS - 1) ? 0 : cards[i][j+1].getValue();
                if (curr == up || curr == down || curr == left || curr == right) {
                    return true;
                }
            }
        }
        return false;
    }

    private void gameWin() {
        gameFinish = true;
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font(Font.SANS_SERIF, Font.ITALIC, 18)));
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(Font.SANS_SERIF, Font.ITALIC, 18)));
        JOptionPane.showMessageDialog(frame, "You Win!");
    }

    private void gameOver() {
        gameFinish = true;
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font(Font.SANS_SERIF, Font.ITALIC, 18)));
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(Font.SANS_SERIF, Font.ITALIC, 18)));
        JOptionPane.showMessageDialog(frame, "Game Over!");
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Arrays.stream(cards)
                .flatMap(Arrays::stream)
                .forEach(card -> card.draw(graphics));
    }
}
