package com.xfrenzy47x.aoc.service;

import java.io.File;
import java.util.Scanner;

public class GameService {
    final int ROCK = 1;
    final int PAPER = 2;
    final int SCISSOR = 3;

    final String THEIR_ROCK = "A";
    final String THEIR_PAPER = "B";
    final String THEIR_SCISSOR = "C";

    final String MY_ROCK = "X";
    final String MY_PAPER = "Y";
    final String MY_SCISSOR = "Z";
    int score;

    final int DRAW_SCORE = 3;
    final int WIN_SCORE = 6;

    int theirOption;
    int myOption;

    public GameService(File input) {
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

        if (myOption == theirOption) {
            score += DRAW_SCORE;
        } else if (myOption == ROCK && theirOption == SCISSOR) {
            score += WIN_SCORE;
        } else if (myOption == SCISSOR && theirOption == ROCK) {
            System.out.println("loser");
        } else if (myOption > theirOption) {
            score += WIN_SCORE;
        }
    }

    private void setOptions(String mine, String theirs) {
        myOption = 0;
        theirOption = 0;

        switch (mine) {
            case MY_ROCK -> myOption = ROCK;
            case MY_PAPER -> myOption = PAPER;
            case MY_SCISSOR -> myOption = SCISSOR;
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
