package core;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import libs.Configs;
import libs.CoreFunc;
import libs.GameText;

import java.util.ArrayList;

public class GameCore implements CoreFunc {

    // Main Game variables should be here
    int[][] level1 = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 1, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 1, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 1, 1, 0, 1, 1},
            {0, 0, 1, 0, 0, 1, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    Balloon balloon = new Balloon();

    int bricksize = Configs.appHeight / level1.length;

    @Override
    public void init(GraphicsContext gc) {
        // initialize objects (initial position, initial size, etc)

        balloon.resize(bricksize - 20, bricksize - 10);

        // find the position in the first column that is zero
        int startPos = 0;

        for (int i=0; i<level1.length; i++) {
            if (level1[i][0] == 0)
                startPos = bricksize*i;
        }

        balloon.x = startPos + 8;
        balloon.y = 8;
        balloon.render(gc, balloon.x, balloon.y);

        for (int i=0; i<level1[0].length; i++) {
            for (int j=0; j<level1.length; j++) {
                Brick brick = new Brick();
                brick.resize(bricksize, bricksize);
                if (level1[i][j] == 1) {
                    brick.render(gc, i*bricksize, j*bricksize);
                }
            }
        }
    }

    public void refreshBackground(GraphicsContext gc) {
        for (int i=0; i<level1[0].length; i++) {
            for (int j=0; j<level1.length; j++) {
                if (level1[i][j] == 0) {
                    gc.setFill(Color.WHITE);
                    gc.clearRect(i*bricksize, j*bricksize, bricksize, bricksize);
                }
            }
        }
    }

    @Override
    public void animate(GraphicsContext gc, int time, ArrayList input) {
        // any animations and keyboard controls should be here

        int xpos = balloon.x / bricksize;
        int ypos = balloon.y / bricksize;

        refreshBackground(gc);

        if (input.contains("UP")) {
            if (ypos > 0) {
                if (level1[xpos][ypos-1] == 0) {
                    balloon.moveUp(bricksize);
                }
            }
        }
        if (input.contains("DOWN")) {
            if (ypos < level1.length - 1) {
                if (level1[xpos][ypos+1] == 0) {
                    balloon.moveDown(bricksize);
                }
            }
        }
        if (input.contains("LEFT")) {
            if (xpos > 0) {
                if (level1[xpos-1][ypos] == 0) {
                    balloon.moveLeft(bricksize);
                }
            }
        }

        if (input.contains("RIGHT")) {
            if (xpos < level1.length) {
                if (level1[xpos+1][ypos] == 0) {
                    balloon.moveRight(bricksize);
                }
            }
        }
        input.clear();

        if (ypos == level1.length - 1) {
            GameText gameText = new GameText(Color.RED, Color.RED);
            gameText.setText(gc, "You solve the maze", 40, 100, 90);
        }

        balloon.render(gc, balloon.x, balloon.y);
    }

    @Override
    public void mouseClick(MouseEvent e) {
        // mouse click event here
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        // mouse move event here
    }
}
