package com.xfrenzy47x.aoc.service;

import com.xfrenzy47x.aoc.App;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;

public class DataStreamService {

    int startOfPacketMarkerComplete;
    int messageMarkerComplete;
    public DataStreamService(String resourceName) throws URISyntaxException {
        File input = getFile(resourceName);
        String packetMarker = "";
        HashSet<String> newPacketMarker = new HashSet<>();

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

            int oldLen = newPacketMarker.size();
            newPacketMarker.add(chara);
            if (newPacketMarker.size() == oldLen) {
                newPacketMarker = new HashSet<>();
                newPacketMarker.add(chara);
            }

            if (packetMarker.length() == 4 && startOfPacketMarkerComplete == 0) {
                startOfPacketMarkerComplete = i+1;
            }
            if (newPacketMarker.size() == 14 && messageMarkerComplete == 0) {
                messageMarkerComplete = i;
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
