package game;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private Game game ;

    @BeforeEach
    public void setup(){
        game = new Game(25, 25);
        game.seedGame(new int[][]{{11,12},{12,13},{13,11},{13,12},{13,13}});
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

        List<Cell> livingCellsAfterFourGenerations = game.tick(4);

        assertEquals(5, livingCellsAfterFourGenerations.size());
        assertEquals(expectedGliderPosition, livingCellsAfterFourGenerations);
    }

    @Test
    public void gliderPattern_newCellsAreBorn() {
        List<Cell> livingCells = game.tick();
        assertEquals(5, livingCells.size());

        //new cells born
        assertArrayEquals( new int[]{12,11},livingCells.get(0).getPos());
        assertArrayEquals( new int[]{14,12},livingCells.get(4).getPos());
    }

    @Test
    public void gliderPattern_edges() {
        int numberOfTicksBeforeItRepeatsAtEdge = 47;
        List<Cell> expectedGliderPosition = Arrays.asList(new Cell[]{
                new Cell(23,23) ,
                new Cell(23,24),
                new Cell(24,23),
                new Cell(24,24)});

        //expected glidergit st alive state
        for(Cell expectedCell: expectedGliderPosition){
            expectedCell.setAlive(true);
        }

        List<Cell> livingCells = game.tick(numberOfTicksBeforeItRepeatsAtEdge);

        assertEquals(4, livingCells.size());
        assertEquals(expectedGliderPosition, livingCells);
    }

}
