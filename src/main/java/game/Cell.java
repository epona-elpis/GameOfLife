package game;

import java.util.Objects;

class Cell {
    private final int row;
    private final int col;
    private boolean alive;
    private int livingNeighbours;

    Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    void incrementLivingNeighbour() {
        livingNeighbours +=1;
    }

    Cell setAlive(boolean alive) {
        this.alive=alive;
        return this;
    }

    boolean isAlive(){
        return this.alive;
    }

    Cell nextGeneration(AliveRule aliveRule) {
        alive = aliveRule.isAlive(alive, livingNeighbours);
        livingNeighbours = 0;
        return this;
    }

    int[][] getNeighbouringBoardPositions() {
        return new int[][]{{row - 1, col - 1}, {row - 1, col}, {row - 1, col + 1},
                {row, col - 1}, {row, col + 1},
                {row + 1, col - 1}, {row + 1, col}, {row + 1, col + 1}};
    }

    @Override
    public String toString() {
        if (alive) {
            return "X";
        }
        return ".";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Cell)) {
            return false;
        }

        Cell other = (Cell) o;
        return this.row == other.row &&
                this.col == other.col &&
                this.alive == other.alive &&
                this.livingNeighbours == other.livingNeighbours;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, alive, livingNeighbours);
    }

}
