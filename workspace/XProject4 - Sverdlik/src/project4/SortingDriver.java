package project4;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * @author Eddie Gurnee
 * @version 0.0.05 03/17/2014
 * @since 12/24/2013
 */
public class SortingDriver {
	private static Scanner kb;
	private static SortingUnit franklin;

	private static String[] dataFiles = { "dataAscend.txt",
			"dataRandom.txt",
			"dataDescend.txt" };
	private static String[] inputType = { "ascend", "random", "descend" };
	private static String[] sortNames = { "merge", "heap", "quick" };

	private static int[] someCollectionOfNumbers = new int[10_000];
	private static final int N_FILES = inputType.length;
	private static final int N_SORTS = sortNames.length;

	public static void main(String[] args) {
		kb = new Scanner(System.in);

		System.out.print("Would you like to run everything " + "with defaults? [y/n] ");

		if (!kb.nextLine().substring(0, 1).equalsIgnoreCase("y")) {
			menu: while (true) {
				System.out.print("Please name your output file "
						+ "(.txt will be appended): ");
				String requestedFileName = kb.nextLine() + ".txt";

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
					int fileSelect = kb.nextInt() - 1;

					kb.nextLine();
					long result = sort(sortType, fileSelect, requestedFileName);
					if (result == 1L) {
						System.out.println("Error reading from the input files.");
					} else {
						System.out.println("The sort took: " + result + " ms.");
					}

					System.out.print("Would you like to try another" + " sort? [y/n] ");

					if (!kb.nextLine().trim().substring(0, 1).equalsIgnoreCase("y")) {
						break menu;
					}
				} else {
					System.out.println("Invalid response.");
				}
				System.out.println();
			}
		} else { // run with defaults
			long[][] runtimes = new long[N_FILES][N_SORTS];

			for (int s = 0; s < N_SORTS; s++) {
				for (int f = 0; f < N_FILES; f++) {
					runtimes[f][s] = sort(s, f, (sortNames[s] + "_" + inputType[f] + ".txt"));
				}
			}

			PrintWriter completeOutput = null;
			File runTimesFile = new File("run_times.txt");
			try {
				completeOutput = new PrintWriter(new FileOutputStream(runTimesFile));

				completeOutput.printf("%25s%15s%15s",
						"MergeSort",
						"HeapSort",
						"QuickSort");

				for (int i = 0; i < N_FILES; i++) {
					completeOutput.printf("%n%10s%15s%15s%15s",
							inputType[i],
							runtimes[i][0] + "ns",
							runtimes[i][1] + "ns",
							runtimes[i][2] + "ns");
				}

				System.out.println("Runs complete.");
				Desktop.getDesktop().open(runTimesFile);

			} catch (FileNotFoundException e) {
				e.printStackTrace(); // for the printwriter
			} catch (IOException e) {
				e.printStackTrace(); // for the desktop.open
			} finally {
				completeOutput.close();
			}

		}
		kb.close();
	}

	private static long sort(int sortType, int fileSelect, String fileName) {
		Scanner dataIn = null;
		try {
			dataIn = new Scanner(new FileInputStream(new File(dataFiles[fileSelect])));
			for (int i = 0; dataIn.hasNext(); i++) {
				someCollectionOfNumbers[i] = dataIn.nextInt();
			}
		} catch (FileNotFoundException e) {
			return 1L;
		} finally {
			dataIn.close();
		}

		switch (sortType) {
			case 0:
				franklin = new MergeSortUnit(someCollectionOfNumbers);
				break;
			case 1:
				franklin = new HeapSortUnit(someCollectionOfNumbers);
				break;
			case 2:
				franklin = new QuickSortUnit(someCollectionOfNumbers);
				break;
		}

		long startTime = System.nanoTime();
		franklin.sort();
		long endTime = System.nanoTime();

		franklin.printToFile(fileName);
		return endTime - startTime;
	}
}