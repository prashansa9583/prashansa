package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    public Tile(int x,int y){
        setWidth(DiceRollSnake.tileSize);
        setHeight(DiceRollSnake.tileSize);

        setFill(Color.PINK);
        setStroke(Color.BLACK);
    }
}
