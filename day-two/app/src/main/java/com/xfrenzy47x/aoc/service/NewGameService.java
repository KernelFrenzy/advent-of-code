package com.xfrenzy47x.aoc.service;

import java.io.File;
import java.util.Scanner;

public class NewGameService {
    final int ROCK = 1;
    final int PAPER = 2;
    final int SCISSOR = 3;

    final String THEIR_ROCK = "A";
    final String THEIR_PAPER = "B";
    final String THEIR_SCISSOR = "C";

    final String I_LOSE = "X";
    final String I_DRAW = "Y";
    final String I_WIN = "Z";
    int score;

    final int LOSS_SCORE = 0;
    final int DRAW_SCORE = 3;
    final int WIN_SCORE = 6;

    int theirOption;
    int myOption;

    public NewGameService(File input) {
        try (Scanner scanner = new Scanner(input)) {
            while(scanner.hasNext()) {
                String line = scanner.nextLine();
                calculateScore(line.split(" "));
            }
        } catch (Exception ex) {
            // Whoops :D
        }
    }

    private void calculateScore(String[] options) {
        setOptions(options[1], options[0]);
        score += myOption;

        if (myOption == DRAW_SCORE) {
            score += theirOption;
        } else if (myOption == WIN_SCORE) {
            switch (theirOption) {
                case SCISSOR -> score += ROCK;
                case PAPER -> score += SCISSOR;
                case ROCK -> score += PAPER;
                default -> System.out.println("? ? ?");
            }
        } else if (myOption == LOSS_SCORE) {
            switch (theirOption) {
                case SCISSOR -> score += PAPER;
                case PAPER -> score += ROCK;
                case ROCK -> score += SCISSOR;
                default -> System.out.println("? ? ?");
            }
        }
    }

    private void setOptions(String mine, String theirs) {
        myOption = 0;
        theirOption = 0;

        switch (mine) {
            case I_LOSE -> myOption = LOSS_SCORE;
            case I_WIN -> myOption = WIN_SCORE;
            case I_DRAW -> myOption = DRAW_SCORE;
            default -> System.out.println("? ? ?");
        }

        switch (theirs) {
            case THEIR_ROCK -> theirOption = ROCK;
            case THEIR_PAPER -> theirOption = PAPER;
            case THEIR_SCISSOR -> theirOption = SCISSOR;
            default -> System.out.println("? ? ?");
        }
    }

    public int getScore() {
        return score;
    }
}
