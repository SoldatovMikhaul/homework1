package com.homework.homework1;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataParser {

    private static final String CURS = "curs";
    private static final String DATA = "data";
    private static final String DATE_TIME_FORMATTER_PATTERN = "MM/dd/yyyy";
    public static List<String[]> getDataFromCsv(String valutaType) throws Exception {
        try (CSVReader reader = new CSVReader(new FileReader("./src/main/resources/" + valutaType + ".csv"))) {
            return reader.readAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static Float[] getValutaValues(List<String[]> data) {
        int cursColumnNumber = 0;
        for (int i = 0; i < 4; i++) {
           String columnName = Arrays.stream(Arrays.toString(data.get(0)).split(", ")).collect(Collectors.toList()).get(i);
           if (columnName.equals(CURS)) {
               cursColumnNumber = i;
           }
        }
        Float[] valutaValues = new Float[7];
        for (int i = 1; i < 8; i++) {
            valutaValues[i - 1] = Float.parseFloat(
                    Arrays.stream(Arrays.toString(data.get(i)).split(", ")).collect(Collectors.toList()).get(cursColumnNumber));
        }
        return valutaValues;
    }

    public static LocalDate getLatestDate(List<String[]> data) {
        int dateColumnNumber = 0;
        for (int i = 0; i < 4; i++) {
            String columnName = Arrays.stream(Arrays.toString(data.get(0)).split(", ")).collect(Collectors.toList()).get(i);
            if (columnName.equals(DATA)) {
                dateColumnNumber = i;
            }
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER_PATTERN);
        LocalDate latestDate = LocalDate.parse((Arrays.stream(Arrays.toString(data.get(dateColumnNumber)).split(", "))
                .collect(Collectors.toList()).get(dateColumnNumber)), dateTimeFormatter);
        return latestDate;
    }
}
