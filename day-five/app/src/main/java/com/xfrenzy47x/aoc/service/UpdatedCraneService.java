package com.xfrenzy47x.aoc.service;

import java.io.File;
import java.net.URISyntaxException;
import java.util.*;

public class UpdatedCraneService extends BaseService {
    public UpdatedCraneService(String resourceName) throws URISyntaxException {
        File input = getFile(resourceName);
        ArrayList<String> initSetup = new ArrayList<>();
        boolean setupActive = true;
        try (Scanner scanner = new Scanner(input)) {
            while(scanner.hasNext()) {
                String line = scanner.nextLine();

                if (line.isEmpty())
                    continue;

                if (setupActive) {
                    if (line.contains("[")) {
                        initSetup.add(line);
                    } else {
                        setupActive = false;
                        initCratePositions(initSetup);
                    }
                } else {
                    String[] instructions = line.split(" ");
                    List<String> crates = new ArrayList<>();
                    for (int i = 0; i < Integer.parseInt(instructions[1]); i++) {
                        crates.add(cratePositions.get(Integer.parseInt(instructions[3])-1).pop());
                    }
                    for (int i = crates.size(); i > 0; i--) {
                        cratePositions.get(Integer.parseInt(instructions[5])-1).addFirst(crates.get(i-1));
                    }
                }
            }
        } catch (Exception ex) {
            // Whoops :D
        }
        System.out.println("Done");
    }
}
