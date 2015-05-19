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
	 * It's a node. Which is Spanish for gray elephant.
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
		 *            the info that the node will have
		 */
		public Node(IndexRecord data) {
			super();
			this.lChild = null;
			this.rChild = null;
			this.data = data;
			this.lSideIsAThreadInDisguise = true;
			this.rSideIsAThreadInDisguise = true;
		}

		/**
		 * 
		 * @return the dude that came direction in order after the current node
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
		 * @return the dude that came directly in order before the current node
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
	 * @see Node
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
		 * Devuelve IndexRecord de la posicion actual y la posicion se mueve al
		 * sucesor finde.
		 * 
		 * @return la IndexRecord de la ubicacion actual
		 */
		public IndexRecord getNext() {
			IndexRecord toReturn = this.position.data;
			this.position = this.position.getInOrderSuccessor();
			return toReturn;
		}

		/**
		 * Devuelve IndexRecord de la posicion actual y la posicion se mueve al
		 * predecesor finde.
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
	 * This is used to delete nodes. 3 cases of deleting nodes, all based on the
	 * number of nodes that the node targeted for deletion has: 2 children, 1
	 * child, or no children.
	 * 
	 * @param theRecordNum
	 *            the recordNum of the node to delete
	 * @return the node that has been deleted from the tree
	 */
	public Node delete(int theRecordNum) {
		Node deleteTarget = this.root;
		// slide to the left
		while (deleteTarget != null && deleteTarget.lChild != null) {
			deleteTarget = deleteTarget.lChild;
		}

		// do some searching
		while (deleteTarget != null
				&& deleteTarget.data.getRecordNumber() != theRecordNum) {
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

			// i feel fine enough i guess
			if (!deleteTarget.lSideIsAThreadInDisguise
					&& !deleteTarget.rSideIsAThreadInDisguise) { // delete node
																	// has two
																	// children
				Node inOrderSuccessorParent = deleteTarget;
				Node successorFinderOfDoom = deleteTarget.rChild;

				while (!successorFinderOfDoom.lSideIsAThreadInDisguise) {
					inOrderSuccessorParent = successorFinderOfDoom;
					successorFinderOfDoom = successorFinderOfDoom.lChild;
				}

				if (!successorFinderOfDoom.data.equals(deleteTarget.rChild.data)
						&& !successorFinderOfDoom.rSideIsAThreadInDisguise) {
					// remove the in order successor from the flow
					inOrderSuccessorParent.lChild = successorFinderOfDoom.rChild;
					inOrderSuccessorParent.lSideIsAThreadInDisguise = false;
				}

				// it's not cheating if it is symmetrical
				deleteTarget.getInOrderPredecessor().rChild =
						deleteTarget.getInOrderSuccessor();
				deleteTarget.getInOrderSuccessor().lChild =
						deleteTarget.getInOrderPredecessor();

				deleteTarget.data = successorFinderOfDoom.data;
				// considering everything's a mess (-barenaked ladies)
			} else if ((deleteTarget.lSideIsAThreadInDisguise && !deleteTarget.rSideIsAThreadInDisguise)
					|| (!deleteTarget.lSideIsAThreadInDisguise && deleteTarget.rSideIsAThreadInDisguise)) { // delete
																											// node
																											// has
																											// 1
																											// child
				if (motherOfThePoorNodeToBeDestroyed == null) { // if the delete
																// node is the
																// head and has
																// only one
																// child
					deleteTarget.getInOrderSuccessor().lChild =
							deleteTarget.getInOrderPredecessor();
					deleteTarget.getInOrderPredecessor().rChild =
							deleteTarget.getInOrderSuccessor();
					this.root =
							this.root.lSideIsAThreadInDisguise ? this.root.rChild
									: this.root.lChild;
				} else {
					// the following variable it too long to be a label in LC-3
					Node nodeThatIsCurrentlyPointingToTheDeleteTargetAndMustPointToItNoLonger;
					if (deleteTarget.lSideIsAThreadInDisguise) { // will set the
																	// thread of
																	// dude that
																	// points to
																	// the
																	// delete
																	// target to
																	// point
																	// elsewhere
						nodeThatIsCurrentlyPointingToTheDeleteTargetAndMustPointToItNoLonger =
								deleteTarget.getInOrderSuccessor();
						nodeThatIsCurrentlyPointingToTheDeleteTargetAndMustPointToItNoLonger.lChild =
								deleteTarget.lChild;
						nodeThatIsCurrentlyPointingToTheDeleteTargetAndMustPointToItNoLonger.lSideIsAThreadInDisguise =
								true;
					} else {
						nodeThatIsCurrentlyPointingToTheDeleteTargetAndMustPointToItNoLonger =
								deleteTarget.getInOrderPredecessor();
						nodeThatIsCurrentlyPointingToTheDeleteTargetAndMustPointToItNoLonger.rChild =
								deleteTarget.rChild;
						nodeThatIsCurrentlyPointingToTheDeleteTargetAndMustPointToItNoLonger.rSideIsAThreadInDisguise =
								true;
					}

					boolean toBeDeletedChildIsOnTheLeft =
							(motherOfThePoorNodeToBeDestroyed.data
									.compareTo(deleteTarget.data) > 0);

					if (toBeDeletedChildIsOnTheLeft) { // child is on the left
						motherOfThePoorNodeToBeDestroyed.lChild =
								deleteTarget.lSideIsAThreadInDisguise ? deleteTarget.rChild
										: deleteTarget.lChild; // links the non
																// thread child
																// of the delete
																// target to the
																// parent of the
																// delete target
						motherOfThePoorNodeToBeDestroyed.lSideIsAThreadInDisguise = false;
					} else {
						motherOfThePoorNodeToBeDestroyed.rChild =
								deleteTarget.lSideIsAThreadInDisguise ? deleteTarget.rChild
										: deleteTarget.lChild;
						motherOfThePoorNodeToBeDestroyed.rSideIsAThreadInDisguise = false;
					}
				}

			} else { // delete node has no children

				if (motherOfThePoorNodeToBeDestroyed == null) { // if the delete
																// node is the
																// head
					this.root = null;
				} else {
					boolean toBeDeletedChildIsOnTheLeft =
							(motherOfThePoorNodeToBeDestroyed.data
									.compareTo(deleteTarget.data) > 0);
					if (toBeDeletedChildIsOnTheLeft) { // steal the child thread
						motherOfThePoorNodeToBeDestroyed.lChild = deleteTarget.lChild;
						motherOfThePoorNodeToBeDestroyed.lSideIsAThreadInDisguise = true;
					} else { // parents should steal from their children
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
	 * Used to find the record number for a given key value. Which is rather
	 * cool when you think about it. It's basically an associative array.
	 * 
	 * @param target
	 *            the key value to find
	 * @return the record number within
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
	 * Take something (generally data and not cats), and puts it into the
	 * glorious tree of wisdom.
	 * 
	 * @param data
	 *            the cat to be put into the tree
	 */
	public void insert(IndexRecord data) {
		Node newNodeThatIsProbablyNotADragon = new Node(data);
		if (this.isEmpty()) {
			this.root = newNodeThatIsProbablyNotADragon;
		} else {
			Node rover = this.root; // ruff ruff
			Node momaRover = this.root;
			boolean theRoverJustDecidedToGoToTheLeft = false; // used to tell if
																// the rover
																// last decided
																// to go to the
																// left

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
				newNodeThatIsProbablyNotADragon.lChild = momaRover.lChild;
				newNodeThatIsProbablyNotADragon.lSideIsAThreadInDisguise = true;
				// connecting moma to child
				momaRover.lChild = newNodeThatIsProbablyNotADragon;
				momaRover.lSideIsAThreadInDisguise = false;
				// moma is bigger
				newNodeThatIsProbablyNotADragon.rChild = momaRover;
				newNodeThatIsProbablyNotADragon.rSideIsAThreadInDisguise = true;
			} else {
				// mugs your own mom, omg how could you
				newNodeThatIsProbablyNotADragon.rChild = momaRover.rChild;
				newNodeThatIsProbablyNotADragon.rSideIsAThreadInDisguise = true;
				// but your moma still loves you
				momaRover.rChild = newNodeThatIsProbablyNotADragon;
				momaRover.rSideIsAThreadInDisguise = false;
				// and you give her a hug back
				newNodeThatIsProbablyNotADragon.lChild = momaRover;
				newNodeThatIsProbablyNotADragon.lSideIsAThreadInDisguise = true;
			}
		}
		this.nElems++;
	} // end insert()

	/**
	 * Used to see if there is a substantial (one could even say absolute) lack
	 * of leaf nodes upon the active tree. This is very helpful for seeing if a
	 * given tree structure is unoccupied with nodes; often it is used to great
	 * effect at seeing if the tree is without data.
	 * 
	 * @return <code>true</code> if the tree is empty
	 */
	public boolean isEmpty() {
		return this.root == null;
	} // end isEmpty()

	/**
	 * It's an iterator for iterating.
	 * 
	 * @return a tree crawler for crawling around the tree
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