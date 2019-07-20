package core;

import javafx.scene.image.Image;
import libs.Sprite;


public class Brick extends Sprite {

    public Brick()  {
        super.imgPath = "/core/brick.png";
        super.setImage(new Image(imgPath));
    }

}
