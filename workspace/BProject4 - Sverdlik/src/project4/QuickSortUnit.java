package project4;

/**
 * 
 * @author eddie
 * @version 0.0.03
 * 
 */
public class QuickSortUnit extends SortUnit {

	public QuickSortUnit(int[] someCollectionOfNumbers) {
		this.theData = someCollectionOfNumbers;
	}

	@Override
	public void sort() {
		this.recQuickSort(0, this.theData.length - 1);

	}

	@Override
	public String getSortType() {
		return "Quick Sort";
	}

	/**
	 * From notes, expanded with support for the pivot index
	 * 
	 * @param left
	 * @param right
	 * @param pivotInd
	 * @return
	 */
	public int partitionIt(int left, int right, int pivotInd) {
		int pivot = this.theData[pivotInd];
		this.swap(right, pivotInd);

		int leftPtr = left - 1;
		int rightPtr = right;

		while (true) {
			while ((this.theData[++leftPtr] < pivot))
				;
			while ((rightPtr > left) && (this.theData[--rightPtr] > pivot))
				;
			if (leftPtr >= rightPtr) {
				break;
			}
			this.swap(leftPtr, rightPtr);
		}
		this.swap(leftPtr, right);
		return leftPtr;
	}

	/**
	 * From notes, expanded with the getPivot method
	 * 
	 * @param left
	 * @param right
	 */
	private void recQuickSort(int left, int right) {
		if (right - left <= 0) {
			return;
		}
		int pivot = this.getPivot(left, right);

		int partition = this.partitionIt(left, right, pivot);
		this.recQuickSort(left, partition - 1);
		this.recQuickSort(partition + 1, right);
	}

	/**
	 * This one is mine
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	private int getPivot(int left, int right) {
		int diffInc = (right - left + 1);
		int num1 = (int) (Math.random() * diffInc) + left;
		int num2 = (int) (Math.random() * diffInc) + left;
		int num3 = (int) (Math.random() * diffInc) + left;

		if (this.theData[num1] > this.theData[num2]) {
			if (this.theData[num2] > this.theData[num3]) {
				return num2;
			} else if (this.theData[num3] > this.theData[num1]) {
				return num1;
			} else {
				return num3;
			}
		} else {
			if (this.theData[num2] < this.theData[num3]) {
				return num2;
			} else if (this.theData[num3] < this.theData[num1]) {
				return num1;
			} else {
				return num3;
			}
		}
	}

	/**
	 * From book
	 * 
	 * @param in1
	 * @param in2
	 */
	public void swap(int in1, int in2) {
		int temp = this.theData[in1];
		this.theData[in1] = this.theData[in2];
		this.theData[in2] = temp;
	}
}
