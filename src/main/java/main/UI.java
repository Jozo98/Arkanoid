package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {

    private GamePanel gp;
    private Graphics2D g2;
    private int menuNum = 0;
    private BufferedImage ball1;

    public UI(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;
        GameState gameState = gp.gsm.getGameState();

        switch (gameState) {

            case TITLE:
                drawMenu();
                if (gp.keyH.upPressed && menuNum > 0) {
                    menuNum--;
                } else if (gp.keyH.downPressed && menuNum < 2) {
                    menuNum++;
                }
                if (gp.keyH.escapePressed || gp.keyH.enterPressed) {
                    menuNum = 0;
                    gp.keyH.escapePressed = false;
                    gp.keyH.enterPressed = false;
                }
                if (gp.keyH.downPressed || gp.keyH.upPressed) {
                    gp.keyH.downPressed = false;
                    gp.keyH.upPressed = false;
                }
                break;

            case PLAY:
                drawHUD();
                break;

//            case LOAD:
//                drawLoadGame();
//                if (gp.keyH.enterPressed) {
//                    menuNum = 0;
//                } else if (gp.keyH.upPressed && menuNum > 0) {
//                        menuNum--;
//                } else if (gp.keyH.downPressed && menuNum < 4) {
//                        menuNum++;
//                }
//                if (gp.keyH.downPressed || gp.keyH.upPressed) {
//                    gp.keyH.downPressed = false;
//                    gp.keyH.upPressed = false;
//                }
//                break;

            case GAME_OVER:
                drawGameOver();
                if (gp.keyH.upPressed && menuNum > 0) {
                        menuNum--;
                } else if (gp.keyH.downPressed && menuNum < 1) {
                    menuNum++;
                }
                break;
        }
    }

    public void drawMenu() {
        g2.setColor(Color.blue);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Arkanoid";

        int x = getCenterX(text);
        int y = gp.tileSize * 3;

        g2.setColor(Color.black);
        g2.drawString(text, x, y);

        g2.setColor(Color.white);
        g2.drawString(text, x + 5, y + 5);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        text = "NEW GAME";
        x = getCenterX(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
        if (menuNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getCenterX(text);
        y += gp.tileSize + gp.tileSize / 2;
        g2.drawString(text, x, y);
        if (menuNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);

        }

        text = "QUIT GAME";
        x = getCenterX(text);
        y += gp.tileSize + gp.tileSize / 2;
        g2.drawString(text, x, y);
        if (menuNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

//    public void drawLoadGame() {
//        g2.setColor(Color.blue);
//        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
//
//        g2.setColor(Color.white);
//        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
//        String text = "Level 1";
//        int x = getCenterX(text);
//        int y = gp.tileSize * 5;
//        g2.drawString(text, x, y);
//        if (menuNum == 0) {
//            g2.drawString(">", x - gp.tileSize, y);
//        }
//
//        text = "Level 2";
//        x = getCenterX(text);
//        y += gp.tileSize + gp.tileSize / 2;
//        g2.drawString(text, x, y);
//        if (menuNum == 1) {
//            g2.drawString(">", x - gp.tileSize, y);
//
//        }
//
//        text = "Level 3";
//        x = getCenterX(text);
//        y += gp.tileSize + gp.tileSize / 2;
//        g2.drawString(text, x, y);
//        if (menuNum == 2) {
//            g2.drawString(">", x - gp.tileSize, y);
//        }
//
//        text = "Level 4";
//        x = getCenterX(text);
//        y += gp.tileSize + gp.tileSize / 2;
//        g2.drawString(text, x, y);
//        if (menuNum == 3) {
//            g2.drawString(">", x - gp.tileSize, y);
//        }
//
//        text = "Level 5";
//        x = getCenterX(text);
//        y += gp.tileSize + gp.tileSize / 2;
//        g2.drawString(text, x, y);
//        if (menuNum == 4) {
//            g2.drawString(">", x - gp.tileSize, y);
//        }
//    }

    public int getCenterX(String text) {
        FontMetrics fm = g2.getFontMetrics();
        int length = fm.stringWidth(text);
        return (gp.screenWidth - length) / 2;
    }

    public void drawHUD() {
        try {
            ball1 = ImageIO.read(getClass().getResourceAsStream("/Ball.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Mit ChatGPT vereinfacht...
        int ballSize = gp.tileSize / 2;
        int startX = 0;
        int y = gp.tileSize * 15 + gp.tileSize / 2;

        for (int i = 0; i < gp.game.getLeben(); i++) {
            g2.drawImage(ball1, startX + i * ballSize, y, ballSize, ballSize, null);
        }
        //Ende
    }

    public void drawGameOver() {
        g2.setColor(Color.red);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setColor(Color.black);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Game Over";
        int x = getCenterX(text);
        int y = gp.tileSize * 5;
        g2.drawString(text, x, y);
        g2.setColor(Color.white);
        g2.drawString(text, x + 5, y + 5);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        text = "TRY AGAIN";
        x = getCenterX(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
        if (menuNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        text = "BACK TO MENU";
        x = getCenterX(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
        if (menuNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public int getMenuNum() {
        return menuNum;
    }
}