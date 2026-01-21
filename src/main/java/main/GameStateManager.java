package main;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GameStateManager {

    GamePanel gp;
    KeyHandler keyH;
    private int gameState = 0;
    public final int titleState = 0;
    public final int playState = 1;
    public final int exitState = 2;
    public final int loadState = 3;
    public final int gameOverState = 4;
    ObjectMapper objectMapper = new ObjectMapper();

    public GameStateManager(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
    }

    public void updateGameState() throws IOException {
        if (gameState == titleState && keyH.enterPressed) {

            if (gp.ui.menuNum == 0) {
                objectMapper.writeValue(new File("src/main/resources/aktuellesLevel.json"), 0);
                gp.game.setAktuellesLevel(objectMapper.readValue(new File("src/main/resources/aktuellesLevel.json"), Integer.class));
                gp.game.reset();
                gameState = playState;
            }
            if (gp.ui.menuNum == 1) {
                gameState = playState;
                gp.game.reset();
                System.out.println(1);
            }
            if (gp.ui.menuNum == 2) {
                gameState = exitState;
            }
        }

        if (gameState == playState) {
            if (keyH.enterPressed && gp.ui.menuNum == 2) {
                gameState = titleState;
            }
            if (gp.game.getBall().getLeben() == 0) {
                gameState = gameOverState;
            }
        }

        if (gameState == gameOverState) {
           if (keyH.enterPressed && gp.ui.menuNum == 0) {
               gp.game.reset();
               gameState = playState;
           }
           if (keyH.enterPressed && gp.ui.menuNum == 1) {
               gameState = titleState;
               System.out.println(2);
           }
        }

        if (gameState == loadState) {
            if (keyH.escapePressed) {
                gameState = titleState;
            }
        }
    }

    public int getGameState() {
        return gameState;
    }
}
