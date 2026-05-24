package main;

import entity.Ball;
import entity.PowerUp;
import entity.Stein;

public class PowerUpManager {
    public static void aktivierePowerUp(PowerUp powerUp, Game game) {
        switch (powerUp.getType()) {
            case 2 -> game.addLeben();
        }
    }
}