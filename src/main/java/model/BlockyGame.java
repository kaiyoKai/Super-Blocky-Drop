package model;

import java.awt.Point;
import java.util.Random;
import java.util.Set;

public class BlockyGame implements GameState {

    private Block currentBlock;
    private Grid grid;
    private boolean gameOver;
    private Set<Block> blockSet;
    private Set<Block> blockContainer; // TODO: Change block container into a Queue


    private Point currentPositon;
    private Point spawnPositon;


    
    public BlockyGame(Set<Block> blockSet, Grid grid) {
        this.blockSet=blockSet;

        loadBlockSetIntoContainer(); // TODO: Check if blockSet is valid

        this.grid = grid;

        int spawnX = (grid.getWidth() / 2);
        int spawnY = 0;
        this.spawnPositon = new Point(spawnX, spawnY);
        this.currentPositon= this.spawnPositon;


        getCurrentBlock();

        this.gameOver = false;
    }

    @Override
    public Grid getGrid() {
        return this.grid;
    }

    public void spawnBlock() {
        this.currentBlock= getRandomblock(); // TODO: Handle null or empty blockSet
        if (!grid.canPlaceBlock(currentBlock, spawnPositon.x, spawnPositon.y)) {
            this.gameOver=true; // TODO: Handle null or empty blockSet
        }
        
    }



    @Override
    public Block getCurrentBlock() {
     return currentBlock;
    }
    
    private void loadBlockSetIntoContainer(){
        this.blockContainer = this.blockSet;
    }

    private void refillBlockSet(){
    if (blockContainer == null || blockContainer.isEmpty()) {    
    throw new IllegalStateException("Block container is empty. Cannot refill.");  
        }
        this.blockSet = this.blockContainer;
    }

    
    private Block getRandomblock() {
        if (blockSet == null || blockSet.isEmpty()) {
         refillBlockSet();
        }

        int randomIndex = new Random().nextInt(blockSet.size());
        int i = 0;
        for (Block block : blockSet) {
            if (i == randomIndex) {
                blockContainer.remove(block);
                return block;
            }
            i++;
        }
        throw new IllegalStateException("Something went wrong while picking a random block.");
    }

    

public boolean canMoveDown() {
    return grid.canPlaceBlock(currentBlock, currentPositon.x, currentPositon.y + 1);
}

@Override
public boolean tick() {
        // TODO: Implement game tick logic (move block down, check for lock, clear rows, etc.)
    return false;
}





    //TO DO,needs to return world positon instead of local positon
    @Override
    public Point getBlockPosition() {
        if (currentBlock == null) {
            throw new IllegalStateException("Current block is not set.");
        }
        Point pivot = currentBlock.getPivot();
        return new Point(pivot.x, pivot.y);
    }



    @Override
    public boolean moveLeft() {
        if (currentBlock == null) {
            throw new IllegalStateException("Current block is not set.");
        }
        Point pivot = currentBlock.getPivot();
        int newX = pivot.x - 1;
        int newY = pivot.y;
        currentBlock.setPivot(new Point(newX, newY));
        return grid.placeBlock(currentBlock, newX, newY);
    }

    @Override
    public boolean moveRight() {
        if (currentBlock == null) {
            throw new IllegalStateException("Current block is not set.");
        }
        Point pivot = currentBlock.getPivot();
        int newX = pivot.x + 1;
        int newY = pivot.y;
        currentBlock.setPivot(new Point(newX, newY));
        return grid.placeBlock(currentBlock, newX, newY);
    }

    @Override
    public boolean rotateClockwise() {
        if (currentBlock == null) {
            throw new IllegalStateException("Current block is not set.");
        }
        Block rotatedBlock = currentBlock.rotateClockwise(); // TODO: Check for collision after rotation
        Point pivot = currentBlock.getPivot();
        return grid.placeBlock(rotatedBlock, pivot.x, pivot.y); // TODO: Update currentBlock if successful
       
    }

    @Override
    public boolean rotateCounterClockwise() {
        if (currentBlock == null) {
            throw new IllegalStateException("Current block is not set.");
        }
        
        Block rotatedBlock = currentBlock.rotateCounterClockwise();
        Point pivot = currentBlock.getPivot();

        return grid.placeBlock(rotatedBlock, pivot.x, pivot.y);
    }

    @Override
    public boolean drop() {
        if (currentBlock == null) {
            throw new IllegalStateException("Current block is not set.");
        }

        Point pivot = currentBlock.getPivot();


        if (!grid.canPlaceBlock(currentBlock,pivot.x,pivot.y-1)) {
            lockDrop(); // TODO: Implement lockDrop logic
        }
    
        boolean placed = grid.placeBlock(currentBlock, pivot.x, pivot.y-1);
        return placed;
    }


    private void lockDrop(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lockDrop'");
    }



    @Override
    public boolean softDrop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'softDrop'");
    }

    

    @Override
    public boolean isGameOver() {
        return this.gameOver;
        }


}
