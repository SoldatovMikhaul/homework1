package com.homework.homework1;

import java.time.LocalDate;

public class Predict {
    public static String predictValueForTomorrow(Float[] values, LocalDate latestDate) {
        float result = 0;
        for (int i = 0; i < 7; i++) {
            result += values[i];
        }
        latestDate.plusDays(1);
        return latestDate.getDayOfWeek().toString().substring(0, 2) + " " +
                latestDate.getDayOfMonth() + "." +
                latestDate.getMonth().getValue() + "." +
                latestDate.getDayOfYear() + " - " + result/7;
    }

    public static String predictValueForNextWeek(Float[] values, LocalDate latestDate) {
        Float[] result = values;
        String finalString = "";
        for (int i = 0; i < 7; i++) {
            float averageValue = 0;
            for (int j = 0; j < 7; j++) {
                averageValue += result[j];
            }

            for (int j = 0; j < 6; j++) {
                result[j] = values[j + 1];
            }

            result[6] = averageValue / 7;
        }
        for (int i = 0; i < 7; i++) {
            latestDate = latestDate.plusDays(1);
            finalString  = finalString +
                    latestDate.getDayOfWeek().toString().substring(0, 2) + " " +
                    latestDate.getDayOfMonth() + "." +
                    latestDate.getMonth().getValue() + "." +
                    latestDate.getDayOfYear() + " - " + result[i] + "\n";
        }
        return finalString;
    }
}
