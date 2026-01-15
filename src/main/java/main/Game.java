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
    private GamePanel gp;
    private int size = 15;
    private int aktuellesLevel = 0;

    public Game(GamePanel gp, KeyHandler keyH) {
        spieler = new Spieler(gp, keyH);
        steine = new ArrayList<>();
        addSteine(gp);
        kollisionsChecker = new KollisionsChecker(gp, keyH, spieler, steine);
        ball = new Ball(gp, keyH, spieler, size, kollisionsChecker);
        this.gp = gp;
    }

    public void update(GamePanel gp) {
            spieler.update();
            updateLevels();
            ball.update();
    }


    public void updateLevels() {
        if (steine.isEmpty()) {
            aktuellesLevel++;
            reset();
        }
    }

    public void reset() {
        spieler.resetPosition();
        steine.clear();
        addSteine(gp);
        gp.resetSteineImage();
        ball.resetLeben();
        ball.resetPosition();
    }

    public void addSteine(GamePanel gp) {
        int y = 10;
        int abstand = 4;
        for (int i = 0; i < 16; i++) {
            int x = 48;
            y += 26;
            for (int j = 0; j < 11; j++) {
                if(Levels.levels[aktuellesLevel][j + i*11]) {
                    steine.add(new Stein(gp, x, y));

                }
                x += gp.tileSize + abstand;
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

    public void setAktuellesLevel(int x) {
        aktuellesLevel = x;
    }
}

