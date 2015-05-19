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
	 * @param max
	 *            the max size that the sorted array will be able to be
	 */
	public SortedArray(int max) {
		theSortedArray = new IndexRecord[max]; // create array
		nElems = 0;
	}

	/**
	 * @return the number of elements currently entered into the SortedArray
	 */
	public int size() {
		return nElems;
	}

	/**
	 * @param target
	 *            the key value of an IndexRecord to find within the SortedArray
	 * @return the index of where the target was found, or the current number of
	 *         elements if the target was not found
	 */
	public int find(String target) {
		int lo = 0;
		int hi = nElems - 1;
		int mid;

		while (true) {
			mid = (lo + hi) / 2;
			int compare = theSortedArray[mid].getKey().compareTo(target);
			if (compare == 0) {
				return mid; // found it
			} else if (lo > hi) {
				return nElems; // can't find it
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
	 * @param value
	 *            an IndexRecord to insert into the SortedArray
	 */
	public void insert(IndexRecord value) {
		int destination;
		for (destination = 0; destination < nElems; destination++) {
			// find where it goes
			int compare = theSortedArray[destination].getKey().compareTo(
					value.getKey());
			if (compare > 0) { // (linear search)
				break;
			}
		}
		for (int biggerNums = nElems; biggerNums > destination; biggerNums--) {
			// move bigger ones up
			theSortedArray[biggerNums] = theSortedArray[biggerNums - 1];
		}
		theSortedArray[destination] = value; // insert it
		nElems++; // increment size
	} // end insert()

	/**
	 * @param value
	 *            the key value of an IndexRecord to delete
	 * @return true if the record was deleted, false if the record wasn't found
	 */
	public boolean delete(String value) {
		int deleteLocation = this.find(value);
		if (deleteLocation == nElems) {// can't find it
			return false;
		} else { // found it
			for (int biggerNums = deleteLocation; biggerNums < nElems; biggerNums++) {
				// move bigger ones down
				theSortedArray[biggerNums] = theSortedArray[biggerNums + 1];
			}
			nElems--; // decrement size
			return true;
		}
	} // end delete()

	/**
	 * @param theValue
	 *            the value of the IndexRecord to get
	 * @return the IndexRecord with the value of theValue
	 */
	public IndexRecord getRecord(String theValue) {
		return this.theSortedArray[this.find(theValue)];
	}

	/**
	 * @param theIndex
	 *            the index of the IndexRecord to get
	 * @return the IndexRecord at the index of theIndex
	 */
	public IndexRecord getRecord(int theIndex) {
		return this.theSortedArray[theIndex];
	}
} // end class SortedArray