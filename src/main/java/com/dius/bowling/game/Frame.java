package com.dius.bowling.game;

class Frame {

    private static final int MAX_PINS = 10;

    private final int roll1;
    private final int roll2;

    Frame(int roll1, int roll2) {
        if (roll1 + roll2 > MAX_PINS) {
            throw new IllegalArgumentException("More than 10 pins are not allowed: " + roll1 + " + " + roll2);
        }
        this.roll1 = roll1;
        this.roll2 = roll2;
    }

    int roll1() {
        return this.roll1;
    }

    int roll2() {
        return this.roll2;
    }

    int score() {
        return this.roll1 + this.roll2;
    }

    boolean isSpare() {
        return !this.isStrike() && (this.roll1 + this.roll2) == MAX_PINS;
    }

    boolean isStrike() {
        return this.roll1 == MAX_PINS;
    }

}
