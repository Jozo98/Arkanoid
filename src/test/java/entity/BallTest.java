package entity;

import main.Game;
import main.GamePanel;
import main.KeyHandler;
import utility.KollisionsChecker;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BallTest {
    GamePanel gamePanel = new GamePanel();
    KeyHandler keyH = new KeyHandler(gamePanel);
    Game game = new Game(gamePanel, keyH);
    Thread gameThread;
    Spieler spieler = new Spieler(gamePanel, keyH);
    KollisionsChecker kollisionsChecker = new KollisionsChecker(gamePanel, keyH, spieler, game.getSteine());

    BallTest() throws IOException {
    }


    @org.junit.jupiter.api.Test
    void ballCollidesWithPlayer01() {
        Ball ball = new Ball(gamePanel, keyH, spieler, 20, kollisionsChecker);
        ball.setPositionX(spieler.getPositionX() - ball.getSize());
        ball.setPositionY(spieler.getPositionY());
        assertFalse(kollisionsChecker.ballKollidiertMitSpieler(ball));
    }
    @org.junit.jupiter.api.Test
    void ballCollidesWithPlayer02() {
        Ball ball = new Ball(gamePanel, keyH, spieler, 20, kollisionsChecker);
        ball.setPositionX(spieler.getPositionX() - ball.getSize() + 1);
        ball.setPositionY(spieler.getPositionY());
        assertTrue(kollisionsChecker.ballKollidiertMitSpieler(ball));
    }
    @org.junit.jupiter.api.Test
    void ballCollidesWithPlayer03() {
        Ball ball = new Ball(gamePanel, keyH, spieler, 20, kollisionsChecker);
        ball.setPositionX(spieler.getPositionX());
        ball.setPositionY(spieler.getPositionY() - ball.getSize());
        assertFalse(kollisionsChecker.ballKollidiertMitSpieler(ball));
    }
    @org.junit.jupiter.api.Test
    void ballCollidesWithPlayer04() {
        Ball ball = new Ball(gamePanel, keyH, spieler, 20,  kollisionsChecker);
        ball.setPositionX(spieler.getPositionX());
        ball.setPositionY(spieler.getPositionY() - ball.getSize() + 1);
        assertTrue(kollisionsChecker.ballKollidiertMitSpieler(ball));
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