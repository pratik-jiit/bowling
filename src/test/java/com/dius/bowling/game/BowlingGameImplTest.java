package com.dius.bowling.game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BowlingGameImplTest {
    private BowlingGame game;

    @Before
    public void setUp() throws Exception {
        this.game = new BowlingGameImpl();
    }

    @Test
    public void roll1And0ShouldScore1() throws Exception {
        game.roll(4);
        game.roll(0);
        assertEquals(4, game.score());
    }

    @Test
    public void roll4And4ShouldScore8() throws Exception {
        game.roll(2);
        game.roll(4);
        assertEquals(6, game.score());
    }

    @Test
    public void roll4And6And5And0ShouldScore20() throws Exception {
        game.roll(7);
        game.roll(3);
        game.roll(3);
        game.roll(0);
        assertEquals(16, game.score());
    }

    @Test
    public void roll10And5And4ShouldScore28() throws Exception {
        game.roll(10);
        game.roll(1);
        game.roll(3);
        assertEquals(18, game.score());
    }

    @Test
    public void roll10FramesShouldBeFine() throws Exception {
        this.rollNTimesNoOfPins(20, 1);
        assertEquals(20, game.score());
    }

    @Test(expected = IllegalStateException.class)
    public void rollMoreThan10FramesShouldThrowIllegalStateException() throws Exception {
        this.rollNTimesNoOfPins(21, 1);
    }

    @Test
    public void rollingSpareInLastFrameShouldAllowOneMoreBowl() throws Exception {
        this.rollNTimesNoOfPins(19, 1);
        game.roll(9);
        game.roll(1);
        assertEquals(30, game.score());
    }

    @Test(expected = IllegalStateException.class)
    public void rollingSpareInLastFrameShouldNotAllowTwoMoreBowls() throws Exception {
        this.rollNTimesNoOfPins(19, 1);
        game.roll(9);
        game.roll(1);
        game.roll(1);
    }

    @Test
    public void rollingStrikeInLastFrameShouldAllowTwoMoreBowls() throws Exception {
        this.rollNTimesNoOfPins(18, 1);
        game.roll(10);
        game.roll(1);
        game.roll(1);
        assertEquals(32, game.score());
    }

    @Test(expected = IllegalStateException.class)
    public void rollingStrikeInLastFrameShouldNotAllowThreeMoreBowls() throws Exception {
        this.rollNTimesNoOfPins(18, 1);
        game.roll(10);
        game.roll(1);
        game.roll(1);
        game.roll(1);
    }

    @Test
    public void allStrikesShouldScore300() throws Exception {
        this.rollNTimesNoOfPins(11, 10);
        assertEquals(300, game.score());
    }

    private void rollNTimesNoOfPins(int times, int noOfPins) {
        for (int i = 0; i < times; i++) {
            game.roll(noOfPins);
        }
    }
}
