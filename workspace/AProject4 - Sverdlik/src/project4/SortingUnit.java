package project4;

import java.util.Arrays;

/**
 * 
 * @author Eddie Gurnee
 * @version 0.0.04 01/09/2014
 * @since 12/16/2013
 */
public class SortingUnit {
	private int[] toSort;
	private int nElems;

	public SortingUnit(int[] toSort) {
		this.toSort = toSort.clone();
		this.nElems = toSort.length;
	}

	public boolean quickSort() {
		// TODO Auto-generated method stub
		if (this.nElems == 0) {
			return false;
		} else {
			return true;
		}

	}

	public boolean heapSort() {
		// TODO Auto-generated method stub
		if (this.nElems == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean mergeSort() {
		// TODO Auto-generated method stub
		if (this.nElems == 0) {
			return false;
		} else {
			int[] oneWouldSayTemp = new int[nElems];
			
			return true;
		}
	}
}