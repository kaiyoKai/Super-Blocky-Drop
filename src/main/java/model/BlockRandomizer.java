package model;

public interface BlockRandomizer {
    Block nextBlock();
    void reseed(long seed);
}
