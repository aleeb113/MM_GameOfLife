package mm.game.of.life;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

class GameOfLife implements Serializable {

    private final int sizeX = GameOfLifeConfig.BOARD_DIMENSION.width;
    private final int sizeY = GameOfLifeConfig.BOARD_DIMENSION.height;
    Cell[][] cells = new Cell[sizeX][sizeY];

    GameOfLife() {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                this.cells[x][y] = new Cell();
            }
        }
    }

    GameOfLife(Cell[][] cells) {
        this();
        this.cells = cells;
    }

    void updateCellsStatus() {
        findNewCellsStatus();
        setCellsStatus();
    }

    private void findNewCellsStatus() {

        int aliveAdjacentCellsCount;
        Set<Position> adjacentCellsPositions;

        for (int x = 0; x < sizeX; x++)
            for (int y = 0; y < sizeY; y++) {

                aliveAdjacentCellsCount = 0;
                adjacentCellsPositions = getAdjacentCellsPositions(x, y);
                for (Position p : adjacentCellsPositions)
                    if (cells[p.getX()][p.getY()].isAlive()) aliveAdjacentCellsCount++;

                if (cells[x][y].isAlive()) {
                    if ((aliveAdjacentCellsCount != 2) && (aliveAdjacentCellsCount != 3))   //Game of Life rule 1
                        cells[x][y].setWillDie(true);
                } else {
                    if (aliveAdjacentCellsCount == 3)                                       //Game of Life rule 2
                        cells[x][y].setWillComeAlive(true);
                }
            }
    }


    private Set<Position> getAdjacentCellsPositions(int x, int y) {
        Set<Position> adjacentCells = new HashSet<>();
        int positionX, positionY;

        for (positionX = x - 1; positionX <= x + 1; positionX++)
            for (positionY = y - 1; positionY <= y + 1; positionY++) {
                if (positionX >= 0 && positionX <= GameOfLifeConfig.BOARD_DIMENSION.width - 1)
                    if (positionY >= 0 && positionY <= GameOfLifeConfig.BOARD_DIMENSION.height - 1)
                        if (!(positionX == x && positionY == y)) adjacentCells.add(new Position(positionX, positionY));
            }
        return adjacentCells;

    }

    private void setCellsStatus() {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                if (cells[x][y].isAlive()) {
                    if (cells[x][y].willDie()) {
                        cells[x][y].setAlive(false);
                        cells[x][y].setWillDie(false);
                    }
                } else {
                    if (cells[x][y].willComeAlive()) {
                        cells[x][y].setAlive(true);
                        cells[x][y].setWillComeAlive(false);
                    }
                }
            }
        }
    }

}
