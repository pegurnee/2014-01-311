package project1;

/**
 * Originally from Robert Lafore's Data Structures and Algorithms in Java (2nd
 * Edition) under the name of OrdArray. Adapted to a more elegant solution, for
 * a more civilized age.
 * 
 * @author Eddie Gurnee
 * @version 0.0.05 01/06/2014
 * @since 12/11/2013
 */
public class SortedArray {
	private IndexRecord[] theSortedArray;
	private int nElems; // number of data items

	/**
	 * Constructs a new SortedArray with a max size declared.
	 * 
	 * @param max
	 *            the max size that the sorted array will be able to be
	 */
	public SortedArray(int max) {
		this.theSortedArray = new IndexRecord[max]; // create array
		this.nElems = 0;
	}

	/**
	 * Returns the number of elements currently entered in the SortedArray.
	 * 
	 * @return the nElems
	 */
	public int size() {
		return this.nElems;
	}

	/**
	 * Returns the location of a requested key in the array or the current
	 * number of elements if the target was not an element of the array.
	 * 
	 * @param target
	 *            an IndexRecord to find within the SortedArray
	 * @return the index of where the target was found, or the current number of
	 *         elements if the target was not found
	 */
	public int find(String target) {
		int lo = 0;
		int hi = this.nElems - 1;
		int mid;

		while (true) {
			mid = (lo + hi) / 2;
			int compare = this.theSortedArray[mid].getKey().compareTo(target);
			if (compare == 0) {
				return mid; // found it
			} else if (lo > hi) {
				return this.nElems; // can't find it
			} else {
				if (compare < 0) {
					lo = mid + 1; // it's in upper half
				} else {
					hi = mid - 1; // it's in lower half
				}
			} // end else divide range
		} // end while
	} // end find()

	/**
	 * Inserts an IndexRecord into the array at the location where it exists
	 * lexicographically.
	 * 
	 * @param value
	 *            an IndexRecord to insert into the SortedArray
	 */
	public void insert(IndexRecord value) {
		int destination;
		for (destination = 0; destination < this.nElems; destination++) {
			// find where it goes
			int compare = this.theSortedArray[destination].compareTo(value);
			if (compare > 0) { // (linear search)
				break;
			}
		}
		for (int biggerNums = this.nElems; biggerNums > destination; biggerNums--) {
			// move bigger ones up
			this.theSortedArray[biggerNums] = this.theSortedArray[biggerNums - 1];
		}
		this.theSortedArray[destination] = value; // insert it
		this.nElems++; // increment size
	} // end insert()

	/**
	 * Returns <code>true</code> if the given element was successfully delete
	 * from the SortedArray. Deletes the element by simply pointing to the
	 * earlier element in the array.
	 * 
	 * @param value
	 *            the key value of an IndexRecord to delete
	 * @return true if the record was deleted, false if the record wasn't found
	 */
	public boolean delete(int recordNum) {
		int deleteLocation = this.findByRecordNum(recordNum);
		if (deleteLocation == this.nElems) {// can't find it
			return false;
		} else { // found it
			for (int biggerNums = deleteLocation; biggerNums < this.nElems; biggerNums++) {
				// move bigger ones down
				this.theSortedArray[biggerNums] = this.theSortedArray[biggerNums + 1];
			}
			this.nElems--; // decrement size
			return true;
		}
	} // end delete()

	/**
	 * Returns the index of a IndexRecord that contains the sought after record
	 * number. Simply a linear search as the record numbers are not sorted.
	 * 
	 * @param theNum
	 *            the record number to find
	 * @return index of the location where the the record number was found, or
	 *         the number of elements if it was not found
	 */
	private int findByRecordNum(int theNum) {
		for (int index = 0; index < this.nElems; index++) {
			if (this.theSortedArray[index].getRecordNumber() == theNum) {
				return index;
			}
		}
		return this.nElems;
	}

	/**
	 * Returns the IndexRecord with the key value of the given value.
	 * 
	 * @param theValue
	 *            the value of the IndexRecord to get
	 * @return the IndexRecord with the value of theValue
	 */
	public IndexRecord getRecord(String theValue) {
		return this.theSortedArray[this.find(theValue)];
	}

	/**
	 * Returns the IndexRecord that exists at a given location in the array.
	 * 
	 * @param theIndex
	 *            the index of the IndexRecord to get
	 * @return the IndexRecord at the index of theIndex
	 */
	public IndexRecord getRecord(int theIndex) {
		return this.theSortedArray[theIndex];
	}
} // end class SortedArray