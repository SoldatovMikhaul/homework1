package com.homework.homeworck1;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Homework1 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String valutaType = null;
		System.out.println("Input 1 to get prediction for USD, 2 - EUR, 3 - LIR");
		String inputValutaType = input.nextLine();
		switch (inputValutaType) {
			case "1": valutaType = "USD";
			case "2": valutaType = "EUR";
			case "3": valutaType = "LIR";
		}

		List<String[]> data = getDataFromCsv(valutaType);
		Float[] valutaValues = getValutaValues(data);

		System.out.println("Input 1 to get prediction for tomorrow, 2 - for next week");
		String inputDuration = input.nextLine();
		switch (inputDuration) {
			case "1": System.out.println(predictValueForTomorrow(valutaValues));
					break;
			case "2": Arrays.stream(predictValueForNextWeek(valutaValues))
					.collect(Collectors.toList()).forEach(x -> System.out.println(x));
		}
	}

	static List<String[]> getDataFromCsv(String valutaType) {
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

	static Float[] getValutaValues(List<String[]> data) {
		Float[] valutaValues = new Float[7];
		for (int i = 1; i < 8; i++) {
			valutaValues[i - 1] = Float.parseFloat(
					Arrays.stream(Arrays.toString(data.get(i)).split(", ")).collect(Collectors.toList()).get(2));
		}
		return valutaValues;
	}

	static float predictValueForTomorrow(Float[] values) {
		float result = 0;
		for (int i = 0; i < 7; i++) {
			result += values[i];
		}
		return result/7;
	}

	static Float[] predictValueForNextWeek(Float[] values) {
		Float[] result = values;
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

		return result;
	}
}
