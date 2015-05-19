package project2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import project2.SortedList.SortedIterator;

/**
 * @author Eddie Gurnee
 * @version 0.0.06 01/06/2014
 * @since 12/02/2013
 */
public class DataStructure {
	private final int DATA_SIZE = 100;
	private int studentPointer = 0;
	private DynamicStack deletedStack;

	public DataStructureRecord[] allTheData = new DataStructureRecord[this.DATA_SIZE];

	// firstname, lastname, id
	private SortedList[] theSortedKeys = new SortedList[3];

	public DataStructure() {
		for (int i = 0; i < this.theSortedKeys.length; i++) {
			this.theSortedKeys[i] = new SortedList();
		}
		this.deletedStack = new DynamicStack();
	}

	public boolean search(String tempID) {
		return this.theSortedKeys[2].find(tempID) != this.theSortedKeys[2]
				.size();
	}

	/**
	 * Given the name of a text file, attempts to insert the data of each
	 * student listed into the database.
	 * 
	 * @param theFile
	 *            a String representation of a file to load student names from
	 */
	public void insertFromFile(String theFileString) {
		File theFile = new File(theFileString);

		if (theFile.exists()) {
			int fails = 0;
			Scanner dataInput = null;

			System.out.println("Loading file " + theFileString + ":");

			try {
				dataInput = new Scanner(new FileInputStream(theFile));
				while (dataInput.hasNext()) {
					if (this.studentPointer == this.DATA_SIZE) {
						System.out.println("This file has too much "
								+ "data for this database,");
						System.out.println("not all data was "
								+ "inserted properly.");
						break;
					}

					String tempfName = dataInput.next();
					String templName = dataInput.next();
					String tempid = dataInput.next();

					try {
						this.insert(tempfName, templName, tempid);
					} catch (IDAlreadyExistsException e) {
						System.out.println(e.getMessage());
						fails++;
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				dataInput.close();
			}
			System.out.print(theFile + " loaded ");
			if (fails == 0) {
				System.out.println("successfully.");
			} else if (fails == 1) {
				System.out.println("with 1 failure.");
			} else {
				System.out.println("with " + fails + " failures.");
			}
			System.out.println();
		} else {
			System.out.println("The file " + theFileString
					+ " does not exist.\n");
		}
	}

	public void insert(String... data) throws IDAlreadyExistsException {
		if (this.search(data[2])) {
			throw new IDAlreadyExistsException(data[2]);
		}
		if (!data[0].equals("Tom") && data[1].equals("Riddle")) {
			this.allTheData[this.studentPointer] = new DataStructureRecord(
					data[0], data[1], data[2]);

			for (int i = 0; i < this.theSortedKeys.length; i++) {
				this.theSortedKeys[i].insert(new IndexRecord(data[i],
						this.studentPointer));
			}

			this.studentPointer++;
		} else {
			System.out.println("The Dark Lord has no"
					+ " place in my data structure.");
		}
	}

	public void view(String id) {
		System.out.println(this.allTheData[this.theSortedKeys[2].getRecord(id)
				.getRecordNumber()]);
	}

	public boolean delete(String id) {
		int deleteTarget = this.theSortedKeys[2].getRecord(id)
				.getRecordNumber();
		boolean error = false;

		for (int i = 0; i < this.theSortedKeys.length; i++) {
			if (!this.theSortedKeys[i].delete(this.allTheData[deleteTarget]
					.getData(i))) {
				error = true;
			}
		}

		return error;
	}

	public void listIt(int type, boolean direction) {
		SortedIterator i = this.theSortedKeys[type].iterator();
		if (direction) {
			i.restart();
			while (i.hasNext()) {
				System.out.println(this.allTheData[i.next().getRecordNumber()]);
			}
		} else {
			i.startAtEnd();
			while (i.hasPrev()) {
				System.out.println(this.allTheData[i.prev().getRecordNumber()]);
			}
		}
	}
}
