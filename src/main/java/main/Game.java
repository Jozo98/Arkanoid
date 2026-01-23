package main;


import entity.Ball;
import entity.Spieler;
import entity.Stein;
import utility.KollisionsChecker;
import utility.SaveManager;

import java.io.IOException;
import java.util.ArrayList;

public class Game {

    private Spieler spieler;
    private Ball ball;
    private KollisionsChecker kollisionsChecker;
    private GamePanel gp;
    private ArrayList<Stein> steine;
    private int size;
    private int aktuellesLevel;

    public Game(GamePanel gp, KeyHandler keyH) throws IOException {
        spieler = new Spieler(gp, keyH);
        steine = new ArrayList<>();
        aktuellesLevel = SaveManager.loadGame();
        addSteine(gp);
        kollisionsChecker = new KollisionsChecker(gp, keyH, spieler, steine);
        ball = new Ball(gp, keyH, spieler, kollisionsChecker);
        this.gp = gp;
    }

    public void update() throws IOException {
            spieler.update();
            updateLevels();
            ball.update();
    }


    public void updateLevels() throws IOException {
        if (steine.isEmpty()) {
            aktuellesLevel++;
            SaveManager.saveGame(aktuellesLevel);
            reset();
        }
    }

    public void reset() {
        spieler.resetPosition();
        steine.clear();
        addSteine(gp);
        gp.resetSteineImage();
        ball.reset();
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

    public void startNewGame() throws IOException {
        SaveManager.resetGame();
        aktuellesLevel = SaveManager.loadGame();
        reset();
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

    public boolean isGameOver() {
        return ball.getLeben() == 0;
    }

    public int getLeben() {
        return ball.getLeben();
    }
}

