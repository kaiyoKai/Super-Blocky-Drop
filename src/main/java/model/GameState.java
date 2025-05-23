package model;

import java.awt.Point;  

public interface GameState{
    Grid getGrid();
    Block getCurrentBlock();
    Point getBlockPosition();
    boolean moveLeft();
    boolean moveRight();
    boolean rotateClockwise();
    boolean rotateCounterClockwise();
    boolean drop();
    boolean softDrop();
    boolean tick(); 
    boolean isGameOver();
}
