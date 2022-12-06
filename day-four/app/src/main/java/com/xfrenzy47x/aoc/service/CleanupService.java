package com.xfrenzy47x.aoc.service;

import com.xfrenzy47x.aoc.App;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class CleanupService {

    int fullyContainScore;
    int overlapScore;
    public CleanupService(String resourceName) throws URISyntaxException {
        File input = getFile(resourceName);
        fullyContainScore = 0;

        try (Scanner scanner = new Scanner(input)) {
            while(scanner.hasNext()) {
                String line = scanner.nextLine();
                checkDuty(line.split(","));
            }
        } catch (Exception ex) {
            // Whoops :D
        }
    }

    public int getFullyContainScore() {
        return fullyContainScore;
    }

    public int getOverlapScore() {
        return overlapScore;
    }

    private void checkDuty(String[] duties) {
        int[] dutyOne = getDutyStartEnd(duties[0]);
        int[] dutyTwo = getDutyStartEnd(duties[1]);

        if (
        ((dutyOne[0] >= dutyTwo[0] && dutyOne[0] <= dutyTwo[1]) && (dutyOne[1] >= dutyTwo[0] && dutyOne[1] <= dutyTwo[1])) ||
        ((dutyTwo[0] >= dutyOne[0] && dutyTwo[0] <= dutyOne[1]) && (dutyTwo[1] >= dutyOne[0] && dutyTwo[1] <= dutyOne[1]))
        ) {
            overlapScore++;
            fullyContainScore++;
        } else if (
        (dutyOne[0] >= dutyTwo[0] && dutyOne[0] <= dutyTwo[1]) || (dutyOne[1] >= dutyTwo[0] && dutyOne[1] <= dutyTwo[1]) ||
        (dutyTwo[0] >= dutyOne[0] && dutyTwo[0] <= dutyOne[1]) || (dutyTwo[1] >= dutyOne[0] && dutyTwo[1] <= dutyOne[1])
        ) {
            overlapScore++;
        }
    }

    private int[] getDutyStartEnd(String duty) {
        return new int[] {Integer.parseInt(duty.split("-")[0]), Integer.parseInt(duty.split("-")[1])};
    }

    private File getFile(String resourceName) throws URISyntaxException {
        URL resource = App.class.getClassLoader().getResource(resourceName);
        if (resource == null) {
            throw new IllegalArgumentException("Resource " + resourceName + " is missing...");
        } else {
            return new File(resource.toURI());
        }
    }
}
