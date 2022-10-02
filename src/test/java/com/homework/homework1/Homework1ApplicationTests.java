package com.homework.homework1;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

class Homework1ApplicationTests {

    Homework1 homework1 = new Homework1();

    @Test
    public void shouldCorrectPredictValueForNextDay_whenSimpleDataGivenInCorrectInput() {
        Float[] data = new Float[]{1.11f, 1.15f, 1.14f, 1.13f, 1.14f, 1.15f, 1.23f};
        String expected = "MO 11.12.346 - 1.15";
        assertEquals(expected,  Homework1.predictValueForTomorrow(data, LocalDate.of(2000, 12, 11)));
    }

    @Test
    public void shouldCorrectPredictValueForNextWeek_whenSimpleDataGivenInCorrectInput() {
        Float[] data = new Float[]{1.11f, 1.15f, 1.14f, 1.13f, 1.14f, 1.15f, 1.23f};
        String expected = "TU 12.12.347 - 1.15\n" +
                "WE 13.12.348 - 1.1557143\n" +
                "TH 14.12.349 - 1.1565306\n" +
                "FR 15.12.350 - 1.158892\n" +
                "SA 16.12.351 - 1.1630194\n" +
                "SU 17.12.352 - 1.1663082\n" +
                "MO 18.12.353 - 1.1686379\n";
        assertEquals(expected,  Homework1.predictValueForNextWeek(data, LocalDate.of(2000, 12, 11)));
    }
}
