package entity;

import main.GamePanel;

import java.awt.*;

public class Stein extends Entitaet {

    int type;


    public Stein(GamePanel gp, int x, int y, int type) {

        this.gp = gp;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void renderSteinImage(Graphics2D g2) {
        if (type == 0) {g2.setColor(Color.yellow);}
        else if (type == 1) {g2.setColor(Color.blue);}
        else if (type == 2) {g2.setColor(Color.green);}
        g2.fillRect((int) x, (int) y, gp.tileSize, gp.tileSize / 2);

    }

    public int  getPositionX() {return (int) x;}

    public int getPositionY() {return (int) y;}

    public int getType() {return type;}

}
