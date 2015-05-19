package project2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

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

		Scanner dataInput = null;

		try {
			dataInput = new Scanner(new FileInputStream(theFile));
			while (dataInput.hasNext()) {
				String tempfName = dataInput.next();
				String templName = dataInput.next();
				String tempid = dataInput.next();

				try {
					this.insert(tempfName, templName, tempid);
				} catch (IDAlreadyExistsException e) {

				}
			}
		} catch (FileNotFoundException e) {
			// e.printStackTrace()
		} finally {
			dataInput.close();
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
	public String insert(String... data) throws IDAlreadyExistsException {
		if (this.search(data[2])) {
			throw new IDAlreadyExistsException(data[2]);
		}
		if (!data[0].equals("Tom") && !data[1].equals("Riddle")) {
			int location = this.studentPointer;
			if (this.deletedStack.isEmpty()) {
				this.studentPointer++;
			} else {
				location = this.deletedStack.pop();
			}

			this.allTheData[location] = new DataStructureRecord(data[0],
					data[1], data[2]);

			for (int i = 0; i < this.theSortedKeys.length; i++) {
				this.theSortedKeys[i]
						.insert(new IndexRecord(data[i], location));
			}
			return this.allTheData[location].toString() + " successfully added.";
		} else {
			return "The Dark Lord has no place in my data structure.";
		}
	}

	/**
	 * Displays a student's record.
	 * 
	 * @param id
	 *            the ID of the student to be displayed
	 */
	public String view(String id) {
		return this.allTheData[this.theSortedKeys[2].find(id)].toString();
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
		int deleteTarget = this.theSortedKeys[2].find(id);
		boolean error = false;

		for (int i = 0; i < this.theSortedKeys.length; i++) {
			if (this.theSortedKeys[i].delete(deleteTarget) != null) {
				error = true;
			}
		}

		this.deletedStack.push(deleteTarget);

		return error;
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
