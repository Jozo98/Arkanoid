package main;

import entity.Ball;
import entity.Spieler;
import entity.Stein;
import utility.KollisionsChecker;

import java.util.ArrayList;

public class Game {

    private Spieler spieler;
    private Ball ball;
    private ArrayList<Stein> steine;
    private KollisionsChecker kollisionsChecker;

    public Game(GamePanel gp, KeyHandler keyH) {
        spieler = new Spieler(gp, keyH);
        steine = new ArrayList<>();
        addSteine(gp);
        kollisionsChecker = new KollisionsChecker(gp, keyH, spieler, steine);
        ball = new Ball(gp, keyH, spieler, 20, kollisionsChecker);
    }

    public void update(GamePanel gp) {
        spieler.update();
        ball.update();
    }

    private void addSteine(GamePanel gp) {
        int y = 10;
        int abstand = 10;
        for (int j = 0; j < 3; j++) {
            int x = 58;
            y += 26;
            for (int i = 0; i < 11; i++) {
                steine.add(new Stein(gp, x, y));
                x += abstand + 40;
            }
        }
    }

    public Spieler getSpieler() {
        return spieler;
    }

    public Ball getBall() {
        return ball;
    }

    public ArrayList<Stein> getSteine() {
        return steine;
    }

    public KollisionsChecker getKollisionsChecker() {
        return kollisionsChecker;
    }
}

