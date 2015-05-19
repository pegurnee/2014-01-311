package project3;

/**
 * 
 * @author Eddie Gurnee
 * @version 0.0.06 03/23/2014
 * @since 12/28/2013
 * 
 */
public class BinarySearchTree {

	/**
	 * 
	 * @author Eddie Gurnee
	 *
	 */
	private class Node {
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
			this.lSideIsAThreadInDisguise = true;
			this.rSideIsAThreadInDisguise = true;
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
	}

	/**
	 * in the time of chimpanzees i was a monkey (-Beck)
	 * 
	 * @author Eddie Gurnee
	 * @version 0.0.02 03/20/2014
	 * @since 03/20/2014
	 * 
	 */
	public class TreeCrawler {
		private Node position;

		/**
		 * El constructor sin parametros para el arbol de busqueda binaria.
		 */
		public TreeCrawler() {
			this.position = root;
		}

		/**
		 * No es este codigo es extranamente similar a hasNext()?
		 * 
		 * @return true si hay un predecesor finde
		 */
		public boolean hasPrev() {
			return (this.position != null);
		}

		/**
		 * No es este codigo es extranamente similar a hasPrev()?
		 * 
		 * @return true si hay un sucesor a finde
		 */
		public boolean hasNext() {
			return (this.position != null);
		}

		/**
		 * Devuelve IndexRecord de la posicion actual y la posicion se mueve al sucesor finde.
		 * 
		 * @return la IndexRecord de la ubicacion actual
		 */
		public IndexRecord getNext() {
			IndexRecord toReturn = this.position.data;
			this.position = this.position.getInOrderSuccessor();
			return toReturn;
		}

		/**
		 * Devuelve IndexRecord de la posicion actual y la posicion se mueve al predecesor finde.
		 * 
		 * @return la IndexRecord de la ubicacion actual
		 */
		public IndexRecord getPrev() {
			IndexRecord toReturn = this.position.data;
			this.position = this.position.getInOrderPredecessor();
			return toReturn;
		}

		/**
		 * Mueve todo el camino hasta el nodo (el mas pequeno) izquierda.
		 */
		public void startAtTheLMost() {
			this.position = root;
			while (this.position.lChild != null) {
				this.position = this.position.lChild;
			}
		}

		/**
		 * Mueve todo el camino a la derecha del nodo (el mas alto).
		 */
		public void startAtTheRMost() {
			this.position = root;
			while (this.position.rChild != null) {
				this.position = this.position.rChild;
			}
		}
	}

	private Node root; // the all-father
	private int nElems;

	/**
	 * 
	 */
	public BinarySearchTree() {
		this.root = null;
		this.nElems = 0;
	}
	/**
	 * 
	 * @param theRecordNum
	 * @return
	 */
	public Node delete(int theRecordNum) {
		Node deleteTarget = this.root;
		// slide to the left
		while (deleteTarget != null && deleteTarget.lChild != null) {
			deleteTarget = deleteTarget.lChild;
		}

		// do some searching
		while (deleteTarget != null && deleteTarget.data.getRecordNumber() != theRecordNum) {
			deleteTarget = deleteTarget.getInOrderSuccessor();
		}

		// right foot two times
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

				if (!successorFinderOfDoom.data.equals(deleteTarget.rChild.data) && !successorFinderOfDoom.rSideIsAThreadInDisguise) {
					// remove the in order successor from the flow
					inOrderSuccessorParent.lChild = successorFinderOfDoom.rChild;
					inOrderSuccessorParent.lSideIsAThreadInDisguise = false;
				}

				// it's not cheating if it is symmetrical
				deleteTarget.getInOrderPredecessor().rChild = deleteTarget.getInOrderSuccessor();
				deleteTarget.getInOrderSuccessor().lChild = deleteTarget.getInOrderPredecessor();

				deleteTarget.data = successorFinderOfDoom.data;
			} else if ((deleteTarget.lSideIsAThreadInDisguise && !deleteTarget.rSideIsAThreadInDisguise) 
					|| (!deleteTarget.lSideIsAThreadInDisguise && deleteTarget.rSideIsAThreadInDisguise)) { // delete node has 1 child
				if (motherOfThePoorNodeToBeDestroyed == null) { // if the delete node is the head and has only one child
					deleteTarget.getInOrderSuccessor().lChild = deleteTarget.getInOrderPredecessor();
					deleteTarget.getInOrderPredecessor().rChild = deleteTarget.getInOrderSuccessor();
					this.root = this.root.lSideIsAThreadInDisguise ? this.root.rChild : this.root.lChild;
				} else {
					Node nodeThatIsCurrentlyPointingToTheDeleteTargetAndMustPointToItNoLonger;
					if (deleteTarget.lSideIsAThreadInDisguise) {
						nodeThatIsCurrentlyPointingToTheDeleteTargetAndMustPointToItNoLonger = deleteTarget.getInOrderSuccessor();
						nodeThatIsCurrentlyPointingToTheDeleteTargetAndMustPointToItNoLonger.lChild = deleteTarget.lChild;
						nodeThatIsCurrentlyPointingToTheDeleteTargetAndMustPointToItNoLonger.lSideIsAThreadInDisguise = true;
					} else {
						nodeThatIsCurrentlyPointingToTheDeleteTargetAndMustPointToItNoLonger = deleteTarget.getInOrderPredecessor();
						nodeThatIsCurrentlyPointingToTheDeleteTargetAndMustPointToItNoLonger.rChild = deleteTarget.rChild;
						nodeThatIsCurrentlyPointingToTheDeleteTargetAndMustPointToItNoLonger.rSideIsAThreadInDisguise = true;
					}

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

				if (motherOfThePoorNodeToBeDestroyed == null) { // if the delete node is the head
					this.root = null;
				} else {
					boolean toBeDeletedChildIsOnTheLeft = (motherOfThePoorNodeToBeDestroyed.data.compareTo(deleteTarget.data) > 0);
					if (toBeDeletedChildIsOnTheLeft) { // steal the child thread
						motherOfThePoorNodeToBeDestroyed.lChild = deleteTarget.lChild;
						motherOfThePoorNodeToBeDestroyed.lSideIsAThreadInDisguise = true;
					} else {
						motherOfThePoorNodeToBeDestroyed.rChild = deleteTarget.rChild;
						motherOfThePoorNodeToBeDestroyed.rSideIsAThreadInDisguise = true;
					}
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
		Node curiosity = this.root;
		int targetRecordNumber = this.nElems;
		while (curiosity != null) {
			int compareValue = curiosity.data.getKey().compareTo(target);
			if (compareValue == 0) {
				targetRecordNumber = curiosity.data.getRecordNumber();
				break;
			}

			// if you don't check for the threads, you'll be checking too much
			// silly goose
			if (compareValue > 0) {
				if (curiosity.lSideIsAThreadInDisguise) {
					break;
				}
				curiosity = curiosity.lChild;
			} else {
				if (curiosity.rSideIsAThreadInDisguise) {
					break;
				}
				curiosity = curiosity.rChild;
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
					// but you say he's just a thread (-biz markie)
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
	public TreeCrawler iterator() {
		return new TreeCrawler();
	}

	/**
	 * Traded 32 bits in memory instead of writing the method to calculate the
	 * length recursively each time. You're welcome processor.
	 * 
	 * @return the amount of elements in the sorted list
	 */
	public int size() {
		return this.nElems;
	} // end size()
}