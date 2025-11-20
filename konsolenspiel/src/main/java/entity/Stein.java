package entity;

import main.GamePanel;

import java.awt.*;

public class Stein extends Entitaet {

    Ball ball;
    boolean sichtbar;

    public Stein(GamePanel gp, Ball ball, int x, int y) {

        this.gp = gp;
        this.ball = ball;
        this.x = x;
        this.y = y;
        this.sichtbar = true;
    }
    @Override
    public void update() {
        if (ballCollidesWithStein()) {
            sichtbar = false;
        }
    }

    public boolean ballCollidesWithStein() {
        Rectangle ballRect = new Rectangle(x, y, gp.tileSize * 3, gp.tileSize / 2);
        Rectangle playerRect = new Rectangle(
                ball.getPositionX(),
                ball.getPositionY(),
                ball.size,
                ball.size
        );

        return ballRect.intersects(playerRect);
    }
    @Override
    public void draw(Graphics2D g2) {
        if (!sichtbar) return;
        g2.setColor(Color.yellow);
        g2.fillRect(x, y, gp.tileSize * 3, gp.tileSize / 2);
    }
}
