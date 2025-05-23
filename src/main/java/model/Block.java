package model;



import java.util.List;
import java.awt.Point;

public interface Block {
    String getName();
    List<Point> getCells(); 
    Point getPivot();
    void setPivot(Point pivot);
    Block rotateClockwise();
    Block rotateCounterClockwise();
    int getWidth();
    int getHeight();
}