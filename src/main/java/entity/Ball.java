package entity;

import main.GamePanel;
import main.KeyHandler;
import utility.KollisionsChecker;

import java.awt.*;

public class Ball extends Entitaet {

    int size;
    double geschwindigkeitX;
    double geschwindigkeitY;
    double richtung;
    Spieler spieler;
    KollisionsChecker kollisionsChecker;

    public Ball(GamePanel gp, KeyHandler keyH, Spieler spieler, int size, KollisionsChecker kollisionsChecker) {

        this.gp = gp;
        this.keyH = keyH;
        this.spieler = spieler;
        this.size = size;
        this.kollisionsChecker = kollisionsChecker;

        setDefaultValues();
    }

    @Override
    public void setDefaultValues() {
        x = 100;
        y = 150;
        geschwindigkeitX = 0;
        geschwindigkeitY = 0;
        geschwindigkeit = 10;
        richtung = 1;
    }

    @Override
    public void update() {

        geschwindigkeitX = Math.cos(richtung) * geschwindigkeit;
        geschwindigkeitY = Math.sin(richtung) * geschwindigkeit;

        x += geschwindigkeitX;
        y += geschwindigkeitY;

        pruefeKollisionBallBildschirm();

        pruefeKollisionBallSpieler();

        pruefeKollisionBallSteine();
    }

    public void pruefeKollisionBallBildschirm() {
        if (kollisionsChecker.trefferBildschirmLinksRechts(this)) {
            richtung = Math.PI - richtung;
            x = Math.max(gp.tileSize, Math.min(gp.screenWidth - gp.tileSize - size, x));
        }
        if (kollisionsChecker.trefferBildschirmOben(this)) {
            richtung = -richtung;
            y = 0;
        }
    }

    public void pruefeKollisionBallSpieler() {
        if (!(kollisionsChecker.ballKollidiertMitSpieler(this))) {
            return;
        }
        if (kollisionsChecker.trefferSeiteSpieler(this)) {
            richtung -= Math.PI;
        } else {
            richtung = -richtung;

            if (keyH.rightPressed) {
                richtung += 0.4;
            }
            if (keyH.leftPressed) {
                richtung -= 0.4;
            }
            korrigiereBallrichtung();
        }
    }

    public void pruefeKollisionBallSteine() {
        int steinIndex = kollisionsChecker.ballKollidiertMitStein(this);
        if (!(steinIndex > -1)) {
            return;
        }
        if (kollisionsChecker.ballKollidiertMitSteinVonSeite(this, steinIndex)) {
            richtung = Math.PI - richtung;
        } else {
            richtung = -richtung;
        }
        kollisionsChecker.entferneStein(steinIndex);
    }

    public void korrigiereBallrichtung() {
        double minSin = 0.3;
        if (Math.sin(richtung) > 0) {
            richtung = -Math.abs(richtung);
        }
        if (!(Math.abs(Math.sin(richtung)) < minSin)) {
            return;
        }
        if (Math.cos(richtung) > 0) {
            richtung = -Math.asin(minSin);
        } else {
            richtung = Math.PI + Math.asin(minSin);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.red);
        g2.fillOval((int) x, (int) y, size, size);
    }

    public int getPositionX() {
        return (int) x;
    }

    public int getPositionY() {
        return (int) y;
    }

    public void setPositionX(int x) {
        this.x = x;
    }

    public void setPositionY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public double getGeschwindigkeit() {
        return geschwindigkeit;
    }
}
