package project4;

/**
 * 
 * @author eddie
 * @version 0.0.02
 * 
 */
public class HeapSortUnit extends SortUnit {
	private int next;

	public HeapSortUnit(int[] someCollectionOfNumbers) {
		this.theData = someCollectionOfNumbers;
		this.next = this.theData.length;
	}

	@Override
	public void sort() {
		this.heapify();

		int limit = this.next;
		for (int i = 1; i <= limit; i++) {
			int x = pop();
			this.theData[this.next] = x;
		}

	}

	@Override
	public String getSortType() {
		return "Heap Sort";
	}

	/**
	 * From notes
	 * 
	 * @param x
	 */
	public void push(int x) {
		this.theData[this.next] = x;
		this.trickleUp(this.next);
		this.next++;
	}

	/**
	 * From notes
	 * 
	 * @return
	 */
	public int pop() {
		int x = this.theData[0];
		this.theData[0] = this.theData[this.next - 1];
		this.trickleDown(0);
		this.next--;
		return x;
	}

	/**
	 * From notes
	 * 
	 * @param index
	 */
	public void trickleUp(int index) {
		int parent = (index - 1) / 2;
		int value = this.theData[index];

		while ((index > 0) && (this.theData[parent] < value)) {
			this.theData[index] = this.theData[parent];
			index = parent;
			parent = (parent - 1) / 2;
		}
		this.theData[index] = value;
	}

	/**
	 * From notes
	 * 
	 * @param index
	 */
	public void trickleDown(int index) {
		int largerChild;
		int value = this.theData[index];

		while (index < (next / 2)) {
			int left = (2 * index) + 1;
			int right = (2 * index) + 2;
			if ((right < next) && (this.theData[left] < this.theData[right])) {
				largerChild = right;
			} else {
				largerChild = left;
			}
			if (value >= this.theData[largerChild]) {
				break;
			}
			this.theData[index] = this.theData[largerChild];
			index = largerChild;
		}
		this.theData[index] = value;
	}

	/**
	 * From notes
	 * 
	 * @param newValue
	 * @param where
	 */
	public void alter(int newValue, int where) {
		int oldValue = this.theData[where];
		this.theData[where] = newValue;
		if (newValue > oldValue) {
			this.trickleUp(where);
		} else {
			this.trickleDown(where);
		}
	}

	/**
	 * From notes
	 */
	public void heapify() {
		int i = (this.next - 2) / 2;
		for (int j = i; j >= 0; j--) {
			this.trickleDown(j);
		}
	}
}
