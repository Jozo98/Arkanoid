package entity;

import main.GamePanel;

import java.awt.*;

public class Stein extends Entitaet {

    public Stein(GamePanel gp, int x, int y) {

        this.gp = gp;
        this.x = x;
        this.y = y;
    }

    public void renderSteinImage(Graphics2D g2) {
        g2.setColor(Color.yellow);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize / 2);
    }

    public int  getPositionX() {return x;}

    public int getPositionY() {return y;}

}
