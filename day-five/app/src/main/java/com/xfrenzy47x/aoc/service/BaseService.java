package com.xfrenzy47x.aoc.service;

import com.xfrenzy47x.aoc.App;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BaseService {
    public List<Deque<String>> cratePositions = new ArrayList<>();

    public String getTopCrates() {
        StringBuilder result = new StringBuilder();
        for (Deque<String> pos : cratePositions) {
            result.append(pos.getFirst());
        }
        return result.toString();
    }

    public void initCratePositions(ArrayList<String> setup) {
        for (int i = setup.size(); i > 0; i--) {
            String[] crates = setup.get(i-1).split(" ");
            if (cratePositions.size() != crates.length)
                for (String ignored : crates) cratePositions.add(new ArrayDeque<>());

            for (int j = 0; j < crates.length; j++) {
                String crateLetter = crates[j].replace("[", "").replace("]", "");
                if (!crateLetter.equals("-")) {
                    cratePositions.get(j).addFirst(crateLetter);
                }
            }
        }
    }

    public File getFile(String resourceName) throws URISyntaxException {
        URL resource = App.class.getClassLoader().getResource(resourceName);
        if (resource == null) {
            throw new IllegalArgumentException("Resource " + resourceName + " is missing...");
        } else {
            return new File(resource.toURI());
        }
    }
}
