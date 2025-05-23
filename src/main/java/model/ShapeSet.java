package model;

import java.util.List;

public interface ShapeSet {
    Block getNextBlock(long seed); // for Replay/Daily deterministic
    List<Block> getAllBlocks();    // for preview or debugging purposes
}
