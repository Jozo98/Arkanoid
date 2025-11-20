package main;

import entity.Ball;
import entity.Spieler;
import entity.Stein;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTitleSize = 16;
    final int scale = 3;

    public final int tileSize = originalTitleSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    final double FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Spieler spieler = new Spieler(this, keyH);
    Ball ball = new Ball(this, keyH, spieler, 20);
    Stein stein = new Stein(this, ball, 300, 40);
    Stein stein1 = new Stein(this, ball, 600, 40);
    Stein stein2 = new Stein(this, ball, 10, 40);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;


        while (gameThread != null) {

            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        spieler.update();
        ball.update();
        stein.update();
        stein1.update();
        stein2.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        spieler.draw(g2);
        ball.draw(g2);
        stein.draw(g2);
        stein1.draw(g2);
        stein2.draw(g2);
        g2.dispose();
    }
}
