package com.xfrenzy47x.aoc;

import com.xfrenzy47x.aoc.service.RucksackService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

public class RucksackServiceTest {

    RucksackService rucksackService;
    @BeforeEach
    void setup() throws URISyntaxException {
        rucksackService = new RucksackService("input-test.txt");
    }
    @Test
    void givenTestDataSumIs157() {
        Assertions.assertEquals(157, rucksackService.getScore());
    }

    @Test
    void givenTestDataSumIs70() {
        Assertions.assertEquals(70, rucksackService.getGroupScore());
    }
}
