package game;

class AliveRule {
    private final int overPopulated;
    private final int minimumNeighbours;
    private final int neighboursRequiredForDeadCellToBeBorn;

    AliveRule(int minimumNeighbours, int overPopulated, int neighboursRequiredForDeadCellToBeBorn) {
        this.minimumNeighbours = minimumNeighbours;
        this.overPopulated = overPopulated;
        this.neighboursRequiredForDeadCellToBeBorn = neighboursRequiredForDeadCellToBeBorn;
    }

    boolean isAlive(boolean currentAliveState, int neighbours){
        if (currentAliveState ){
            return neighbours < overPopulated && neighbours >= minimumNeighbours;
        }
        return neighbours == neighboursRequiredForDeadCellToBeBorn;
    }

}
