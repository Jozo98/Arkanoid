package main;
import entity.Stein;
import javax.swing.*;
import java.awt.*;
import java.awt.image.VolatileImage;

public class GamePanel extends JPanel implements Runnable {

    final int originalTitleSize = 16;
    final int scale = 3;

    public final int tileSize = originalTitleSize * scale;
    final int maxScreenCol = 14;
    final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    private VolatileImage steineImage;


    final double FPS = 60;

    Game game;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        game = new Game(this, keyH);
        buildSteineImage();
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

            game.update(this);

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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(steineImage, 0, 0, null);
        game.getSpieler().draw(g2);
        game.getBall().draw(g2);
    }

    private void buildSteineImage() {
        // Use opaque image for speed
        steineImage = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .createCompatibleVolatileImage(screenWidth, screenHeight, Transparency.OPAQUE);

        Graphics2D g2 = steineImage.createGraphics();
        g2.setColor(Color.BLACK); // background
        g2.fillRect(0, 0, screenWidth, screenHeight);

        // Draw all bricks once
        for (Stein stein : game.getSteine()) {
            stein.renderSteinImage(g2);
        }

        g2.dispose();
    }

    public void removeSteinFromImage(Stein removed) {
        if (removed == null) return;
        Graphics2D g2 = steineImage.createGraphics();
        g2.setColor(Color.BLACK);
        g2.fillRect(removed.getPositionX(), removed.getPositionY(),
                tileSize, tileSize / 2);
        g2.dispose();
    }
}
