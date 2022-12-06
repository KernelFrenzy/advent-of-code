package com.xfrenzy47x.aoc.service;

import com.xfrenzy47x.aoc.App;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class DataStreamService {

    int startOfPacketMarkerComplete;
    int messageMarkerComplete;
    public DataStreamService(String resourceName) throws URISyntaxException {
        File input = getFile(resourceName);
        String packetMarker = "";
        startOfPacketMarkerComplete = 0;
        messageMarkerComplete = 0;
        String theStream = "";
        try (Scanner scanner = new Scanner(input)) {
            while(scanner.hasNext()) {
                theStream = scanner.nextLine();
            }
        } catch (Exception ex) {
            // Whoops :D
        }

        for (int i = 0; i < theStream.length(); i++) {
            String chara = theStream.substring(i, i+1);
            if (packetMarker.contains(chara)) {
                packetMarker = chara;
            } else {
                packetMarker += chara;
            }

            if (packetMarker.length() == 4 && startOfPacketMarkerComplete == 0) {
                startOfPacketMarkerComplete = i+1;
                packetMarker = "";
            } else if (packetMarker.length() == 14 && messageMarkerComplete == 0) {
                messageMarkerComplete = i+1;
                break;
            }
        }
    }

    public int getStartOfPacketMarkerComplete() {
        return startOfPacketMarkerComplete;
    }

    public int getMessageMarkerComplete() {
        return messageMarkerComplete;
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
