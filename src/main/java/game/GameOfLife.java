package game;

import java.util.List;

public class GameOfLife {
    private final AliveRule aliveRule;
    private final Board board;

    public GameOfLife(Board board, AliveRule aliveRule){
        this.board = board;
        this.aliveRule = aliveRule;
    }

    public List<Cell> tick(int ticks) {
        for (int i = 1; i <= ticks; i++) {
            board.incrementACellsLivingNeighbourCount();
            board.createNextGeneration(aliveRule);
            System.out.print("\n generation" +i);
            board.printState();
        }
        return board.getState();
    }

    static class GameOfLifeBuilder{
        private int rows;
        private int cols;
        private int[][] pattern;
        private AliveRule aliveRule;

        GameOfLifeBuilder withBoardSize(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            return this;
        }

        GameOfLifeBuilder withPattern(int[][] pattern){
            this.pattern=pattern;
            return this;
        }

        public GameOfLifeBuilder withRule(AliveRule aliveRule) {
            this.aliveRule = aliveRule;
            return this;
        }

        GameOfLife build(){
            Board board  = new Board(rows, cols);
            board.seedGame(pattern);
            return new GameOfLife(board, aliveRule);
        }
    }

    public static void main(String[] args) {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder()
                .withBoardSize(25,25)
                .withPattern(new int[][]{{11, 12}, {12, 13}, {13, 11}, {13, 12}, {13, 13}})
                .withRule(new AliveRule())
                .build();

        gameOfLife.tick(4);
    }

}
