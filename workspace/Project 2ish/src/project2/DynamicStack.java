package project2;

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
	 * It's a node. It holds things and links to things.
	 * 
	 * @author Eddie
	 * 
	 */
	private class Node {
		private Node link;
		private int recordNum;

		private Node(int recordNum) {
			this.recordNum = recordNum;
			this.link = null;
		}
	}

	private Node head;

	/**
	 * 
	 */
	public DynamicStack() {
		this.head = null;
	}

	/**
	 * @param nodeData
	 */
	public void push(int nodeData) {
		Node newNode = new Node(nodeData);
		newNode.link = this.head;
		this.head = newNode;
	}

	/**
	 * @return the record number that exists at the top of the stack
	 */
	public int pop() {
		int temp = this.head.recordNum;
		this.head = this.head.link;
		return temp;
	}

	/**
	 * @return <code>true</code> if there are no elements in the list
	 */
	public boolean isEmpty() {
		return this.head == null;
	}
}
