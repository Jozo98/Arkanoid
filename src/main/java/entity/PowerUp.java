package entity;

import main.GamePanel;
import main.KeyHandler;
import utility.KollisionsChecker;

import java.awt.*;

public class PowerUp extends Entitaet {

    private Spieler spieler;
    private KollisionsChecker kollisionsChecker;
    private Stein stein;

    public PowerUp(GamePanel gp, KeyHandler keyH, Spieler spieler, Stein stein ,KollisionsChecker kollisionsChecker) {

        this.gp = gp;
        this.keyH = keyH;
        this.spieler = spieler;
        this.kollisionsChecker = kollisionsChecker;
        this.stein = stein;

        setDefaultValues();

    }
    @Override
    public void setDefaultValues() {
        x = stein.getPositionX();
        y= stein.getPositionY();
        geschwindigkeit = 10;
    }


    public void update() {
        y += geschwindigkeit;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect((int) x, (int) y, gp.tileSize / 2, gp.tileSize / 2);
    }
}
