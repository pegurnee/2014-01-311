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
	private static SortUnit franklin;

	private static final String[] dataFiles = { "dataAscend.txt",
			"dataRandom.txt",
			"dataDescend.txt" };
	private static final String[] fileNames = { "ascend", "random", "descend" };
	private static final String[] sortNames = { "merge", "heap", "quick" };

	private static int[] someCollectionOfNumbers = new int[10_000];
	private static final int N_FILES = fileNames.length;
	private static final int N_SORTS = sortNames.length;

	private static final int N_TRIALS = 50_000;
	private static final int SAMPLE_SIZE = 100;

	// private static final int COMPLETE_LIMIT = N_FILES * N_SORTS;

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
				if (sortType <= N_SORTS && sortType >= 0) {
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
			DynamicStack[][] runtimes = new DynamicStack[N_FILES][N_SORTS];
			for (int s = 0; s < N_SORTS; s++) {
				for (int f = 0; f < N_FILES; f++) {
					runtimes[f][s] = new DynamicStack();
				}
			}

			int count = 0;
			for (int t = 0; t < N_TRIALS; t++) {
				for (int s = 0; s < N_SORTS; s++) {
					for (int f = 0; f < N_FILES; f++) {
						runtimes[f][s].push(sort(s, f, "do_not_print"));
					}
				}
				System.out.println("trial num: " + ++count);
			}

			PrintWriter completeOutput = null;
			PrintWriter sampleOutput = null;
			PrintWriter csvOutput = null;
			File timesFile = new File("aggregate_run_times.txt");
			File sampleFile = new File("selected_sample_run_times.txt");

			try {
				completeOutput = new PrintWriter(new FileOutputStream(timesFile));
				sampleOutput = new PrintWriter(new FileOutputStream(sampleFile));
				csvOutput = new PrintWriter(new FileOutputStream(new File("total_run_times.csv")));

				String headerStr = String.format("%30s", sortNames[0]);
				for (int s = 1; s < N_SORTS; s++) {
					headerStr += String.format("%15s", sortNames[s]);
				}
				completeOutput.print(headerStr);
				sampleOutput.print(headerStr);

				String csvHeaderStr = String.format(",%s,", sortNames[0]);
				for (int s = 1; s < N_SORTS; s++) {
					csvHeaderStr += String.format("%s,", sortNames[s]);
				}
				csvOutput.print(csvHeaderStr);

				DynamicStack[] fullAvg = new DynamicStack[N_FILES];

				for (int f = 0; f < N_FILES; f++) {
					fullAvg[f] = new DynamicStack();
				}

				for (int f = 0; f < N_FILES; f++) {
					for (int s = 0; s < N_SORTS; s++) {
						fullAvg[s].push(runtimes[f][s].avg());
					}

					// print aggregate run data
					int numOfDataOfInterest = 3;
					// String dataOutStr = "";
					String[] dataTypes = { "min", "max", "avg" };
					for (int i = 0; i < numOfDataOfInterest; i++) {
						String dataOutStr =
								String.format("%n%15s",
										(fileNames[f] + " " + dataTypes[i]));
						for (int s = 0; s < N_SORTS; s++) {
							dataOutStr +=
									String.format("%15s",
											(runtimes[f][s].getData(i) + "ns"));
						}
						completeOutput.print(dataOutStr);
					}
					completeOutput.println();

					
					long[][] theRuntimes =
							new long[N_SORTS][];
					for (int s = 0; s < N_SORTS; s++) {
						theRuntimes[s] = new long[runtimes[f][s].size()];
						
						for (int t = 0; t < N_TRIALS; t++) {
							theRuntimes[s][t] = runtimes[f][s].pop();
						}
					}

					// print full run data
					for (int sam = 0; sam < SAMPLE_SIZE; sam++) {
						String sampleOutStr =
								String.format("%n%15s", fileNames[f] + " " + (1 + sam));
						for (int s = 0; s < N_SORTS; s++) {
							sampleOutStr +=
									String.format("%15s", theRuntimes[s][sam] + "ns");
						}
						sampleOutput.print(sampleOutStr);
					}
					sampleOutput.println();

					// print run data to csv
					for (int t = 0; t < N_TRIALS; t++) {
						String csvOutStr =
								String.format("%n%s,", fileNames[f] + " " + (1 + t));
						for (int s = 0; s < N_SORTS; s++) {
							csvOutStr += String.format("%s,", theRuntimes[s][t]);
						}
						csvOutput.print(csvOutStr);
					}
					csvOutput.println();
				}
				
				String avgStr = String.format("%n%15s", "average times");
				for (int i = 0; i < N_SORTS; i++) {
					avgStr += String.format("%15s", fullAvg[i].avg() + "ns");
				}
				completeOutput.print(avgStr);

				System.out.println("Runs complete.");

				Desktop.getDesktop().open(sampleFile);
				Desktop.getDesktop().open(timesFile);

			} catch (FileNotFoundException e) {
				e.printStackTrace(); // printwriter
			} catch (IOException e) {
				e.printStackTrace(); // desktop.open()
			} finally {
				completeOutput.close();
				sampleOutput.close();
				csvOutput.close();
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
		
		if (!"do_not_print".equals(fileName)) {
			franklin.printToFile(fileName);
		}

		return endTime - startTime;

	}
}