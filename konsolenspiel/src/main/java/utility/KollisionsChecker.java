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

    public boolean trefferLinkeSeiteSpieler(Ball ball) {
        return ball.getPositionX() + ball.getSize() <= spieler.getPositionX() + ball.getGeschwindigkeit();
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

    public boolean ballKollidiertMitSteinVonSeite(Ball ball, int steinIndex) {
        Stein stein = steine.get(steinIndex);
        if (ball.getPositionX() + ball.getSize() <= stein.getPositionX() + ball.getGeschwindigkeitX() && ball.getPositionX() > 10 ||
                ball.getPositionX() >= stein.getPositionX() + gp.tileSize + ball.getGeschwindigkeitX()) {
            return true;
        }
        return false;
    }

    public void entferneStein(int steinIndex) {
        Stein stein = steine.get(steinIndex);
        steine.remove(steinIndex);
        gp.removeSteinFromImage(stein);
    }

}
