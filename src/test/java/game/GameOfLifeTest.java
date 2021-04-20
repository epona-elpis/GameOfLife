package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameOfLifeTest {
    private GameOfLife gameOfLife;

    @BeforeEach
    public void setup(){
        gameOfLife = new GameOfLife.GameOfLifeBuilder()
                .withBoardSize(25,25)
                .withPattern(new int[][]{{11, 12}, {12, 13}, {13, 11}, {13, 12}, {13, 13}})
                .withRule(new AliveRule())
                .build();
    }

    @Test
    public void gliderPattern_repeatsAfterFourTicks() {
        //expect glider has moved diagonally down and right
        List<Cell> expectedGliderPosition = Arrays.asList(new Cell[]{
                new Cell(11+1,12+1) ,
                new Cell(12+1,13+1),
                new Cell(13+1,11+1),
                new Cell(13+1,12+1),
                new Cell(13+1,13+1)});

        //expected glider alive state
        for(Cell expectedCell: expectedGliderPosition){
            expectedCell.setAlive(true);
        }

        List<Cell> livingCellsAfterFourGenerations = gameOfLife.tick(4);

        assertEquals(5, livingCellsAfterFourGenerations.size());
        assertEquals(expectedGliderPosition, livingCellsAfterFourGenerations);
    }

    @Test
    public void gliderPattern_newCellsAreBorn() {
        List<Cell> livingCells = gameOfLife.tick(1);
        assertEquals(5, livingCells.size());
        //new cells born
        assertEquals(new Cell(12,11).setAlive(true), livingCells.get(0));
        assertEquals( new Cell(14,12).setAlive(true), livingCells.get(4));
    }

    @Test
    public void gliderPattern_edges() {
        int numberOfTicksBeforeItRepeatsAtEdge = 47;
        List<Cell> expectedGliderPosition = Arrays.asList(new Cell[]{
                new Cell(23,23) ,
                new Cell(23,24),
                new Cell(24,23),
                new Cell(24,24)});

        for(Cell expectedCell: expectedGliderPosition){
            expectedCell.setAlive(true);
        }

        List<Cell> livingCells = gameOfLife.tick(numberOfTicksBeforeItRepeatsAtEdge);

        assertEquals(4, livingCells.size());
        assertEquals(expectedGliderPosition, livingCells);
    }

}
