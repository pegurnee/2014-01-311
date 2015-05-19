package project1;


/**
 * A static implementation of a stack. Used specifically here to maintain the
 * deleted student records.
 * 
 * @author Eddie Gurnee
 * @version 0.0.01 01/15/2014
 * @since 01/15/2014
 */
public class Stack {

	private int[] theStack;

	private int pointer;
	private int size;

	/**
	 * Constructs a new Stack.
	 */
	public Stack() {
		this(50);
	}

	/**
	 * Constructs a new Stack allowing for a max size to be input.
	 * 
	 * @param size
	 *            the maximum size that the stack can be
	 */
	public Stack(int size) {
		this.size = size;
		this.pointer = 0;
		this.theStack = new int[size];
	}

	/**
	 * Returns <code>true</code> if the stack contains no elements.
	 * 
	 * @return true if the stack is empty, false if the stack has any elements
	 */
	public boolean isEmpty() {
		return this.pointer == 0;
	}

	/**
	 * Returns <code>true</code> if the stack is at the maximum allowed.
	 * 
	 * @return true if the stack is full, false if there is still room in the
	 *         stack
	 */
	public boolean isFull() {
		return this.size == this.pointer;
	}

	/**
	 * Returns the value of the top element on the stack and decrements the
	 * stack pointer.
	 * 
	 * @return the top item of the stack
	 */
	public int pop() {
		return this.theStack[--this.pointer];
	}

	/**
	 * Adds a new element to the top of the stack (the current stack pointer),
	 * and increments the stack pointer.
	 * 
	 * @param toPush
	 *            the element that is to be pushed on the stack
	 */
	public void push(int toPush) {
		this.theStack[this.pointer++] = toPush;
	}

}
