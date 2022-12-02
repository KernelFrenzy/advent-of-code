package com.xfrenzy47x.aoc;

import com.xfrenzy47x.aoc.service.GameService;
import com.xfrenzy47x.aoc.util.Helper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.net.URISyntaxException;

public class GameServiceTest {
    @Test
    void givenTestDataScoreIs15() throws URISyntaxException {
        File testInput = Helper.getFile("input-test.txt");
        GameService gameService = new GameService(testInput);

        Assertions.assertEquals(15, gameService.getScore());
    }
}
