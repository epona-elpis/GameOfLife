package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CellTest {

    private AliveRule aliveRule = new AliveRule();

    @Test
    public void underpopulatedCellDies_noNeighbours(){
        Cell cellWithNoNeighbours = new Cell(1,1);
        cellWithNoNeighbours.setAlive(true);
        assertFalse(cellWithNoNeighbours.nextGeneration(aliveRule).isAlive());
    }

    @Test
    public void underpopulatedCellDies_oneNeighbour(){
        Cell cellWithOneNeighbour = new Cell(1,1);
        cellWithOneNeighbour.setAlive(true);
        cellWithOneNeighbour.incrementLivingNeighbour();
        assertFalse(cellWithOneNeighbour.nextGeneration(aliveRule).isAlive());
    }

    @Test
    public void liveCellsDontDieWhenTheyHaveTwoNeighbours(){
        Cell cellWithTwoNeighbours = new Cell(1,1);
        cellWithTwoNeighbours.setAlive(true);
        cellWithTwoNeighbours.incrementLivingNeighbour();
        cellWithTwoNeighbours.incrementLivingNeighbour();
        assertTrue(cellWithTwoNeighbours.nextGeneration(aliveRule).isAlive());
    }

    @Test
    public void liveCellsDontDieWhenTheyHaveThreeNeighbours(){
        Cell cellWithThreeNeighbours = new Cell(1,1);
        cellWithThreeNeighbours.setAlive(true);
        cellWithThreeNeighbours.incrementLivingNeighbour();
        cellWithThreeNeighbours.incrementLivingNeighbour();
        cellWithThreeNeighbours.incrementLivingNeighbour();
        assertTrue(cellWithThreeNeighbours.nextGeneration(aliveRule).isAlive());
    }

    @Test
    public void overcrowedCellDies(){
        Cell cellWithFourNeighbours = new Cell(1,1);
        cellWithFourNeighbours.setAlive(true);
        cellWithFourNeighbours.incrementLivingNeighbour();
        cellWithFourNeighbours.incrementLivingNeighbour();
        cellWithFourNeighbours.incrementLivingNeighbour();
        cellWithFourNeighbours.incrementLivingNeighbour();
        assertFalse(cellWithFourNeighbours.nextGeneration(aliveRule).isAlive());
    }

    @Test
    public void deadCellsWithExactlyThreeNeighboursBecomeAlive(){
        Cell deadCellWithExactlyThreeNeighbours = new Cell(1,1);
        deadCellWithExactlyThreeNeighbours.setAlive(false);
        deadCellWithExactlyThreeNeighbours.incrementLivingNeighbour();
        deadCellWithExactlyThreeNeighbours.incrementLivingNeighbour();
        deadCellWithExactlyThreeNeighbours.incrementLivingNeighbour();
        assertTrue(deadCellWithExactlyThreeNeighbours.nextGeneration(aliveRule).isAlive());
    }

    @Test
    public void deadCellsWithLessThanThreeNeighboursStayDead(){
        Cell deadCellWithLessThanThreeNeighbours = new Cell(1,1);
        deadCellWithLessThanThreeNeighbours.setAlive(false);
        deadCellWithLessThanThreeNeighbours.incrementLivingNeighbour();
        deadCellWithLessThanThreeNeighbours.incrementLivingNeighbour();
        assertFalse(deadCellWithLessThanThreeNeighbours.nextGeneration(aliveRule).isAlive());
    }

    @Test
    public void deadCellsWithMoreThanThreeNeighboursStayDead(){
        Cell deadCellWithLessThanThreeNeighbours = new Cell(1,1);
        deadCellWithLessThanThreeNeighbours.setAlive(false);
        deadCellWithLessThanThreeNeighbours.incrementLivingNeighbour();
        deadCellWithLessThanThreeNeighbours.incrementLivingNeighbour();
        deadCellWithLessThanThreeNeighbours.incrementLivingNeighbour();
        deadCellWithLessThanThreeNeighbours.incrementLivingNeighbour();
        assertFalse(deadCellWithLessThanThreeNeighbours.nextGeneration(aliveRule).isAlive());
    }
}
