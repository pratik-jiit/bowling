package com.dius.bowling;

import com.dius.bowling.game.BowlingGame;
import com.dius.bowling.game.BowlingGameImpl;

import java.util.Arrays;

public class Run {

    public static void main(String[] args) {
        System.out.println("Simulating...");
        System.out.println();

        simulateRolls(1, 6);
        simulateRolls(3, 7, 4, 0);
        simulateRolls(10, 2, 6);
        simulateRolls(10, 10, 10);
    }

    private static void simulateRolls(int... rolls) {
        System.out.println("Starting now");
        BowlingGame game = new BowlingGameImpl();
        Arrays.stream(rolls)
                .map(i -> {
                    System.out.println("Rolling.. " + i);
                    return i;
                })
                .forEach(game::roll);
        System.out.println("Score is: " + game.score());
        System.out.println();
    }

}
