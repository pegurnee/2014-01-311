package project3;

/**
 * 
 * @author Eddie Gurnee
 * @version 0.0.04 03/20/2014
 * @since 12/28/2013
 * 
 */
public class BinarySearchTree {

	public class Node {
		private Node lChild;
		private Node rChild;
		private boolean lSideIsAThreadInDisguise;
		private boolean rSideIsAThreadInDisguise;

		private IndexRecord data;

		/**
		 * @param data
		 */
		public Node(IndexRecord data) {
			this(null, null, data);
		}

		/**
		 * @param lChild
		 * @param rChild
		 * @param data
		 */
		public Node(Node lChild, Node rChild, IndexRecord data) {
			super();
			this.lChild = lChild;
			this.rChild = rChild;
			this.data = data;
			this.lSideIsAThreadInDisguise = false;
			this.rSideIsAThreadInDisguise = false;
		}
		/**
		 * 
		 * @return
		 */
		public Node getInOrderSuccessor() {
			Node temp = this.rChild;

			if (!this.rSideIsAThreadInDisguise) {
				while (temp.lChild != null && !temp.lSideIsAThreadInDisguise) {
					temp = temp.lChild;
				}
			}

			return temp;
		}

		/**
		 * 
		 * @return
		 */
		public Node getInOrderPredecessor() {
			Node temp = this.lChild;

			if (!this.lSideIsAThreadInDisguise) {
				while (temp.rChild != null && !temp.rSideIsAThreadInDisguise) {
					temp = temp.rChild;
				}
			}

			return temp;
		}
		
		public int getRecordNum() {
			return this.data.getRecordNumber();
		}
		
		public Node getL() {
			return this.lChild;
		}

		public Node getR() {
			return this.rChild;
		}
		
		public boolean hasL() {
			return this.lSideIsAThreadInDisguise ? false : this.lChild != null;
		}

		public boolean hasR() {
			return this.rSideIsAThreadInDisguise ? false : this.rChild != null;
		}
	}

	/**
	 * In the time of chimpanzees i was a monkey
	 * 
	 * @author Eddie Gurnee
	 * @version 0.0.02 03/20/2014
	 * @since 03/20/2014
	 * 
	 */
	public class TreeClimber {
		private Node position;

		public TreeClimber() {
			this.position = root;
		}

		public boolean hasPrev() {
			return (this.position.lChild != null);
		}

		public boolean hasNext() {
			return (this.position.rChild != null);
		}

		public IndexRecord getNext() {
			IndexRecord toReturn = this.position.data;
			this.position = this.position.getInOrderSuccessor();
			return toReturn;
		}

		public IndexRecord getPrev() {
			IndexRecord toReturn = this.position.data;
			this.position = this.position.getInOrderPredecessor();
			return toReturn;
		}
		
		public void startAtTheLMost() {
			this.position = root;
			while (this.hasPrev()) {
				this.position = this.position.lChild;
			}
		}

		public void startAtTheRMost() {
			this.position = root;
			while (this.hasNext()) {
				this.position = this.position.rChild;
			}
		}
		
		public int getRecord() {
			return this.position.data.getRecordNumber();
		}
		
	}

	private Node root;
	private int nElems;

	public BinarySearchTree() {
		this.root = null;
		this.nElems = 0;
	}
	public Node delete(int theRecordNum) {
		Node deleteTarget = this.root;
		// move all the way to the left most
		while (deleteTarget != null && deleteTarget.lChild != null) {
			deleteTarget = deleteTarget.lChild;
		}

		// do some searching
		while (deleteTarget != null && deleteTarget.data.getRecordNumber() != theRecordNum) {
			deleteTarget = deleteTarget.getInOrderSuccessor();
		}

		if (deleteTarget != null) { // item was there

			Node motherOfThePoorNodeToBeDestroyed = null;
			Node temp = root;
			
			while (!deleteTarget.data.equals(temp.data)) {
				motherOfThePoorNodeToBeDestroyed = temp;
				int compareValue = deleteTarget.data.compareTo(temp.data);

				if (compareValue < 0) {
					if (temp.lSideIsAThreadInDisguise) {
						break;
					}
					temp = temp.lChild;
				} else {
					if (temp.rSideIsAThreadInDisguise) {
						break;
					}
					temp = temp.rChild;
				}
			}

			
			if (!deleteTarget.lSideIsAThreadInDisguise && !deleteTarget.rSideIsAThreadInDisguise){ // delete node has two children
				Node inOrderSuccessorParent = deleteTarget;
				Node successorFinderOfDoom = deleteTarget.rChild;
				
				while (!successorFinderOfDoom.lSideIsAThreadInDisguise) {
					inOrderSuccessorParent = successorFinderOfDoom;
					successorFinderOfDoom = successorFinderOfDoom.lChild;
				}
				
				if (successorFinderOfDoom != deleteTarget.rChild && !successorFinderOfDoom.rSideIsAThreadInDisguise) {
					// remove the in order successor from the flow
					inOrderSuccessorParent.lChild = successorFinderOfDoom.rChild;
					inOrderSuccessorParent.lSideIsAThreadInDisguise = false;
				}
				
				/*
				if (toBeDeletedChildIsOnTheLeft) {
					motherOfThePoorNodeToBeDestroyed.lChild = successorFinderOfDoom;
					motherOfThePoorNodeToBeDestroyed.lSideIsAThreadInDisguise = false;
				} else {
					motherOfThePoorNodeToBeDestroyed.rChild = successorFinderOfDoom;
					motherOfThePoorNodeToBeDestroyed.rSideIsAThreadInDisguise = false;
				}
				 */

				/*
				successorFinderOfDoom.lChild = deleteTarget.lChild;
				successorFinderOfDoom.lSideIsAThreadInDisguise = deleteTarget.lSideIsAThreadInDisguise;
				successorFinderOfDoom.rChild = deleteTarget.rChild;
				successorFinderOfDoom.rSideIsAThreadInDisguise = deleteTarget.rSideIsAThreadInDisguise;
				 */
				deleteTarget.data = successorFinderOfDoom.data;
			} else if ((deleteTarget.lSideIsAThreadInDisguise && !deleteTarget.rSideIsAThreadInDisguise) 
					|| (!deleteTarget.lSideIsAThreadInDisguise && deleteTarget.rSideIsAThreadInDisguise)) { // delete node has 1 child
				if (motherOfThePoorNodeToBeDestroyed == null) { // if the delete node is the head and has only one child
					this.root = this.root.lSideIsAThreadInDisguise ? this.root.rChild : this.root.lChild;
				} else {
					boolean toBeDeletedChildIsOnTheLeft = (motherOfThePoorNodeToBeDestroyed.data.compareTo(deleteTarget.data) > 0);
					
					if (toBeDeletedChildIsOnTheLeft) { // child is on the left
						motherOfThePoorNodeToBeDestroyed.lChild = 
								deleteTarget.lSideIsAThreadInDisguise ? deleteTarget.rChild : deleteTarget.lChild;
						motherOfThePoorNodeToBeDestroyed.lSideIsAThreadInDisguise = false;
					} else {
						motherOfThePoorNodeToBeDestroyed.rChild = 
								deleteTarget.lSideIsAThreadInDisguise ? deleteTarget.rChild : deleteTarget.lChild;
						motherOfThePoorNodeToBeDestroyed.rSideIsAThreadInDisguise = false;
					}
				}

			} else { // delete node has no children

				boolean toBeDeletedChildIsOnTheLeft = (motherOfThePoorNodeToBeDestroyed.data.compareTo(deleteTarget.data) > 0);
				if (toBeDeletedChildIsOnTheLeft) { // steal the child thread
					motherOfThePoorNodeToBeDestroyed.lChild = deleteTarget.lChild;
					motherOfThePoorNodeToBeDestroyed.lSideIsAThreadInDisguise = true;
				} else {
					motherOfThePoorNodeToBeDestroyed.rChild = deleteTarget.rChild;
					motherOfThePoorNodeToBeDestroyed.rSideIsAThreadInDisguise = true;
				}
			}
			this.nElems--;
		} // end undiscovered case

		return deleteTarget;
	} // end delete()

	/**
	 * Used to find the record number for a given key value. TREE-i-FIED!
	 * 
	 * @param target
	 * @return
	 */
	public int find(String target) {
		Node rover = this.root;
		int targetRecordNumber = this.nElems;
		while (rover != null) {
			int compareValue = rover.data.getKey().compareTo(target);
			if (compareValue == 0) {
				targetRecordNumber = rover.data.getRecordNumber();
				break;
			}

			// if you don't check for the threads, you'll be checking too much
			// silly goose
			if (compareValue > 0) {
				if (rover.lSideIsAThreadInDisguise) {
					break;
				}
				rover = rover.lChild;
			} else {
				if (rover.rSideIsAThreadInDisguise) {
					break;
				}
				rover = rover.rChild;
			}
		}

		return targetRecordNumber;
	} // end find()

	/**
	 * Does some inserting stuff. WITH TREES.
	 * 
	 * @param data
	 */
	public void insert(IndexRecord data) {
		Node temp = new Node(data);
		if (this.isEmpty()) {
			this.root = temp;
		} else {
			Node rover = this.root;
			Node momaRover = this.root;
			boolean theRoverJustDecidedToGoToTheLeft = false;

			while (rover != null) {
				momaRover = rover;
				int compareVal = data.compareTo(rover.data);
				// the new value is less than the current value
				if (compareVal < 0) {
					theRoverJustDecidedToGoToTheLeft = true;
					rover = rover.lChild;
					// oh baby you, got what i need
					if (momaRover.lSideIsAThreadInDisguise) {
						break;
					}
				} else {
					theRoverJustDecidedToGoToTheLeft = false;
					rover = rover.rChild;
					// but you say she's just a thread
					if (momaRover.rSideIsAThreadInDisguise) {
						break;
					}
				}
			}

			if (theRoverJustDecidedToGoToTheLeft) {
				// stealing moma's in-order predecessor
				temp.lChild = momaRover.lChild;
				temp.lSideIsAThreadInDisguise = true;
				// connecting moma to child
				momaRover.lChild = temp;
				momaRover.lSideIsAThreadInDisguise = false;
				// moma is bigger
				temp.rChild = momaRover;
				temp.rSideIsAThreadInDisguise = true;
			} else {
				// stealing moma's in-order successor
				temp.rChild = momaRover.rChild;
				temp.rSideIsAThreadInDisguise = true;
				// connecting moma to child
				momaRover.rChild = temp;
				momaRover.rSideIsAThreadInDisguise = false;
				// moma is smaller
				temp.lChild = momaRover;
				temp.lSideIsAThreadInDisguise = true;
			}
		}
		this.nElems++;
	} // end insert()

	/**
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return this.root == null;
	} // end isEmpty()

	/**
	 * 
	 * @return
	 */
	public TreeClimber iterator() {
		return new TreeClimber();
	}

	/**
	 * Traded 32 bits in memory instead of writing the method to calculate the
	 * length recursively each time.
	 * 
	 * @return the amount of elements in the sorted list
	 */
	public int size() {
		return this.nElems;
	} // end size()
	
	public Node getRoot() {
		return this.root;
	}
}