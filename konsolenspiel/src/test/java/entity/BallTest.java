package entity;

import main.GamePanel;
import main.KeyHandler;

import static org.junit.jupiter.api.Assertions.*;

class BallTest {

    GamePanel gamePanel = new GamePanel();
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Spieler spieler = new Spieler(gamePanel, keyH);


    @org.junit.jupiter.api.Test
    void ballCollidesWithPlayer01() {
        Ball ball = new Ball(gamePanel, keyH, spieler, 20, gamePanel.steine);
        ball.setPositionX(spieler.getPositionX() - ball.getSize());
        ball.setPositionY(spieler.getPositionY());
        assertFalse(ball.ballCollidesWithPlayer());
    }
    @org.junit.jupiter.api.Test
    void ballCollidesWithPlayer02() {
        Ball ball = new Ball(gamePanel, keyH, spieler, 20, gamePanel.steine);
        ball.setPositionX(spieler.getPositionX() - ball.getSize() + 1);
        ball.setPositionY(spieler.getPositionY());
        assertTrue(ball.ballCollidesWithPlayer());
    }
    @org.junit.jupiter.api.Test
    void ballCollidesWithPlayer03() {
        Ball ball = new Ball(gamePanel, keyH, spieler, 20);
        ball.setPositionX(spieler.getPositionX());
        ball.setPositionY(spieler.getPositionY() - ball.getSize());
        assertFalse(ball.ballCollidesWithPlayer());
    }
    @org.junit.jupiter.api.Test
    void ballCollidesWithPlayer04() {
        Ball ball = new Ball(gamePanel, keyH, spieler, 20);
        ball.setPositionX(spieler.getPositionX());
        ball.setPositionY(spieler.getPositionY() - ball.getSize() + 1);
        assertTrue(ball.ballCollidesWithPlayer());
    }

    @org.junit.jupiter.api.Test
    void draw() {
    }

    @org.junit.jupiter.api.Test
    void getPositionX() {
    }

    @org.junit.jupiter.api.Test
    void getPositionY() {
    }
}