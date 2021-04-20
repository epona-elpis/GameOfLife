package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Board {
    private final Cell[][] board;
    private List<Cell> livingCells = new ArrayList<>();

    Board(int rows, int cols) {
        board = new Cell[rows][cols];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell(i, j);
            }
        }
    }

    void seedGame(int[][] pattern) {
        livingCells = Arrays
                .stream(pattern)
                .map(pos -> board[pos[0]][pos[1]].setAlive(true))
                .collect(Collectors.toList());
    }


    void incrementACellsLivingNeighbourCount() {
        for (Cell livingCell : livingCells) {
            int[][] neighbouringPositions = livingCell.getNeighbouringBoardPositions();
            for (int i = 0; i < neighbouringPositions.length; i++) {
                Cell neighbour = getNeighbour(neighbouringPositions[i]);
                if (neighbour != null) {
                    neighbour.incrementLivingNeighbour();
                }
            }
        }
    }

     void createNextGeneration(AliveRule aliveRule) {
        Cell[] flattened = Stream.of(board)
                .flatMap(Stream::of)
                .toArray(Cell[]::new);

        livingCells = Arrays.stream(flattened)
                .map(cell -> cell.nextGeneration(aliveRule))
                .filter(c -> c.isAlive())
                .collect(Collectors.toList());
    }

    void printState() {
        Arrays.stream(board).forEach((row) -> {
            System.out.println();
            Arrays.stream(row).forEach((col) -> System.out.print(col));
        });
    }

    List<Cell> getState(){
        return livingCells;
    }

    private Cell getNeighbour(int[] neighbouringPosition) {
        int x = neighbouringPosition[0];
        int y = neighbouringPosition[1];
        if (x < 0 || x >= board.length) {
            return null;
        }
        if (y < 0 || y >= board[0].length) {
            return null;
        }
        return board[x][y];
    }
}
