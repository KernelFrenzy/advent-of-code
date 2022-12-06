package com.xfrenzy47x.aoc.service;

import com.xfrenzy47x.aoc.App;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class CraneService {

    List<Deque<String>> cratePositions = new ArrayList<>();
    public CraneService(String resourceName) throws URISyntaxException {
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
                    //System.out.println(line);
                    String[] instructions = line.split(" ");
                    for (int i = 0; i < Integer.parseInt(instructions[1]); i++) {
                        String crate = cratePositions.get(Integer.parseInt(instructions[3])-1).pop();
                        cratePositions.get(Integer.parseInt(instructions[5])-1).addFirst(crate);
                    }
                }
            }
        } catch (Exception ex) {
            // Whoops :D
        }
        System.out.println("Done");
    }

    public String getTopCrates() {
        StringBuilder result = new StringBuilder();
        for (Deque<String> pos : cratePositions) {
            result.append(pos.getFirst());
        }
        return result.toString();
    }

    private void initCratePositions(ArrayList<String> setup) {
        for (int i = setup.size(); i > 0; i--) {
            String[] crates = setup.get(i-1).split(" ");
            if (cratePositions.size() != crates.length)
                for (String crate : crates) cratePositions.add(new ArrayDeque<>());

            for (int j = 0; j < crates.length; j++) {
                String crateLetter = crates[j].replace("[", "").replace("]", "");
                if (!crateLetter.equals("-")) {
                    cratePositions.get(j).addFirst(crateLetter);
                }
            }
        }
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
