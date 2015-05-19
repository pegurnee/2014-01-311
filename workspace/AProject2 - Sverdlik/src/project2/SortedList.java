package project2;

/**
 * 
 * @author Eddie Gurnee
 * @version 0.0.03 01/08/2014
 * @since 12/28/2013
 * 
 */
public class SortedList {

	public class SortedIterator {
		private SortedNode position;

		public SortedIterator() {
			this.position = head;
		}

		public void delete() {
			if (this.position == null) {
				throw new IllegalStateException();
			} else if (this.position.prev == null) {
				// Deleting first node
				head = head.next;
				this.position = head;
			} else if (this.position.next == null) {
				// Deleting last node
				this.position.prev.next = null; // destroys the link from the last node
				this.position = null;
			} else {
				this.position.prev.next = this.position.next;
				this.position.next.prev = this.position.prev;
				this.position = this.position.next;
			}
		}

		public void goTo(int destination) {
			this.restart();
			for (int i = 0; i < destination; i++) {
				if (!this.hasNext()) {
					throw new IllegalStateException();
				}
				this.position = this.position.next;
			}
		}

		public boolean hasNext() {
			return (this.position != null);
		}

		public boolean hasPrev() {
			return (this.position != null);
		}

		public void insertHere(IndexRecord data) {
			if (this.position == null && head != null) {
				// add to end of list. First move a temp
				// pointer to the end of the list
				SortedNode temp = head;
				while (temp.next != null) {
					temp = temp.next;
				}
				temp.next = new SortedNode(temp, null, data);
			} else if (head == null || this.position.prev == null) {
				// at head of list
				SortedList.this.addToStart(data);
			} else {
				// Insert before the current position
				SortedNode temp = new SortedNode(this.position.prev,
						this.position, data);
				this.position.prev.next = temp;
				this.position.prev = temp;
			}
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
			if (!hasNext())
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
			SortedNode temp = head;
			while (temp.next != null) {
				temp = temp.next;
			}
			this.position = temp;
		}
	}

	private class SortedNode {
		private SortedNode prev;
		private SortedNode next;
		private IndexRecord data;

		/**
		 * @param prev
		 * @param next
		 * @param data
		 */
		public SortedNode(SortedNode prev, SortedNode next, IndexRecord data) {
			super();
			this.prev = prev;
			this.next = next;
			this.data = data;
		}
	}

	private SortedNode head;
	private int nElems;

	public SortedList() {
		this.head = null;
		this.nElems = 0;
	}

	public void addToStart(IndexRecord data) {
		SortedNode newHead = new SortedNode(null, this.head, data);
		if (this.head != null) {
			this.head.prev = newHead;
		}
		this.head = newHead;
	}

	public boolean delete(String theValue) {
		int deleteLocation = this.find(theValue);
		if (deleteLocation == nElems) {// can't find it
			return false;
		} else { // found it
			SortedIterator i = new SortedIterator();
			i.goTo(deleteLocation);
			i.delete();
			this.nElems--; // decrement size
			return true;
		}
	} // end delete()

	public int find(String target) {
		int lo = 0;
		int hi = nElems - 1;
		int mid;
		SortedIterator i = new SortedIterator();

		while (true) {
			mid = (lo + hi) / 2;
			i.goTo(mid);
			int compare = i.peek().getKey().compareTo(target);
			if (compare == 0) {
				return mid; // found it
			} else if (lo > hi) {
				return nElems; // can't find it
			} else {
				if (compare < 0) {
					lo = mid + 1; // it's in upper half
				} else {
					hi = mid - 1; // it's in lower half
				}
			} // end else divide range
		} // end while
	} // end find()

	public IndexRecord getRecord(int theIndex) {
		SortedIterator i = new SortedIterator();
		i.goTo(theIndex);
		return i.peek();
	}

	public IndexRecord getRecord(String theValue) {
		return this.getRecord(this.find(theValue));
	}

	public void insert(IndexRecord newRecord) {
		SortedIterator i = new SortedIterator();
		while (i.hasNext()) {
			int compare = i.peek().getKey().compareTo(newRecord.getKey());
			if (compare > 0) { // (linear search)
				break;
			}
			i.next();
		}
		i.insertHere(newRecord);
		this.nElems++;
	}

	public SortedIterator iterator() {
		return new SortedIterator();
	}

	public int size() {
		return this.nElems;
	}
}