package project4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author Eddie Gurnee
 * @version 0.0.03 01/09/2014
 * @since 12/24/2013
 */
public class SortingDriver {
	private static Scanner kb;
	private static SortingUnit franklin;
	private static String[] dataFiles = { "dataAscend.txt", "dataRandom.txt",
			"dataDescend.txt" };
	private static int[] someCollectionOfNumbers = new int[10_000];

	public static void main(String[] args) {
		kb = new Scanner(System.in);

		menu: while (true) {
			System.out.println("1: Merge Sort");
			System.out.println("2: Heap Sort");
			System.out.println("3: Quick Sort");
			System.out.println("");
			System.out.println("0: End");

			System.out.print("-" + System.getProperty("user.name") + "$ ");
			int sortType = kb.nextInt();
			if (sortType <= 3 && sortType >= 0) {
				if (sortType == 0) {
					break menu;
				}
				System.out.println("Which file would you like to sort?");
				System.out.println("1: Ascending");
				System.out.println("2: Random");
				System.out.println("3: Descending");
				int fileSelect = kb.nextInt();

				sort(sortType, fileSelect);
				System.out.print("Would you like to try another sort? [y/n] ");
				if (!kb.nextLine().substring(0, 1).equalsIgnoreCase("y")) {
					break menu;
				}
			} else {
				System.out.println("Invalid response.");
			}
			System.out.println();
		}
		kb.close();
	}

	private static void sort(int sortType, int fileSelect) {
		Scanner dataIn = null;
		try {
			dataIn = new Scanner(new FileInputStream(new File(
					dataFiles[fileSelect])));
			for (int i = 0; dataIn.hasNext(); i++) {
				someCollectionOfNumbers[i] = dataIn.nextInt();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		franklin = new SortingUnit(someCollectionOfNumbers);

		boolean success = true;
		
		long startTime = System.currentTimeMillis();
		switch (sortType) {
		case 1:
			if (!franklin.mergeSort()) {
				success = false;
			}
			break;
		case 2:
			if (!franklin.heapSort()) {
				success = false;
			}
			break;
		case 3:
			if (!franklin.quickSort()) {
				success = false;
			}
			break;
		}
		long endTime = System.currentTimeMillis();
		
		if (!success) {
			System.out.println("The sort could not be completed.");
		} else {
			System.out.println("The sort took: " + (endTime - startTime)
					+ " ms.");
		}
	}
}