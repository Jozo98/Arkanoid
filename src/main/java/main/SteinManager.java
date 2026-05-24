package main;

import entity.Ball;
import entity.Stein;

public class SteinManager {

    public static void aktiviereStein(Stein stein, Ball ball, Game game) {
        switch (stein.getType()) {
            case 1 -> game.addBall(ball.getPositionX(), ball.getPositionY());
            case 2 -> game.addPowerUp(stein);
        }
    }
}
