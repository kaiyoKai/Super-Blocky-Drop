package model;

public interface ScoringSystem {
    void onLineClear(int lines);
    void onDrop(int cellsDropped);
    int getScore();
}
