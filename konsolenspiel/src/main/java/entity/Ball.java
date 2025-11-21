package entity;

import main.GamePanel;
import main.KeyHandler;
import utility.KollisionsChecker;

import java.awt.*;

public class Ball extends Entitaet {

    int size;
    int geschwindigkeitX;
    int geschwindigkeitY;
    Spieler spieler;
    KollisionsChecker kollisionsChecker;

    public Ball(GamePanel gp, KeyHandler keyH, Spieler spieler, int size, KollisionsChecker kollisionsChecker) {

        this.gp = gp;
        this.keyH = keyH;
        this.spieler = spieler;
        this.size = size;
        this.kollisionsChecker = kollisionsChecker;
        this.geschwindigkeit = 5;

        setDefaultValues();
    }
    @Override
    public void setDefaultValues() {
        x = 100;
        y = 150;
        geschwindigkeitX = 5;
        geschwindigkeitY = 8;
    }
    @Override
    public void update() {

        x += geschwindigkeitX;
        y += geschwindigkeitY;


        if (x < gp.tileSize || x + size > gp.screenWidth - gp.tileSize) {
            geschwindigkeitX *= -1;
        }
        if (y < 0) {
            geschwindigkeitY *= -1;
        }

        if (kollisionsChecker.ballCollidesWithPlayer(this)) {
            if (x + size <= spieler.getPositionX() + geschwindigkeitX * 2 || x >= spieler.getPositionX() + gp.tileSize * 2 + geschwindigkeitX * 2) {
                geschwindigkeitY *= -1;
                geschwindigkeitX *= -1;
            } else {
                geschwindigkeitY *= -1;
            }
        }
        int i = kollisionsChecker.ballCollidesWithStein(this);
        if (i > -1) {

            // bounce logic
            if (kollisionsChecker.ballCollidesWithSteinFromSide(this, i)) {
                geschwindigkeitX *= -1;
            } else {
                geschwindigkeitY *= -1;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.red);
        g2.fillOval(x, y, 10 * 2, 10 * 2);
    }

    public int getPositionX() {
        return x;
    }

    public int getPositionY() {
        return y;
    }

    public void  setPositionX(int x) {
        this.x = x;
    }
    public void setPositionY(int y) {
        this.y = y;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getGeschwindigkeitX() {
        return geschwindigkeitX;
    }
}
