package entity;

import main.GamePanel;
import main.KeyHandler;
import utility.KollisionsChecker;

import java.awt.*;

public class Ball extends Entitaet {

    private int size;
    private double geschwindigkeitX;
    private double geschwindigkeitY;
    private double richtung;
    private Spieler spieler;
    private KollisionsChecker kollisionsChecker;
    private boolean startBall;
    private int leben;

    public Ball(GamePanel gp, KeyHandler keyH, Spieler spieler, KollisionsChecker kollisionsChecker) {

        this.gp = gp;
        this.keyH = keyH;
        this.spieler = spieler;
        this.kollisionsChecker = kollisionsChecker;

        setDefaultValues();
    }

    @Override
    public void setDefaultValues() {
        x = spieler.getPositionX() + (double) gp.tileSize - (double) size / 2;
        y = spieler.getPositionY() - size - 1;
        geschwindigkeitX = 0;
        geschwindigkeitY = 0;
        geschwindigkeit = 10;
        richtung = 1;
        leben = 3;
        size = 15;
        startBall = false;
    }

    @Override
    public void update() {
        if (keyH.spacePressed) {
            startBall = true;
            keyH.spacePressed = false;
        }

        if (startBall && leben > 0) {
            geschwindigkeitX = Math.cos(richtung) * geschwindigkeit;
            geschwindigkeitY = Math.sin(richtung) * geschwindigkeit;
            x += geschwindigkeitX;
            y += geschwindigkeitY;
        } else {
            x = (spieler.getPositionX() + gp.tileSize - (double) size / 2);
        }
        if (y > gp.screenHeight) {
            leben--;
            startBall = false;
            resetRichtung();

            if (leben > 0) {
                x = (spieler.getPositionX() + gp.tileSize - (double) size / 2);
                y = spieler.getPositionY() - size - 1;
            }
        }

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

    public int getLeben() {
        return leben;
    }

    public void resetLeben() {leben = 3; }

    public void resetRichtung() {richtung = 1;}

    public void resetPosition() {
        x = spieler.getPositionX() + (double) gp.tileSize - (double) size / 2;
        y = spieler.getPositionY() - size - 1;
        startBall = false;
    }

    public void reset() {
        resetLeben();
        resetRichtung();
        resetPosition();
    }
}