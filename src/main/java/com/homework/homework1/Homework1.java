package com.homework.homework1;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static com.homework.homework1.DataParser.*;
import static com.homework.homework1.Predict.predictValueForNextWeek;
import static com.homework.homework1.Predict.predictValueForTomorrow;

public class Homework1 {
	public static final String TOMORROW = "tomorrow";
	public static final String WEEK = "week";
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.println("Input command to start");
		String command = input.nextLine();
		String[] inputValues = command.split(" ");
		String inputValutaType = inputValues[1];

		List<String[]> data = getDataFromCsv(inputValutaType);
		Float[] valutaValues = getValutaValues(data);
		LocalDate latestDate = getLatestDate(data);

		String inputDuration = inputValues[2];
		switch (inputDuration) {
			case TOMORROW: System.out.println(predictValueForTomorrow(valutaValues, latestDate));
					break;
			case WEEK: System.out.println(predictValueForNextWeek(valutaValues, latestDate));
					break;
		}
	}
}
