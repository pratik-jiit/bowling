package com.dius.bowling.game;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FrameTest {

    @Test
    public void roll1And1ShouldNeitherBeSpareNorStrike() throws Exception {
        Frame frame = new Frame(1, 1);
        assertFalse(frame.isSpare());
        assertFalse(frame.isStrike());
    }

    @Test
    public void roll5And5ShouldBeSpare() throws Exception {
        Frame frame = new Frame(5, 5);
        assertTrue(frame.isSpare());
        assertFalse(frame.isStrike());
    }

    @Test
    public void roll10And0ShouldBeStrike() throws Exception {
        Frame frame = new Frame(10, 0);
        assertFalse(frame.isSpare());
        assertTrue(frame.isStrike());
    }

    @Test(expected = IllegalArgumentException.class)
    public void rollingTooManyPinsShouldThrowInvalidArgumentException() throws Exception {
        new Frame(10, 1);
    }
}
