package com.homework.homework1;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.homework.homework1.Predict.predictValueForNextWeek;
import static com.homework.homework1.Predict.predictValueForTomorrow;
import static org.junit.Assert.assertEquals;

public class PredictTest {
    private static final int YEAR = 2000;
    private static final int MONTH = 12;
    private static final int DAY_OF_MONTH = 11;
    @Test
    public void shouldCorrectPredictValueForNextDay_whenSimpleDataGivenInCorrectInput() {
        Float[] data = new Float[]{1.11f, 1.15f, 1.14f, 1.13f, 1.14f, 1.15f, 1.23f};
        String expected = "MO 11.12.346 - 1.15";
        assertEquals(expected,  predictValueForTomorrow(data, LocalDate.of(YEAR, MONTH, DAY_OF_MONTH)));
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
        assertEquals(expected,  predictValueForNextWeek(data, LocalDate.of(YEAR, MONTH, DAY_OF_MONTH)));
    }
}
