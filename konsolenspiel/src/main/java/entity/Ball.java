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

    public void pruefeKollisionBallBildschirm(){
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
        if (kollisionsChecker.ballKollidiertMitSpieler(this)) {

            if (kollisionsChecker.trefferLinkeSeiteSpieler(this)) {
                richtung -= Math.PI;
                x = spieler.getPositionX() - size;
            } else if (kollisionsChecker.trefferRechteSeiteSpieler(this)) {
                richtung -= Math.PI;
                x = spieler.getPositionX() + gp.tileSize * 2;
            } else {
                richtung = -richtung;

                if (keyH.rightPressed) {
                    richtung += 0.4;
                }
                if (keyH.leftPressed) {
                    richtung -= 0.4;
                }
                if (Math.sin(richtung) > 0) {
                    richtung = -Math.abs(richtung);
                }

                double minSin = 0.3;
                if (Math.abs(Math.sin(richtung)) < minSin) {
                    if (Math.cos(richtung) > 0) {
                        richtung = -Math.asin(minSin);
                    } else {
                        richtung = Math.PI + Math.asin(minSin);
                    }
                }
            }
        }
    }

    public void pruefeKollisionBallSteine() {
        int steinIndex = kollisionsChecker.ballKollidiertMitStein(this);
        if (steinIndex > -1) {
            if (kollisionsChecker.ballKollidiertMitSteinVonSeite(this, steinIndex)) {
                richtung = Math.PI - richtung;
            } else {
                richtung = -richtung;
            }
            kollisionsChecker.entferneStein(steinIndex);
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

    public double getGeschwindigkeitX() {
        return geschwindigkeitX;
    }

    public double getGeschwindigkeit() {
        return geschwindigkeit;
    }
}
