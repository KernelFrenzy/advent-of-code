package com.xfrenzy47x.aoc;

import com.xfrenzy47x.aoc.service.RucksackService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

public class RucksackServiceTest {
    @Test
    void givenTestDataSumIs157() throws URISyntaxException {
        RucksackService rucksackService = new RucksackService("input-test.txt");
        Assertions.assertEquals(157, rucksackService.getScore());
    }
}
