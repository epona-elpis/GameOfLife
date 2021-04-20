package game;

class AliveRule {
    private final static int OVER_POPULATED = 4;
    private final static int UNDER_POULATED = 1;
    private final static int NEIGHBOURS_REQUIRED_FOR_DEAD_CELL_TO_BE_BORN = 3;

    boolean isAlive(boolean currentAliveState, int neighbours){
        if (currentAliveState ){
            return neighbours < OVER_POPULATED && neighbours > UNDER_POULATED;
        }
        return neighbours == NEIGHBOURS_REQUIRED_FOR_DEAD_CELL_TO_BE_BORN;
    }

}
