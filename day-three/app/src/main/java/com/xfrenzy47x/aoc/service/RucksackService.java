package com.xfrenzy47x.aoc.service;

import com.xfrenzy47x.aoc.util.Helper;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class RucksackService {

    int score;
    public RucksackService(String resourceName) throws URISyntaxException {
        score = 0;

        File input = Helper.getFile(resourceName);

        try (Scanner scanner = new Scanner(input)) {
            while(scanner.hasNext()) {
                String line = scanner.nextLine();
                checkRucksack(line);
            }
        } catch (Exception ex) {
            // Whoops :D
        }
    }

    private void checkRucksack(String theLine) {
        char[] compartmentOne = theLine.substring(0, theLine.length() / 2).toCharArray();
        char[] compartmentTwo = theLine.substring(theLine.length() / 2).toCharArray();

        ArrayList<Integer> priorities = new ArrayList<>();

        for (char one : compartmentOne) {
            for (char two : compartmentTwo) {
                if (one == two) {
                    priorities.add(getPriority(one));
                }
            }
        }

        score += priorities.stream().distinct().mapToInt(value -> value).sum();
    }

    private int getPriority(char value) {
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == value) {
                return i;
            }
        }
        return 0;
    }

    char[] alphabet = {' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public int getScore() {
        return score;
    }
}
