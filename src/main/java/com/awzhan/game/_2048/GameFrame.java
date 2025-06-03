package com.awzhan.game._2048;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class GameFrame extends JFrame {

    public GameFrame() {
        this.setTitle("Game 2048");
        this.setSize(370, 420);
        this.getContentPane().setBackground(new Color(66, 136, 83));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        createMenu();

        this.setVisible(true);
    }

    private void createMenu() {
        final Font font = new Font(Font.SANS_SERIF, Font.BOLD, 16);

        final JMenu menuGame = new JMenu("Game");
        menuGame.setFont(font);
        final JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(e -> newGame(e));
        final JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> exit(e));
        menuGame.add(newGame);
        menuGame.add(exit);

        final JMenu menuHelp = new JMenu("Help");
        menuHelp.setFont(font);
        final JMenuItem howToPlay = new JMenuItem("How to Play");
        howToPlay.addActionListener(e -> howToPlay(e));
        menuHelp.add(howToPlay);

        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuGame);
        menuBar.add(menuHelp);

        this.setJMenuBar(menuBar);
    }

    private void newGame(final ActionEvent event) {
        System.out.println("New Game");
    }

    private void exit(final ActionEvent event) {
        int res = JOptionPane.showConfirmDialog(this, "Do you want to exit game?");
        if (res == 0) {
            System.exit(0);
        }
    }

    private void howToPlay(final ActionEvent event) {
        System.out.println("How to Play");
    }
}
