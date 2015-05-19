package project4;

public class scratch {
	private int[] theArray; // ref to array theArray private
	int nElems; // number of data items

	// -----------------------------------------------------------
	public void mergeSort() // called by main()
	{ // provides workspace
		int[] workSpace = new int[nElems];
		recMergeSort(workSpace, 0, nElems - 1);
	}

	// -----------------------------------------------------------
	private void recMergeSort(int[] playArray, int lo, int hi) {
		if (lo == hi) {
			return;
		} else {
			// find midpoint
			int mid = (lo + hi) / 2;
			// sort low half
			recMergeSort(playArray, lo, mid);
			// sort high half
			recMergeSort(playArray, mid + 1, hi);
			// merge them
			merge(playArray, lo, mid + 1, hi);
		} // end else
	} // end recMergeSort()
		// -----------------------------------------------------------

	private void merge(int[] workSpace, int lowPtr, int highPtr, int upperBound) {
		int j = 0; // workspace index
		int lowerBound = lowPtr;
		int mid = highPtr - 1;
		int n = upperBound - lowerBound + 1; // # of items
		while (lowPtr <= mid && highPtr <= upperBound) {
			if (theArray[lowPtr] < theArray[highPtr]) {
				workSpace[j++] = theArray[lowPtr++];
			} else {
				workSpace[j++] = theArray[highPtr++];
			}
		}
		while (lowPtr <= mid) {
			workSpace[j++] = theArray[lowPtr++];
		}
		while (highPtr <= upperBound) {
			workSpace[j++] = theArray[highPtr++];
		}
		for (j = 0; j < n; j++) {
			theArray[lowerBound + j] = workSpace[j];
		}
	} // end merge()
}
