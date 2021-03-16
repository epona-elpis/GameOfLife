package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {
    private AliveRule aliveRule = new AliveRule(2, 4, 3);
    private Cell[][] cells;
    private List<Cell> livingCells = new ArrayList<>();

     public Game(int rows, int cols) {
        cells = new Cell[rows][cols];
        for(int i = 0; i < cells.length; i++) {
           for(int j = 0; j < cells[i].length; j++){
               cells[i][j] = new Cell(i,j);
           }
        }
    }

    public void seedGame(int[][] pattern) {
        livingCells = Arrays
            .stream(pattern)
            .map(pos -> {
                Cell livingCell = cells[pos[0]][pos[1]];
                livingCell.setAlive(true);
                return livingCell;
            })
            .collect(Collectors.toList());
   }

   public List<Cell> tick() {
        return tick(1);
    }

    public List<Cell> tick(int ticks) {
        printState(0);
        for (int i = 0; i<ticks; i++) {
            incrementNeighbourCountForCellsAdjacentToLivingCell();
            createNextGeneration();
            printState(i+1);
        }
        return livingCells;
    }

    private void incrementNeighbourCountForCellsAdjacentToLivingCell(){
        for (Cell livingCell: livingCells) {
            for (int i = livingCell.getPos()[0]-1; i < (livingCell.getPos()[0]+2); i++){
                if( i >= 0 && i < cells.length) {
                    for (int j = livingCell.getPos()[1] -1; j < livingCell.getPos()[1] +2; j++) {
                        if ( j >= 0 && j < cells[0].length ) {
                            Cell cell = cells[i][j];
                            if (cell != livingCell) {
                                cell.incrementLivingNeighbour();
                            }
                        }
                    }
                }
            }
        }
    }

    private void createNextGeneration() {
        Cell[] flattened = Stream.of(cells)
                .flatMap(Stream::of)
                .toArray(Cell[]::new);

        livingCells = Arrays.stream(flattened)
                .map(cell -> cell.nextGeneration(aliveRule))
                .filter(c -> c.isAlive())
                .collect(Collectors.toList());
   }

   private void printState(int i) {
        System.out.print("\n generation "+i);
        Arrays.stream(cells).forEach((row) -> {
            System.out.println();
            Arrays.stream(row).forEach((col) -> System.out.print(col));
        });
    }

}
