package project2;

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

	public DynamicStack() {
		this.head = null;
	}

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
}
