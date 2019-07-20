package core;

import javafx.scene.canvas.GraphicsContext;
import libs.Configs;
import libs.Sprite;
import javafx.scene.image.Image;

import java.util.Random;


public class Balloon extends Sprite {

    public int x;
    public int y;

    public Balloon()  {
        super.imgPath = "/core/balloon.png";
        super.setImage(new Image(imgPath));
    }

    public void moveUp(int brickSize) {
        y-=brickSize;
    }

    public void moveDown(int brickSize) {
        y+= brickSize;
    }

    public void moveRight(int brickSize) {
        x+= brickSize;
    }

    public void moveLeft(int brickSize) {
        x-= brickSize;
    }
}
