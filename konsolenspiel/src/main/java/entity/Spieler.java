package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Spieler extends Entitaet {

    int startPosX = 288;
    int startPosY = 736;
    int startGeschwindigkeit = 8;

    public Spieler(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
    }

    public void setDefaultValues() {
        x = startPosX;
        y = startPosY;
        geschwindigkeit = startGeschwindigkeit;
    }

    public void update() {
        if (keyH.leftPressed) {
            x -= geschwindigkeit;
        } else if (keyH.rightPressed) {
            x += geschwindigkeit;
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(x, y, gp.tileSize * 2, gp.tileSize / 2);
    }

    public int getPositionX() {return x;}

    public int getPositionY() {return y;}

}
