package com.xfrenzy47x.aoc.service;

import com.xfrenzy47x.aoc.util.Helper;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class RucksackService {

    int score;
    int groupScore;
    public RucksackService(String resourceName) throws URISyntaxException {
        score = 0;
        groupScore = 0;

        File input = Helper.getFile(resourceName);

        ArrayList<String> group = new ArrayList<>();
        try (Scanner scanner = new Scanner(input)) {
            while(scanner.hasNext()) {
                String line = scanner.nextLine();
                if (group.size() == 3) {
                    checkRucksackGroup(group);
                    group = new ArrayList<>();

                }
                group.add(line);
                checkRucksack(line);
            }
            if (group.size() == 3) {
                checkRucksackGroup(group);
            }
        } catch (Exception ex) {
            // Whoops :D
        }
    }

    private void checkRucksackGroup(ArrayList<String> group) {
        char[] bagOne = group.get(0).toCharArray();

        for (char bagO : bagOne) {
            if (group.get(1).contains(bagO + "") && group.get(2).contains(bagO + "")) {
                groupScore += getPriority(bagO);
                break;
            }
        }
    }

    private void checkRucksack(String theLine) {
        char[] compartmentOne = theLine.substring(0, theLine.length() / 2).toCharArray();

        ArrayList<Integer> priorities = new ArrayList<>();

        for (char one : compartmentOne) {
            if (theLine.substring(theLine.length() / 2).contains(one + "")) {
                priorities.add(getPriority(one));
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

    public int getGroupScore() {
        return groupScore;
    }
}
