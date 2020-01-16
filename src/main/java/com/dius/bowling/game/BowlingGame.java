package com.dius.bowling.game;

/**
 * Interface representing a bowling game.
 */
public interface BowlingGame {
    /**
     * Roll a specified number of pins.
     * @param noOfPins The number of pins rolled.
     */
    void roll(int noOfPins);

    /**
     * Get the current score.
     * @return The current score.
     */
    int score();
}
