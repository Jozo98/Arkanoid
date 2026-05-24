package main;


import entity.Ball;
import entity.PowerUp;
import entity.Spieler;
import entity.Stein;
import utility.KollisionsChecker;
import utility.SaveManager;

import java.io.IOException;
import java.util.ArrayList;

public class Game {

    private Spieler spieler;
    private KollisionsChecker kollisionsChecker;
    private GamePanel gp;
    private ArrayList<Stein> steine;
    private ArrayList<Ball> balls;
    private ArrayList<PowerUp> powerUps;
    private int aktuellesLevel;
    private int leben;

    public Game(GamePanel gp, KeyHandler keyH) throws IOException {
        spieler = new Spieler(gp, keyH);
        steine = new ArrayList<>();
        balls = new ArrayList<>();
        powerUps = new ArrayList<>();
        aktuellesLevel = SaveManager.loadGame();
        addSteine(gp);
        kollisionsChecker = new KollisionsChecker(gp, keyH, spieler, steine);
        balls.add(new Ball(gp, keyH, spieler, kollisionsChecker));
        leben = 3;
        this.gp = gp;
    }

    public void update() throws IOException {
        spieler.update();
        updateLevels();
        ArrayList<Ball> toRemove = new ArrayList<>();
        int size = balls.size();
        for (int i = 0; i < size; i++) {
            Ball ball = balls.get(i);
            int steinIndex = ball.update();

            if (steinIndex != -1) {
                PowerUpManager.aktivierePowerUps(steine.get(steinIndex), ball, this);
                entferneStein(steinIndex);
            }

            if (ball.getPositionY() > gp.screenHeight) {
                if(balls.size() == 1) {
                    leben--;
                    if (leben > 0) {
                        ball.reset();
                        gp.playSE(10);
                    } else {
                        gp.playSE(11);
                    }
                } else {
                    toRemove.add(ball);
            }
            }
        }
        balls.removeAll(toRemove);
        gp.keyH.spacePressed = false;

        size = powerUps.size();
        for (int i = 0; i < size; i++) {
            PowerUp powerUp = powerUps.get(i);
            powerUp.update();
        }
    }


    public void updateLevels() throws IOException {
        if (steine.isEmpty()) {
            aktuellesLevel++;
            SaveManager.saveGame(aktuellesLevel);
            reset();
            gp.playSE(9);
        }
    }

    public void reset() {
        spieler.resetPosition();
        steine.clear();
        leben = 3;
        addSteine(gp);
        gp.resetSteineImage();
        balls.clear();
        balls.add(new Ball(gp, gp.keyH, spieler, kollisionsChecker));
    }

    public void addSteine(GamePanel gp) {
        int y = 10;
        int abstand = 4;
        for (int i = 0; i < 16; i++) {
            int x = gp.tileSize + 4;
            y += 26;
            for (int j = 0; j < 11; j++) {
                if(Levels.levels[aktuellesLevel][j + i*11] == 1) {
                    steine.add(new Stein(gp, x, y, 0));
                }
                else if (Levels.levels[aktuellesLevel][j + i*11] == 2) {
                    steine.add(new Stein(gp, x, y, 1));
                }
                else if (Levels.levels[aktuellesLevel][j + i*11] == 3) {
                    steine.add(new Stein(gp, x, y, 2));
                }
                x += gp.tileSize + abstand;
            }
        }
    }

    public void entferneStein(int steinIndex) {
            Stein stein = steine.get(steinIndex);
            steine.remove(steinIndex);
            gp.removeSteinFromImage(stein);
    }

    public void addBall(int x, int y) {
        for(int i = 0; i < 2; i++) {
            Ball newBall = new Ball(gp, gp.keyH, spieler, kollisionsChecker);
            newBall.setPositionX(x);
            newBall.setPositionY(y);
            newBall.setStartBall(true);
            double richtung = 0.8 + (balls.size() * 0.1 * i);
            newBall.resetRichtung(richtung);
            balls.add(newBall);
        }
    }

    public void addPowerUp(Stein stein) {
        powerUps.add(new PowerUp(gp, gp.keyH, spieler, stein, kollisionsChecker));
    }

    public void startNewGame() throws IOException {
        SaveManager.resetGame();
        aktuellesLevel = SaveManager.loadGame();
        reset();
    }

    public Spieler getSpieler() {
        return spieler;
    }

    public ArrayList<Ball> getBall() {
        return balls;
    }

    public ArrayList<PowerUp> getPowerUp() {
        return powerUps;
    }

    public ArrayList<Stein> getSteine() {
        return steine;
    }

    public boolean isGameOver() {
        return leben == 0;
    }

    public int getLeben() {
        return leben;
    }
}

