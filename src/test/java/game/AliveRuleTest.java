package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AliveRuleTest {

    @Test
    public void deadCellsAreBorn(){
        AliveRule aliveRule = new AliveRule(2,4, 3);
        assertTrue( aliveRule.isAlive(false, 3));
    }

    @Test
    public void deadCellsRemainDead(){
        AliveRule aliveRule = new AliveRule(2,4, 3);
        assertFalse( aliveRule.isAlive(false, 4));
        assertFalse( aliveRule.isAlive(false, 4));
    }

    @Test
    public void livingCellsDie(){
        AliveRule aliveRule = new AliveRule(2,4, 3);
        assertFalse( aliveRule.isAlive(true, 4));
        assertFalse( aliveRule.isAlive(true, 0));
    }

    @Test
    public void livingCellsRemainALive(){
        AliveRule aliveRule = new AliveRule(2,4, 3);
        assertTrue( aliveRule.isAlive(true, 2));
        assertTrue( aliveRule.isAlive(true, 3));
    }

}
