package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

abstract class Entitaet {

    public double x, y;
    public int geschwindigkeit;
    GamePanel gp;
    KeyHandler keyH;

    public void setDefaultValues() {

    }

    public void update() {

    }

    public void draw(Graphics2D g) {

    }
}
