package main;

import java.awt.event.KeyEvent;

public class GameStateManager {

    GamePanel gp;
    KeyHandler keyH;
    private int gameState = 0;
    public final int titleState = 0;
    public final int playState = 1;
    public final int exitState = 2;
    public final int loadState = 3;

    public GameStateManager(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
    }

    public void updateGameState() {
        if (gameState == titleState && keyH.enterPressed) {

            if (gp.ui.menuNum == 0) {
                gameState = playState;
            }
            if (gp.ui.menuNum == 1) {
                gameState = loadState;
            }
            if (gp.ui.menuNum == 2) {
                gameState = exitState;
            }
        }

        if (gameState == playState) {
            if (keyH.enterPressed && gp.ui.menuNum == 1) {
                gameState = titleState;
            }
        }

        if (gameState == loadState && keyH.escapePressed) {
            gameState = titleState;
        }
    }

    public int getGameState() {
        return gameState;
    }
}
