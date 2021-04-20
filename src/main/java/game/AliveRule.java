package game;

class AliveRule {
    private final static int OVER_POPULATED = 4;
    private final static int MINIMUM_NEIGHBOURS = 2;
    private final static int NEIGHBOURS_REQUIRED_FOR_DEAD_CELL_TO_BE_BORN = 3;

    boolean isAlive(boolean currentAliveState, int neighbours){
        if (currentAliveState ){
            return neighbours < OVER_POPULATED && neighbours >= MINIMUM_NEIGHBOURS;
        }
        return neighbours == NEIGHBOURS_REQUIRED_FOR_DEAD_CELL_TO_BE_BORN;
    }

}
