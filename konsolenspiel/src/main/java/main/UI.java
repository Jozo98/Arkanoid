package main;

import java.awt.*;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    public int menuNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        if (gp.gsm.getGameState() == 0) {
            drawMenu();
            if (gp.keyH.upPressed) {
                if (menuNum > 0)
                    menuNum--;
            }
            if (gp.keyH.downPressed) {
                if (menuNum < 2)
                    menuNum++;
            }
            if (menuNum == 2 && gp.keyH.enterPressed) {
                menuNum = 0;
            }
        } else if (gp.gsm.getGameState() == 3) {
            drawLoadGame();
            if (gp.keyH.upPressed) {
                if (gp.ui.menuNum > 0) {
                    gp.ui.menuNum--;
                }
            }
            if (gp.keyH.downPressed) {
                if (gp.ui.menuNum < 4) {
                    gp.ui.menuNum++;
                }
            }
            if (gp.keyH.escapePressed) {
                menuNum = 0;
            }
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

    public void drawLoadGame() {
        g2.setColor(Color.blue);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        String text = "Level 1";
        int x = getCenterX(text);
        int y = gp.tileSize * 5;
        g2.drawString(text, x, y);
        if (menuNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Level 2";
        x = getCenterX(text);
        y += gp.tileSize + gp.tileSize / 2;
        g2.drawString(text, x, y);
        if (menuNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);

        }

        text = "Level 3";
        x = getCenterX(text);
        y += gp.tileSize + gp.tileSize / 2;
        g2.drawString(text, x, y);
        if (menuNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Level 4";
        x = getCenterX(text);
        y += gp.tileSize + gp.tileSize / 2;
        g2.drawString(text, x, y);
        if (menuNum == 3) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Level 5";
        x = getCenterX(text);
        y += gp.tileSize + gp.tileSize / 2;
        g2.drawString(text, x, y);
        if (menuNum == 4) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public int getCenterX(String text) {
        FontMetrics fm = g2.getFontMetrics();
        int length = fm.stringWidth(text);
        return (gp.screenWidth - length) / 2;
    }

}
