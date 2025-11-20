package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Ball extends Entitaet {

    int size;

    public Ball(GamePanel gp, KeyHandler keyH, Spieler spieler, int size) {

        this.gp = gp;
        this.keyH = keyH;
        this.spieler = spieler;
        this.size = size;

        setDefaultValues();
    }
    @Override
    public void setDefaultValues() {
        x = 0;
        y = 100;
        speedX = 4;
        speedY = 6;
    }
    @Override
    public void update() {

        x += speedX;
        y += speedY;

        if (x < 0 || x + size > gp.screenWidth) {
            speedX *= -1;
        }
        if (y < 0) {
            speedY *= -1;
        }

        if (ballCollidesWithPlayer()) {
            if (x + size <= spieler.getPositionX() + speedX * 2|| x >= spieler.getPositionX() + gp.tileSize * 2 + speedX * 2) {
                speedY *= -1;
                speedX *= -1;
            } else {
                speedY *= -1;
            }
        }
    }

    public boolean ballCollidesWithPlayer() {

        Rectangle ballRect = new Rectangle(x, y, size, size);
        Rectangle playerRect = new Rectangle(
                spieler.getPositionX(),
                spieler.getPositionY(),
                gp.tileSize * 2,
                gp.tileSize / 2
        );

        return ballRect.intersects(playerRect);
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
}
