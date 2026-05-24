package main;
import entity.Ball;
import entity.PowerUp;
import entity.Stein;


import javax.swing.*;
import java.awt.*;
import java.awt.image.VolatileImage;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    private final int originalTitleSize = 16;
    private final int scale = 3;
    private final int maxScreenCol = 14;
    private final int maxScreenRow = 16;
    private VolatileImage steineImage;

    //Ist hier public, da es im Tutorial auch auf public gestellt wurde. Nicht OOP.
    public final int tileSize = originalTitleSize * scale;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    Game game;
    UI ui;
    KeyHandler keyH = new KeyHandler(this);
    Sound sound = new Sound();
    GameStateManager gsm;
    Thread gameThread;

    public GamePanel() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        game = new Game(this, keyH);
        ui = new UI(this);
        gsm = new GameStateManager(this, keyH);
        buildSteineImage();
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double FPS = 60;
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;


        while (gameThread != null) {

            try {
                gsm.updateGameState();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if(gsm.getGameState() == GameState.EXIT){
                System.exit(0);
            }
            if(gsm.getGameState() == GameState.PLAY){
                try {
                    game.update();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

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
        if (gsm.getGameState() == GameState.PLAY) {
            g2.drawImage(steineImage, 0, 0, null);
            game.getSpieler().draw(g2);
            ArrayList<Ball> balls = game.getBall();
            for (Ball b : balls) {
                b.draw(g2);
            }
            ArrayList<PowerUp> powerUps = game.getPowerUp();
            for (PowerUp p : powerUps) {
                p.draw(g2);
            }
        }
        ui.draw(g2);
    }

    //ChatGPT
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
    //ChatGPT
    public void removeSteinFromImage(Stein removed) {
        if (removed == null) return;
        Graphics2D g2 = steineImage.createGraphics();
        g2.setColor(Color.BLACK);
        g2.fillRect(removed.getPositionX(), removed.getPositionY(),
                tileSize, tileSize / 2);
        g2.dispose();
    }

    //ChatGPT
    public void resetSteineImage() {
        if (steineImage != null) {
            steineImage.flush(); // release resources
        }
        buildSteineImage();
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic() {
        sound.stop();
    }

    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }
}
