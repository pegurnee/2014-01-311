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
	private int numStudents = 0;

	public DataStructureRecord[] allTheData = new DataStructureRecord[this.DATA_SIZE];

	private SortedList fNameIndex = new SortedList();
	private SortedList lNameIndex = new SortedList();
	private SortedList idIndex = new SortedList();

	public DataStructure() {
		this.init();
	}

	public boolean search(String tempID) {
		return this.idIndex.find(tempID) != this.idIndex.size();
	}

	public void init() {
		Scanner dataInput = null;
		try {
			dataInput = new Scanner(
					new FileInputStream(new File("DataSet.txt")));
			while (dataInput.hasNext()) {
				String tempfName = dataInput.next();
				String templName = dataInput.next();
				String tempid = dataInput.next();

				this.insert(tempfName, templName, tempid);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			dataInput.close();
		}
	}

	public void insert(String fName, String lName, String id) {
		this.allTheData[this.numStudents] = new DataStructureRecord(fName,
				lName, id);

		this.fNameIndex.insert(new IndexRecord(fName, this.numStudents));
		this.lNameIndex.insert(new IndexRecord(lName, this.numStudents));
		this.idIndex.insert(new IndexRecord(id, this.numStudents));

		this.numStudents++;
	}

	public void view(String id) {
		System.out.println(this.allTheData[this.idIndex.getRecord(id)
		                                   .getRecordNumber()]);
	}

	public boolean delete(String id) {
		int deleteTarget = this.idIndex.getRecord(id).getRecordNumber();
		boolean error = false;

		if (!this.fNameIndex.delete(this.allTheData[deleteTarget]
				.getFirstName())) {
			error = true;
		}
		if (!this.lNameIndex
				.delete(this.allTheData[deleteTarget].getLastName())) {
			error = true;
		}
		if (!this.idIndex.delete(id)) {
			error = true;
		}
		return error;
	}

	public void listIt(int type, int direction) {
		SortedIterator i = null;
		switch (type) {
		case 0:
			i = fNameIndex.iterator();
			break;
		case 1:
			i = lNameIndex.iterator();
			break;
		case 2:
			i = idIndex.iterator();
			break;
		}
		switch (direction) {
		case 0:
			i.restart();
			while (i.hasNext()) {
				System.out.println(this.allTheData[i.next().getRecordNumber()]);
			}
			break;
		case 1:
			i.startAtEnd();
			while (i.hasPrev()) {
				System.out.println(this.allTheData[i.prev().getRecordNumber()]);
			}
			break;
		}
	}
}
