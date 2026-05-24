package entity;

import main.GamePanel;
import main.KeyHandler;
import utility.KollisionsChecker;

import java.awt.*;

public class PowerUp extends Entitaet {

    private KollisionsChecker kollisionsChecker;
    private Stein stein;
    private int type;

    public PowerUp(GamePanel gp, KeyHandler keyH, Spieler spieler, Stein stein ,KollisionsChecker kollisionsChecker, int type) {

        this.gp = gp;
        this.keyH = keyH;
        this.kollisionsChecker = kollisionsChecker;
        this.stein = stein;
        this.type = type;

        setDefaultValues();

    }
    @Override
    public void setDefaultValues() {
        x = stein.getPositionX() + (double) gp.tileSize / 4;
        y= stein.getPositionY();
        geschwindigkeit = 6;
    }


    public boolean update() {
        y += geschwindigkeit;
        boolean getroffen = kollisionsChecker.powerUpKollidiertMitSpieler(this);
        if(getroffen) {
            gp.playSE(1);
            return true;
        }
        return false;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect((int) x, (int) y, gp.tileSize / 2, gp.tileSize / 2);
    }

    public int getPositionX() {
        return  (int) x;
    }

    public int getPositionY() {
        return  (int) y;
    }

    public int getType() {
        return type;
    }
}
