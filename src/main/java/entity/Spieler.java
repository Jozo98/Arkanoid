package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Spieler extends Entitaet {

    int startPosX = 288;
    int startPosY = 720;
    int startGeschwindigkeit = 8;

    public Spieler(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
    }
    @Override
    public void setDefaultValues() {
        x = startPosX;
        y = startPosY;
        geschwindigkeit = startGeschwindigkeit;
    }
    @Override
    public void update() {
        if (keyH.leftPressed) {
            x -= geschwindigkeit;
        } else if (keyH.rightPressed) {
            x += geschwindigkeit;
        }
    }
    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect((int) x, (int) y, gp.tileSize * 2, gp.tileSize / 2);
    }

    public int getPositionX() {return (int) x;}

    public int getPositionY() {return (int) y;}

    public int getGeschwindigkeit() {
        return startGeschwindigkeit;
    }

    public void resetPosition() {
        x = 288;
        y = 720;
    }
}
