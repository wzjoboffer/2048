package com.awzhan.game._2048.model;

import java.awt.Color;
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
        final Color oldColor = graphics.getColor();
        graphics.setColor(getColor(value));
        graphics.fillRoundRect(x, y, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
        graphics.setColor(oldColor);
    }

    private Color getColor(int value) {
        switch (value) {
            case 2:
                return new Color(238, 244, 234);
            case 4:
                return new Color(222, 236, 200);
            case 8:
                return new Color(174, 213, 130);
            case 16:
                return new Color(142, 201, 75);
            case 32:
                return new Color(111, 148, 48);
            case 64:
                return new Color(76, 174, 124);
            case 128:
                return new Color(60, 180, 144);
            case 256:
                return new Color(45, 130, 120);
            case 512:
                return new Color(9, 97, 26);
            case 1024:
                return new Color(242, 177, 121);
            case 2048:
                return new Color(223, 185, 0);
            default:
                return new Color(92, 151, 117);
        }
    }
}
