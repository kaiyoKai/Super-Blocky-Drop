package model;

public interface Grid {
    int getWidth();
    int getHeight();
    boolean isCellOccupied(int x, int y);
    boolean canPlaceBlock(Block block, int x, int y);
    Boolean placeBlock(Block block, int x, int y);
    boolean isInsideBounds(int x, int y);
    int clearFullRows();
    boolean[][] getState();
}