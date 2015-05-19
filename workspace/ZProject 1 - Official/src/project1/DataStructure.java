package project1;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * DataStructure holds data for up to 100 students, allows access to the data
 * through the use of three sorted arrays each holding the indexed data of each
 * student.
 * 
 * @author Eddie Gurnee
 * @version 0.0.06 01/08/2014
 * @since 12/02/2013
 * @see DataStructureRecord
 * @see SortedArray
 * @see Stack
 * @see IDAlreadyExistsException
 */
public class DataStructure {
	private final int DATA_SIZE = 100;

	private DataStructureRecord[] todaLaInformacion;

	// firstname, lastname, id
	private SortedArray[] theSortedKeys = new SortedArray[3];
	private Stack deletedStack;

	private int studentPointer;

	/**
	 * Constructs a new DataStructure.
	 */
	public DataStructure() {
		this.todaLaInformacion = new DataStructureRecord[this.DATA_SIZE];
		for (int i = 0; i < this.theSortedKeys.length; i++) {
			this.theSortedKeys[i] = new SortedArray(this.DATA_SIZE);
		}
		this.studentPointer = 0;
		this.deletedStack = new Stack(this.DATA_SIZE);
	}

	/**
	 * Returns <code>true</code> if a given student's ID is inside of the
	 * database.
	 * 
	 * @param tempID
	 *            the ID of a student to search the database for
	 * @return true if the student ID is in the database, false if the student
	 *         ID is not in the database OR if the database is empty
	 */
	public boolean search(String tempID) {
		if (this.studentPointer == 0) {
			return false;
		} else {
			return this.theSortedKeys[2].find(tempID) != this.theSortedKeys[2]
					.size();
		}
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
                        System.out.println("This file has too much data for this database,");
                        System.out.println("not all data was inserted properly.");
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

	/**
	 * Given a student's first name, last name, and ID, inserts that student
	 * record into the database, and the respective indexes.
	 * 
	 * @param data
	 *            the three data types associated with students, first name,
	 *            last name, ID
	 * @throws IDAlreadyExistsException
	 *             an exception stating that the ID already exists
	 */
	public void insert(String... data) throws IDAlreadyExistsException {
		if (this.search(data[2])) {
			throw new IDAlreadyExistsException(data[2]);
		}
		int target = this.studentPointer;

		if (!this.deletedStack.isEmpty()) {
			target = this.deletedStack.pop();
		} else {
			this.studentPointer++;
		}

		this.todaLaInformacion[target] = new DataStructureRecord(data[0], data[1],
				data[2]);

		for (int i = 0; i < this.theSortedKeys.length; i++) {
			this.theSortedKeys[i].insert(new IndexRecord(data[i], target));
		}
	}

	/**
	 * Displays a student's record.
	 * 
	 * @param id
	 *            the ID of the student to be displayed
	 */
	public void view(String id) {
		System.out.println(this.todaLaInformacion[this.theSortedKeys[2].getRecord(id)
				.getRecordNumber()]);
	}

	/**
	 * Returns <code>true</code> if the given student was successfully deleted
	 * from the database. This is achieved by removing all index pointers to it,
	 * and adding the ID to the stack of deleted students to be used later. Uses
	 * the getData() method to remove the IndexRecord from each of the
	 * SortedArrays.
	 * 
	 * @param id
	 *            the ID of the student to be deleted
	 * @return true if the delete was successful in all of the sorted indexes,
	 *         otherwise return false
	 * @see DataStructureRecord#getData(int)
	 * @see Stack#push(int)
	 */
	public boolean delete(String id) {
		int deleteTarget = this.theSortedKeys[2].getRecord(id)
				.getRecordNumber();

		for (int i = 0; i < this.theSortedKeys.length; i++) {
			if (!this.theSortedKeys[i].delete(deleteTarget)) {
				return false;
			}
		}

		if (!this.deletedStack.isFull()) {
			this.deletedStack.push(deleteTarget);
		} else {
			System.out
					.println("Could not add " + id + " to the deleted stack.");
			System.out.println("You have deleted too many students.");
		}

		return true;
	}

	/**
	 * Outputs a list of all of the students stored in the database, listed by
	 * first name, last name, or ID, in either ascending or descending order.
	 * 
	 * @param type
	 *            the type of data to be displayed by, either first name, last
	 *            name, or ID
	 * @param direction
	 *            the direction of which data will be displayed, either
	 *            ascending or descending
	 */
	public void listIt(int type, boolean direction) {
		if (direction) {
			for (int i = 0; i < this.theSortedKeys[type].size(); i++) {
				System.out.println(this.todaLaInformacion[this.theSortedKeys[type]
						.getRecord(i).getRecordNumber()]);
			}
		} else {
			for (int i = this.theSortedKeys[type].size() - 1; i >= 0; i--) {
				System.out.println(this.todaLaInformacion[this.theSortedKeys[type]
						.getRecord(i).getRecordNumber()]);
			}
		}
	}
}