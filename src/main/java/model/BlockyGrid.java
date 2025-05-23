    package model;

    import java.awt.Point;
    import java.util.List;


    // Internal layout: cells[row][column] = cells[y][x]
    // External methods always use x, y for consistency

    public class BlockyGrid implements Grid {

        private boolean[][] cells;
        private int height;
        private int width;
        
        public BlockyGrid(boolean[][] cells, int height, int width) {
            this.cells = new boolean[height][width];
            this.width = width;
            this.height = height;
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
    public boolean isCellOccupied(int x, int y) {
        return cells[y][x];
    }




    public boolean placeBlock(Block block, int x,int y) {
        if (!canPlaceBlock(block, x, y)) {
            return false;
        }
        List<Point> cellsToPlace = block.getCells();
        
        Point pivot = block.getPivot();
        
        for (Point cell : cellsToPlace) {
            int gridX = x + cell.x - pivot.x;
            int gridY = y + cell.y - pivot.y;
            cells[gridY][gridX] = true;
        }

        return true;
    }


    @Override
    public boolean canPlaceBlock(Block block, int x, int y) throws IllegalArgumentException{ 
        List<Point> cellsToPlace = block.getCells();
        Point pivot = block.getPivot();

        for (Point cell : cellsToPlace) {
            int gridX = x + cell.x - pivot.x;
            int gridY = y + cell.y - pivot.y;

            if (!isInsideBounds(gridX, gridY)) {
                return false;
            }
            if (cells[gridY][gridX]) {
                return false;
            }
        }
    return true;
    }




        @Override
        public boolean isInsideBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    @Override
    public int clearFullRows() {
        int clearedRows = 0;

        for (int y = height - 1; y >= 0; y--) {
            boolean full = true;
            for (int x = 0; x < width; x++) {
                if (!cells[y][x]) {
                    full = false;
                    break;
                }
            }

            if (full) {
                // delete the line and pull the lines above below
                for (int row = y; row > 0; row--) {
                    for (int col = 0; col < width; col++) {
                        cells[row][col] = cells[row - 1][col];
                    }

                }
                for (int col = 0; col < width; col++) {
                    cells[0][col] = false;
                }

                clearedRows++;
                y++;
            }
        }

        return clearedRows;
    }

        
        @Override
        public boolean[][] getState() {
            boolean[][] copy = new boolean[height][width];
            for (int i = 0; i < height; i++) {
                System.arraycopy(cells[i], 0, copy[i], 0, width);
            }
            return copy;
        }

    
        
        
    }



