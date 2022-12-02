package com.xfrenzy47x.aoc;

import com.xfrenzy47x.aoc.service.NewGameService;
import com.xfrenzy47x.aoc.util.Helper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;

public class NewGameServiceTest {
    @Test
    void givenTestDataScoreIs12() throws URISyntaxException {
        File testInput = Helper.getFile("input-test.txt");
        NewGameService newGameService = new NewGameService(testInput);

        Assertions.assertEquals(12, newGameService.getScore());
    }
}
