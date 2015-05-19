package project4;

/**
 * 
 * @author eddie
 * @version 0.0.04
 * 
 */
public class MergeSortUnit extends SortUnit {

	public MergeSortUnit(int[] someCollectionOfNumbers) {
		this.theData = someCollectionOfNumbers;

	}

	@Override
	public void sort() {
		int[] workspace = new int[this.theData.length];

		this.recMergeSort(workspace, 0, this.theData.length - 1);
	}

	@Override
	public String getSortType() {
		return "Merge Sort";
	}

	/**
	 * From notes
	 * 
	 * @param arrA
	 * @param szA
	 * @param arrB
	 * @param szB
	 * @param arrC
	 */
	public void merge(int[] arrA, int szA, int[] arrB, int szB, int[] arrC) {
		int aIn = 0, bIn = 0, cIn = 0;
		while (aIn < szA && bIn < szB) {
			if (arrA[aIn] < arrB[bIn]) {
				arrC[cIn++] = arrA[aIn++];
			} else {
				arrC[cIn++] = arrB[bIn++];
			}
		}
		while (aIn < szA) {
			arrC[cIn++] = arrA[aIn++];
		}
		while (bIn < szB) {
			arrC[cIn++] = arrB[bIn++];
		}
	}

	/**
	 * From notes
	 * 
	 * @param workspace
	 * @param lowerbound
	 * @param upperbound
	 */
	private void recMergeSort(int[] workspace, int lowerbound, int upperbound) {
		if (lowerbound == upperbound) {
			return;
		} else {
			int mid = (upperbound + lowerbound) / 2;
			recMergeSort(workspace, lowerbound, mid);
			recMergeSort(workspace, mid + 1, upperbound);
			merge(workspace, lowerbound, mid + 1, upperbound);
		}
	}

	/**
	 * From notes
	 * 
	 * @param workspace
	 * @param loPtr
	 * @param hiPtr
	 * @param upperbound
	 */
	public void merge(int[] workspace, int loPtr, int hiPtr, int upperbound) {
		int j = 0;
		int lowerbound = loPtr;
		int mid = hiPtr - 1;
		int n = upperbound - lowerbound + 1;
		while (loPtr <= mid && hiPtr <= upperbound) {
			if (this.theData[loPtr] < this.theData[hiPtr]) {
				workspace[j++] = this.theData[loPtr++];
			} else {
				workspace[j++] = this.theData[hiPtr++];
			}
		}
		while (loPtr <= mid) {
			workspace[j++] = this.theData[loPtr++];
		}
		while (hiPtr <= upperbound) {
			workspace[j++] = this.theData[hiPtr++];
		}
		for (j = 0; j < n; j++) {
			this.theData[lowerbound + j] = workspace[j];
		}
	}

}
