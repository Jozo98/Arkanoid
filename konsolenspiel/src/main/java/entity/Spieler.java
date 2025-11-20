package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Spieler extends Entitaet {

    public Spieler(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 100;
        y = 530;
        speed = 8;
    }

    public void update() {
        if(keyH.leftPressed){
            x -= speed;
        }
        else if(keyH.rightPressed){
            x += speed;
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(x,y,gp.tileSize * 2,gp.tileSize / 2);
    }

    public int getPositionX() {

        return x;
    }

    public int getPositionY() {

        return y;
    }

}
