package project4;

/**
 * This is a stack. You know, pushing and popping, if you know what I mean.
 * 
 * @author Eddie Gurnee
 * @version 2/12/2014
 * @since 2/12/2014
 * 
 */
public class DynamicStack {
	/**
	 * It's a node. It holds things and a link to the next thing.
	 * 
	 * @author Eddie Gurnee
	 * 
	 */
	private class Node {
		private Node link;
		private long runTime;

		private Node(long runTime) {
			this.runTime = runTime;
			this.link = null;
		}
	}

	private Node head;

	private int nElems;
	private long runningSum;

	/**
	 * 
	 */
	public DynamicStack() {
		this.head = null;

		this.nElems = 0;
		this.runningSum = 0;
	}

	/**
	 * As opposed to pull.
	 * 
	 * @param nodeData
	 *            the number to include in the Stack
	 */
	public void push(long nodeData) {
		Node newNode = new Node(nodeData);
		newNode.link = this.head;

		this.nElems++;
		this.runningSum += nodeData;

		this.head = newNode;
	}

	/**
	 * Also known as soda. Or coke in the south.
	 * 
	 * @return the record number that exists at the top of the stack
	 */
	public long pop() {
		long temp = this.head.runTime;
		this.head = this.head.link;

		this.nElems--;
		this.runningSum -= temp;

		return temp;
	}

	/**
	 * This is a helpful description of the method.
	 * 
	 * @return <code>true</code> if there are no elements in the list
	 */
	public boolean isEmpty() {
		return this.head == null;
	}

	/**
	 * 
	 * @return
	 */
	public int size() {
		return this.nElems;
	}

	/**
	 * 
	 * @return
	 */
	public long avg() {
		return this.runningSum / this.nElems;
	}

	/**
	 * 
	 * @return
	 */
	public long min() {
		if (this.head == null) {
			return 0L;
		} else {
			Node temp = this.head;
			long toReturn = temp.runTime;
			while (temp != null) {
				if (temp.runTime < toReturn) {
					toReturn = temp.runTime;
				}
				temp = temp.link;
			}
			return toReturn;
		}
	}

	/**
	 * 
	 * @return
	 */
	public long max() {
		if (this.head == null) {
			return 0L;
		} else {
			Node temp = this.head;
			long toReturn = temp.runTime;
			while (temp != null) {
				if (temp.runTime > toReturn) {
					toReturn = temp.runTime;
				}
				temp = temp.link;
			}
			return toReturn;
		}
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public long getData(int type) {
		long toReturn;
		if (type == 0) {
			toReturn = this.min();
		} else if (type == 1) {
			toReturn = this.max();
		} else {
			toReturn = this.avg();
		}
		return toReturn;
	}
}
