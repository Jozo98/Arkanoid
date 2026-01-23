package main;


import java.io.IOException;

public class GameStateManager {

    private GamePanel gp;
    private KeyHandler keyH;
    private GameState gameState;

    public GameStateManager(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.gameState = GameState.TITLE;
    }

    public void updateGameState() throws IOException {

        int menuNum = gp.ui.getMenuNum();

        switch (gameState) {
            case TITLE:
                if (!keyH.enterPressed) {
                    return;
                }
                if (menuNum == 0) {
                    gp.game.startNewGame();
                    gameState = GameState.PLAY;
                } else if (menuNum == 1) {
                    gameState = GameState.PLAY;
                    gp.game.reset();
                } else if (menuNum == 2) {
                    gameState = GameState.EXIT;
                }
                break;
            case PLAY:
                if (gp.game.isGameOver()) {
                    gameState = GameState.GAME_OVER;
                } else if (keyH.escapePressed) {
                    gameState = GameState.TITLE;
                }
                break;
            case GAME_OVER:
                if (!keyH.enterPressed) {
                    return;
                }
                if (menuNum == 0) {
                    gp.game.reset();
                    gameState = GameState.PLAY;
                } else if (menuNum == 1) {
                    gameState = GameState.TITLE;
                }
                break;
//            case LOAD:
//                if (keyH.escapePressed) {
//                    gameState = GameState.TITLE;
//                }
//                break;
        }
    }

    public GameState getGameState() {
        return gameState;
    }
}
