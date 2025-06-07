package com.awzhan.game._2048.model;

import java.awt.Graphics;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Card {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 80;
    private static final int OFFSET = 10;
    private static final int INTERVAL = 5;
    private static final int ARC_WIDTH = 4;
    private static final int ARC_HEIGHT = 4;

    private int x;
    private int y;
    private int value;
    private boolean merge;

    public Card(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.merge = false;
    }

    public static int[] getCoordinates(int i, int j) {
        return new int[] {
            OFFSET + j * WIDTH + (j + 1) * INTERVAL,
            OFFSET + i * HEIGHT + (i + 1) * INTERVAL
        };
    }

    public void draw(Graphics graphics) {
        graphics.fillRoundRect(x, y, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
    }
}
