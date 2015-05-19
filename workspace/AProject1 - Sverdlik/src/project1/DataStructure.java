package project1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Eddie Gurnee
 * @version 0.0.06 01/06/2014
 * @since 12/02/2013
 */
public class DataStructure {
	private final int DATA_SIZE = 100;
	private int numStudents;

	private DataStructureRecord[] allTheData;
	private Stack deletedStack;

	private SortedArray fNameIndex;
	private SortedArray lNameIndex;
	private SortedArray idIndex;

	public DataStructure() {
		this.numStudents = 0;
		this.allTheData = new DataStructureRecord[this.DATA_SIZE];
		this.fNameIndex = new SortedArray(this.DATA_SIZE);
		this.lNameIndex = new SortedArray(this.DATA_SIZE);
		this.idIndex = new SortedArray(this.DATA_SIZE);

		this.deletedStack = new Stack();
		this.insertFromFile("DataSet.txt");
	}

	public boolean search(String tempID) {
		if (this.numStudents == 0) {
			return false;
		} else {
			return this.idIndex.find(tempID) != this.idIndex.size();
		}
	}

	public void insertFromFile(String fileName) {
		Scanner dataInput = null;
		try {
			dataInput = new Scanner(new FileInputStream(new File(fileName)));
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

	public boolean insert(String fName, String lName, String id) {
		if (!this.search(id)) {
			int target = this.numStudents;
			if (this.deletedStack.isEmpty()) {
				this.numStudents++;
			} else {
				target = this.deletedStack.pop();
			}
			this.allTheData[target] = new DataStructureRecord(fName, lName, id);

			this.fNameIndex.insert(new IndexRecord(fName, target));
			this.lNameIndex.insert(new IndexRecord(lName, target));
			this.idIndex.insert(new IndexRecord(id, target));
			return true;
		} else {
			return false;
		}
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

		if (!this.deletedStack.isFull()) {
			this.deletedStack.push(deleteTarget);
		}

		return error;
	}

	public void listIt(int type, int direction) {
		switch (direction) {
		case 0:
			switch (type) {
			case 0:
				for (int i = 0; i < this.fNameIndex.size(); i++) {
					System.out.println(this.allTheData[this.fNameIndex
							.getRecord(i).getRecordNumber()]);
				}
				break;
			case 1:
				for (int i = 0; i < this.lNameIndex.size(); i++) {
					System.out.println(this.allTheData[this.lNameIndex
							.getRecord(i).getRecordNumber()]);
				}
				break;
			case 2:
				for (int i = 0; i < this.idIndex.size(); i++) {
					System.out.println(this.allTheData[this.idIndex
							.getRecord(i).getRecordNumber()]);
				}
				break;
			}
			break;
		case 1:
			switch (type) {
			case 0:
				for (int i = this.fNameIndex.size() - 1; i >= 0; i--) {
					System.out.println(this.allTheData[this.fNameIndex
							.getRecord(i).getRecordNumber()]);
				}
				break;
			case 1:
				for (int i = this.lNameIndex.size() - 1; i >= 0; i--) {
					System.out.println(this.allTheData[this.lNameIndex
							.getRecord(i).getRecordNumber()]);
				}
				break;
			case 2:
				for (int i = this.idIndex.size() - 1; i >= 0; i--) {
					System.out.println(this.allTheData[this.idIndex
							.getRecord(i).getRecordNumber()]);
				}
				break;
			}
			break;
		}
	}
}
