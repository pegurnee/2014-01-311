package project1;

public class Stack {
	private int pointer;
	private int limit;
	private int[] theStack;

	public Stack() {
		this(50);
	}

	public Stack(int size) {
		this.limit = size;
		this.pointer = 0;
		this.theStack = new int[this.limit];
	}

	public int pop() {		
		return this.theStack[--this.pointer];
	}

	public void push(int deleteTarget) {
		this.theStack[this.pointer++] = deleteTarget;
	}

	public boolean isEmpty() {
		return this.pointer == 0;
	}

	public boolean isFull() {
		return this.pointer == this.limit;
	}

}
