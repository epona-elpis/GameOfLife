package game;

import java.util.Objects;

class Cell {
    private int row;
    private int col;
    private boolean alive;
    private int livingNeighbours;

    Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    int[] getPos() {
        return new int[] {row,col};
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }

    void incrementLivingNeighbour() {
        livingNeighbours +=1;
    }

    void setAlive(boolean alive) {
        this.alive=alive;
    }

    boolean isAlive(){
        return this.alive;
    }

    Cell nextGeneration(AliveRule aliveRule) {
        alive = aliveRule.isAlive(alive, livingNeighbours);
        livingNeighbours = 0;
        return this;
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
