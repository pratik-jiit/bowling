package com.dius.bowling.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BowlingGameImpl implements BowlingGame {

    private static final int MAX_FRAMES = 10;
    private static final int MAX_PINS = 10;

    // In case of a spare or strike in the last frame, one additional frame is possible.
    private final List<Frame> frames = new ArrayList<>(MAX_FRAMES + 1);

    private boolean isRoll1 = true;

    private int pinsRoll1 = 0;


    @Override
    public void roll(int noOfPins) {
        beforeRoll();
        executeRoll(noOfPins);
        afterRoll();
    }

    @Override
    public int score() {
        if (frames.stream().allMatch(Frame::isStrike)) {
            return 300;
        }

        return IntStream.range(0, frames.size())
                .map(this::scoreForFrame)
                .reduce(0, Integer::sum);
    }


    private void beforeRoll() {
        // Regular frame.
        if (frames.size() < MAX_FRAMES) {
            return;
        }

        // Extra roll owed to spare in 10th frame.
        if (frames.size() == MAX_FRAMES && frames.get(MAX_FRAMES - 1).isSpare() && isRoll1) {
            return;
        }

        // 2 extra rolls due to Strike in 10th frame.
        if (frames.size() == MAX_FRAMES && frames.get(MAX_FRAMES - 1).isStrike()) {
            return;
        }
        throw new IllegalStateException("Maximum of 10 frames are possible.");
    }

    private void afterRoll() {
        if (frames.size() == MAX_FRAMES && frames.get(MAX_FRAMES - 1).isSpare() && !isRoll1) {
            // Spare - only allows one more roll, so simulate empty second roll to complete the frame.
            roll2(0);
        }
    }

    private void executeRoll(int noOfPins) {
        if (isRoll1) {
            roll1(noOfPins);
        } else {
            roll2(noOfPins);
        }
    }

    private void roll1(int noOfPins) {
        pinsRoll1 = noOfPins;
        isRoll1 = false;
        if (noOfPins == MAX_PINS) {
            // Strike - simulate empty second roll to complete the frame.
            roll2(0);
        }
    }

    private void roll2(int noOfPins) {
        frames.add(new Frame(pinsRoll1, noOfPins));
        isRoll1 = true;
        pinsRoll1 = 0;
    }

    private int scoreForFrame(int i) {
        int result = frames.get(i).score();
        if (i == 0) {
            return result;
        }
        if (frames.get(i - 1).isSpare()) {
            result += frames.get(i).roll1();
        }
        if (frames.get(i - 1).isStrike()) {
            result += frames.get(i).score();
        }
        return result;
    }

}
