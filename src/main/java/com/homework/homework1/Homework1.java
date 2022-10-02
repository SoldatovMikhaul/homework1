package com.homework.homework1;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Homework1 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String valutaType = null;
		int counter = 1;
		System.out.println("Input 1 to get prediction for USD, 2 - EUR, 3 - LIR");
		String inputValutaType = input.nextLine();
		switch (inputValutaType) {
			case "1": valutaType = "USD";
			break;
			case "2": valutaType = "EUR";
			break;
			case "3": valutaType = "LIR";
			break;
		}

		List<String[]> data = getDataFromCsv(valutaType);
		Float[] valutaValues = getValutaValues(data);
		LocalDate latestDate = getLatestDate(data);

		System.out.println("Input 1 to get prediction for tomorrow, 2 - for next week");
		String inputDuration = input.nextLine();
		switch (inputDuration) {
			case "1": System.out.println(predictValueForTomorrow(valutaValues, latestDate));
					break;
			case "2": System.out.println(predictValueForNextWeek(valutaValues, latestDate));
					break;
		}
	}

	public static List<String[]> getDataFromCsv(String valutaType) {
		try (CSVReader reader = new CSVReader(new FileReader("./src/main/resources/" + valutaType + ".csv"))) {
			return reader.readAll();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (CsvException e) {
			throw new RuntimeException(e);
		}
	}

	public static Float[] getValutaValues(List<String[]> data) {
		Float[] valutaValues = new Float[7];
		for (int i = 1; i < 8; i++) {
			valutaValues[i - 1] = Float.parseFloat(
					Arrays.stream(Arrays.toString(data.get(i)).split(", ")).collect(Collectors.toList()).get(2));
		}
		return valutaValues;
	}

	public static LocalDate getLatestDate(List<String[]> data) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate latestDate = LocalDate.parse((Arrays.stream(Arrays.toString(data.get(1)).split(", "))
				.collect(Collectors.toList()).get(1)), dateTimeFormatter);
		return latestDate;
	}

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
