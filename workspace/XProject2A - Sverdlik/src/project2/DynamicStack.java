package project2;
/**
 * This is a stack. You know, pushing and popping, if you know what I mean. 
 * @author Eddie Gurnee
 * @version 2/12/2014
 * @since 2/12/2014
 * 
 */
public class DynamicStack {
	private class Node {
		private Node link;
		private int recordNum;

		private Node() {
			this.link = null;
			this.recordNum = -1;
		}

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

	public int pop() {
		int temp = this.head.recordNum;
		this.head = this.head.link;
		return temp;
	}

	public boolean isEmpty() {
		return this.head == null;
	}
}
