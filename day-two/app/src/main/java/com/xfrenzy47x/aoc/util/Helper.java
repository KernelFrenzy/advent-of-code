package com.xfrenzy47x.aoc.util;

import com.xfrenzy47x.aoc.App;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class Helper {
    public static File getFile(String resourceName) throws URISyntaxException {
        URL resource = App.class.getClassLoader().getResource(resourceName);
        if (resource == null) {
            throw new IllegalArgumentException("Resource " + resourceName + " is missing...");
        } else {
            return new File(resource.toURI());
        }
    }
}
