package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class StandardBlock implements Block {

    private String name;
    private List<Point> cells;
    private Point pivot;

    private int width;
    private int height; 

  

    public StandardBlock(List<Point> cells, Point pivot) {
        this.cells = cells;
        this.pivot =pivot;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public List<Point> getCells() {
        return cells;
    }

    @Override
    public Block rotateClockwise() {
    List<Point> rotated = new ArrayList<>();
    for (Point p : getCells()) {
        int dx = p.x - pivot.x;
        int dy = p.y - pivot.y;
        int newX = pivot.x + dy;
        int newY = pivot.y - dx;
        rotated.add(new Point(newX, newY));
    }
    return new StandardBlock(rotated, pivot);
}


    @Override
    public Block rotateCounterClockwise() {
         List<Point> rotated = new ArrayList<>();
    for (Point p : getCells()) {
        int dx = p.x + pivot.x;
        int dy = p.y + pivot.y;
        int newX = pivot.x - dy;
        int newY = pivot.y + dx;
        rotated.add(new Point(newX, newY));
    }
    return new StandardBlock(rotated, pivot);
    }


   
    @Override
    public int getWidth() {
        return this.width;  
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public Point getPivot() {
        return this.pivot;
    }

    @Override
    public void setPivot(Point pivot) {
        this.pivot = pivot;
    }

}
