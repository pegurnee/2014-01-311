package project3;

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
	 * As opposed to pull.
	 * 
	 * @param nodeData
	 *            the number to include in the Stack
	 */
	public void push(int nodeData) {
		Node newNode = new Node(nodeData);
		newNode.link = this.head;
		this.head = newNode;
	}

	/**
	 * Also known as soda. Or coke in the south.
	 * 
	 * @return the record number that exists at the top of the stack
	 */
	public int pop() {
		int temp = this.head.recordNum;
		this.head = this.head.link;
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
}
