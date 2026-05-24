package main;

import entity.Ball;
import entity.Spieler;
import entity.Stein;

import java.util.ArrayList;

public class PowerUpManager {

    public static void aktivierePowerUps(Stein stein, Ball ball, Game game) {
        switch (stein.getType()) {
            case 1 -> game.addBall(ball.getPositionX(), ball.getPositionY());
            case 2 -> game.addPowerUp(stein);
        }
    }
}
