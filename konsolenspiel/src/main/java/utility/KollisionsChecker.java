package utility;

import entity.Ball;
import entity.Spieler;
import entity.Stein;
import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.util.ArrayList;

public class KollisionsChecker {

    GamePanel gp;
    KeyHandler keyH;
    Spieler spieler;
    ArrayList<Stein> steine;

    public KollisionsChecker(GamePanel gp, KeyHandler keyH,  Spieler spieler, ArrayList<Stein> steine) {
        this.gp = gp;
        this.keyH = keyH;
        this.spieler = spieler;
        this.steine = steine;
    }

    public boolean trefferBildschirmLinksRechts(Ball ball) {
        return ball.getPositionX() < gp.tileSize || ball.getPositionX() + ball.getSize() > gp.screenWidth - gp.tileSize;
    }

    public boolean trefferBildschirmOben(Ball ball) {
        return ball.getPositionY() < 0;
    }

    public boolean ballKollidiertMitSpieler(Ball ball) {

        Rectangle ballRect = new Rectangle(ball.getPositionX(), ball.getPositionY(), ball.getSize(), ball.getSize());
        Rectangle playerRect = new Rectangle(
                spieler.getPositionX(),
                spieler.getPositionY(),
                gp.tileSize * 2,
                gp.tileSize / 2
        );

        return ballRect.intersects(playerRect);
    }
    //claude.ai erstellt:
    public boolean trefferSeiteSpieler(Ball ball) {
        int overlapLeft = (ball.getPositionX() + ball.getSize() / 2)  - spieler.getPositionX();
        int overlapRight = (spieler.getPositionX() + gp.tileSize * 2) - ball.getPositionX() - ball.getSize() / 2;
        int overlapTop = (ball.getPositionY() + ball.getSize() / 2) - spieler.getPositionY();
        int overlapBottom = (spieler.getPositionY() + gp.tileSize / 2) - ball.getPositionY() - ball.getSize() / 2;

        // Find minimum overlap
        int minOverlap = Math.min(Math.min(overlapLeft, overlapRight),
                Math.min(overlapTop, overlapBottom));

        // Push ball out and determine collision type
        if (minOverlap == overlapLeft) {
            ball.setPositionX(spieler.getPositionX() - ball.getSize() - 1);
            if (keyH.leftPressed) {ball.setPositionX(ball.getPositionX() - 7);}
            return true;
        } else if (minOverlap == overlapRight) {
            ball.setPositionX(spieler.getPositionX() + gp.tileSize * 2 + 1);
            if (keyH.rightPressed) {ball.setPositionX(ball.getPositionX() + 7);}
            return true;
        } else if (minOverlap == overlapTop) {
            ball.setPositionY(spieler.getPositionY() - ball.getSize() - 1);
            return false;
        } else {
            return false;
        }
    }

    public boolean trefferRechteSeiteSpieler(Ball ball) {
        return ball.getPositionX() >= spieler.getPositionX() + gp.tileSize * 2 - ball.getGeschwindigkeit();
    }

    public int ballKollidiertMitStein(Ball ball) {
        int i = 0;
        for (Stein stein : steine) {
            Rectangle steinRect = new Rectangle(stein.getPositionX(), stein.getPositionY(), gp.tileSize, gp.tileSize / 2);
            Rectangle ballRect = new Rectangle(
                    ball.getPositionX(),
                    ball.getPositionY(),
                    ball.getSize(),
                    ball.getSize()
            );
            if (ballRect.intersects(steinRect)) {
                return i;
            }
            i++;
        }
        return -1;
    }
    //Folgende Methoden wurde durch claude.ai erstellt:
    public boolean ballKollidiertMitSteinVonSeite(Ball ball, int steinIndex) {
        Stein stein = steine.get(steinIndex);

        int overlapLeft = (ball.getPositionX() + ball.getSize() / 2)  - stein.getPositionX();
        int overlapRight = (stein.getPositionX() + gp.tileSize) - ball.getPositionX() - ball.getSize() / 2;
        int overlapTop = (ball.getPositionY() + ball.getSize() / 2) - stein.getPositionY();
        int overlapBottom = (stein.getPositionY() + gp.tileSize / 2) - ball.getPositionY() - ball.getSize() / 2;

        // Find minimum overlap
        int minOverlap = Math.min(Math.min(overlapLeft, overlapRight),
                Math.min(overlapTop, overlapBottom));

        // Push ball out and determine collision type
        if (minOverlap == overlapLeft) {

            return true;
        } else if (minOverlap == overlapRight) {

            return true;
        } else if (minOverlap == overlapTop) {

            return false;
        } else { // bottom

            return false;
        }
    }

    public void entferneStein(int steinIndex) {
        Stein stein = steine.get(steinIndex);
        steine.remove(steinIndex);
        gp.removeSteinFromImage(stein);
    }

}
