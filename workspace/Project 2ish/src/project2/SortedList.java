package project2;

/**
 * 
 * @author Eddie Gurnee
 * @version 0.0.03 01/08/2014
 * @since 12/28/2013
 * 
 */
public class SortedList {

	private class Node {
		private Node prev;
		private Node next;
		private IndexRecord data;

		/**
		 * @param data
		 */
		public Node(IndexRecord data) {
			this(null, null, data);
		}

		/**
		 * @param prev
		 * @param next
		 * @param data
		 */
		public Node(Node prev, Node next, IndexRecord data) {
			super();
			this.prev = prev;
			this.next = next;
			this.data = data;
		}
	}

	public class SortedIterator {
		private Node position;

		public SortedIterator() {
			this.position = head;
		}

		public boolean hasNext() {
			return (this.position != null);
		}

		public boolean hasPrev() {
			return (this.position != null);
		}

		public IndexRecord next() {
			if (!this.hasNext()) {
				throw new IllegalStateException();
			}
			IndexRecord toReturn = this.peek();
			this.position = this.position.next;
			return toReturn;
		}

		public IndexRecord peek() {
			if (this.position == null)
				throw new IllegalStateException();
			return this.position.data;
		}

		public IndexRecord prev() {
			if (!hasPrev()) {
				throw new IllegalStateException();
			}
			IndexRecord toReturn = this.peek();
			this.position = this.position.prev;
			return toReturn;
		}

		public void restart() {
			this.position = head;
		}

		public void startAtEnd() {
			this.position = tail;
		}
	}

	private Node head, tail;
	private int nElems;

	public SortedList() {
		this.head = null;
		this.nElems = 0;
	}

	public Node delete(int theRecordNum) {
		Node temp = this.head;
		while (temp.data.getRecordNumber() != theRecordNum && temp != null) {
			temp = temp.next;
		}

		if (temp != null) { // item was there
			if (this.head == this.tail) { // only one item in list
				this.head = null;
				this.tail = null;
			} else if (temp == this.head) { // delete head
				this.head.next.prev = null;
				this.head = this.head.next;
			} else if (temp == this.tail) { // delete tail
				this.tail.prev.next = null;
				this.tail = this.tail.prev;
			} else { // delete anywhere
				temp.prev.next = temp.next;
				temp.next.prev = temp.prev;
			} // end delete cases
			this.nElems--;
		} // end undiscovered case

		return temp;
	} // end delete()

	public int find(String target) {
		Node rover = this.head;
		int targetRecordNumber = this.nElems;
		while (rover != null && rover.data.getKey().compareTo(target) < 0) {
			rover = rover.next;
		}
		if (rover != null && rover.data.getKey().compareTo(target) == 0) {
			targetRecordNumber = rover.data.getRecordNumber();
		}

		return targetRecordNumber;
	} // end find()

	public void insert(IndexRecord data) {
		Node temp = new Node(data);
		if (this.isEmpty()) { // insert at head and tail
			this.head = temp;
			this.tail = temp;
		} else {
			Node rover = this.head;
			while (rover != null && data.compareTo(rover.data) > 0) {
				rover = rover.next;
			}

			if (rover == null) { // at tail
				temp.prev = this.tail;
				this.tail.next = temp;
				this.tail = temp;
			} else if (rover == this.head) { // at head
				temp.next = this.head;
				this.head.prev = temp;
				this.head = temp;
			} else { // anywhere else you fool!!
				temp.next = rover;
				temp.prev = rover.prev;
				rover.prev.next = temp;
				rover.prev = temp;
			}
		}
		this.nElems++;
	} // end insert()

	public boolean isEmpty() {
		return this.head == null;
	} // end isEmpty()

	public SortedIterator iterator() {
		return new SortedIterator();
	}

	/**
	 * Traded 32 bits in memory instead of writing the method to calculate the
	 * length recursively (or roverly) each time.
	 * 
	 * @return the amount of elements in the sorted list
	 */
	public int size() {
		return this.nElems;
	} // end size()
}