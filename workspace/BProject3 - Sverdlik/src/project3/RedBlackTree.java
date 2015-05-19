package project3;

/**
 * 
 * @author Eddie Gurnee
 * @version 0.0.06 03/23/2014
 * @since 12/28/2013
 * 
 */
public class RedBlackTree {
	private final boolean BLACK = false;
	private final boolean RED = true;

	/**
	 * 
	 * @author Eddie Gurnee
	 * 
	 */
	private class Node {
		private Node parent;
		private Node lChild;
		private Node rChild;
		private boolean color;
		private boolean isLeft;

		private IndexRecord data;

		/**
		 * 
		 * @param parent
		 * @param data
		 */
		public Node(IndexRecord data) {
			super();
			this.parent = null;
			this.lChild = null;
			this.rChild = null;
			this.color = RedBlackTree.this.RED;
			this.data = data;
		}

		/**
		 * 
		 * @return
		 */
		public Node getInOrderSuccessor() {
			if (this.rChild == null) {
				if (this.isLeft) {
					return this.parent;
				} else {
					Node temp = this.parent;

					while (temp != null && !temp.isLeft) {
						temp = temp.parent;
					}

					return temp;
				}
			} else {
				Node temp = this.rChild;

				while (temp.lChild != null) {
					temp = temp.lChild;
				}

				return temp;
			}
		}

		/**
		 * 
		 * @return
		 */
		public Node getInOrderPredecessor() {
			if (this.lChild == null) {
				if (!this.isLeft) {
					return this.parent;
				} else {
					Node temp = this.parent;

					while (temp != null && temp.isLeft) {
						temp = temp.parent;
					}

					return temp;
				}
			} else {
				Node temp = this.lChild;

				while (temp.rChild != null) {
					temp = temp.rChild;
				}

				return temp;
			}
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
	public RedBlackTree() {
		this.root = null;
		this.nElems = 0;
	}

	private boolean isNodeRed(Node x) {
		return (x == null ? false : x.color == RED);
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
					temp = temp.lChild;
				} else {
					temp = temp.rChild;
				}
			}

			if (deleteTarget.lChild != null && deleteTarget.rChild != null) { // delete
																				// node
				// has two
				// children
				Node successorFinderOfDoom = deleteTarget.getInOrderSuccessor();

				if (!successorFinderOfDoom.data.equals(deleteTarget.rChild.data)) {
					// remove the in order successor from the flow
					Node inOrderSuccessorParent = successorFinderOfDoom.parent;
					inOrderSuccessorParent.lChild = successorFinderOfDoom.rChild;
					inOrderSuccessorParent.lChild.parent = inOrderSuccessorParent;
					inOrderSuccessorParent.lChild.isLeft = true;
				} else {
					deleteTarget.rChild = successorFinderOfDoom.rChild;
					deleteTarget.rChild.parent = deleteTarget;
				}

				deleteTarget.data = successorFinderOfDoom.data;
			} else if (((deleteTarget.lChild != null) && (deleteTarget.rChild == null))
					|| ((deleteTarget.lChild == null) && (deleteTarget.rChild != null))) { // delete
																							// node
																							// has
																							// 1
																							// child
				if (motherOfThePoorNodeToBeDestroyed == null) { // if the delete
																// node is the
																// head and has
																// only one
																// child
					this.root =
							this.root.lChild != null ? this.root.rChild
									: this.root.lChild;
					this.root.parent = null;
				} else {
					if (deleteTarget.isLeft) {
						deleteTarget.parent.lChild = deleteTarget.lChild == null ? deleteTarget.rChild : deleteTarget.lChild;
						deleteTarget.parent.lChild.isLeft = true;
					} else {
						deleteTarget.parent.rChild = deleteTarget.rChild == null ? deleteTarget.lChild : deleteTarget.rChild;
						deleteTarget.parent.rChild.isLeft = false;
						
					}
					deleteTarget.parent.rChild.parent = deleteTarget.parent;
				}

			} else { // delete node has no children
				deleteTarget = null;
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

			if (compareValue > 0) {
				curiosity = curiosity.lChild;
			} else {
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
			temp.color = this.BLACK; // if root, set to black
		} else {
			Node rover = this.root;
			Node momaRover = this.root;
			boolean theRoverJustDecidedToGoToTheLeft = false;

			while (rover != null) {

				/*
				 * $$$$$$$$ 
				 * This is the first color swap, 
				 * black head, red children becomes,
				 * red head, black children
				 * $$$$$$$$
				 */
				if (rover.color && momaRover != this.root) { // if rover is red
					if (rover.isLeft) { // on the left
						if (this.isNodeRed(momaRover.rChild)) { // check the
																// right
							this.colorSwap(momaRover);
						}
					} else {
						if (this.isNodeRed(momaRover.lChild)) {
							this.colorSwap(momaRover);
						}
					}
				}

				momaRover = rover;
				int compareVal = data.compareTo(rover.data);
				// the new value is less than the current value
				if (compareVal < 0) {
					theRoverJustDecidedToGoToTheLeft = true;
					rover = rover.lChild;
				} else {
					theRoverJustDecidedToGoToTheLeft = false;
					rover = rover.rChild;
				}
			}

			temp.parent = momaRover;
			if (theRoverJustDecidedToGoToTheLeft) {
				// connecting moma to child
				momaRover.lChild = temp;
				temp.isLeft = true;
			} else {
				// connecting moma to child
				momaRover.rChild = temp;
				temp.isLeft = false;
			}

			/*
			 * $$$$$$$$ 
			 * This is the second color swap, 
			 * the rotation swapping!
			 * inside and outside rotations
			 * $$$$$$$$
			 */
			if (momaRover.color) {
				if ((temp.isLeft && momaRover.isLeft)
						|| (!temp.isLeft && !momaRover.isLeft)) { // outside
																	// rotations
					if (temp.isLeft) { // in a row /-way
						this.rotate(temp, 0);
					} else { // in a row \-way
						this.rotate(temp, 1);
					}
				} else { // inside rotations
					if (!temp.isLeft) { // in a <-way
						this.rotate(temp, 2);
					} else { // in a >-way
						this.rotate(temp, 3);
					}
				}
			}
		}
		this.nElems++;
	} // end insert()

	private void colorSwap(Node root) {
		root.color = this.RED;
		root.lChild.color = this.BLACK;
		root.rChild.color = this.BLACK;
	}

	private void rotate(Node base, int version) {
		Node mamaBase = base.parent;
		Node gramaBase = mamaBase.parent;
		IndexRecord temp;
		switch (version) {
			case 0: // row /-way
				mamaBase.lChild = mamaBase.rChild; // swaps hands
				mamaBase.rChild = gramaBase.rChild; // grabs gma's child
				gramaBase.lChild = base; // makes root happy
				gramaBase.rChild = mamaBase; // makes root happier

				mamaBase.isLeft = false;

				temp = gramaBase.data; // move
				gramaBase.data = mamaBase.data; // around
				mamaBase.data = temp; // the data
				break;
			case 1: // row \-way
				mamaBase.rChild = mamaBase.lChild; // swaps hands
				mamaBase.lChild = gramaBase.lChild; // grabs gma's child
				gramaBase.rChild = base; // makes root happy
				gramaBase.lChild = mamaBase; // makes root happier

				mamaBase.isLeft = true;

				temp = gramaBase.data; // move
				gramaBase.data = mamaBase.data; // around
				mamaBase.data = temp; // the data
				break;
			case 2: // in <-way
				mamaBase.rChild = base.lChild; // swaps hands
				base.lChild = base.rChild; // grabs gma's child
				base.rChild = gramaBase.rChild; // makes root happy
				gramaBase.rChild = base; // makes root happier

				temp = gramaBase.data; // move
				gramaBase.data = base.data; // around
				base.data = temp; // the data
				break;
			case 3: // in >-way
				mamaBase.lChild = base.rChild; // swaps hands
				base.rChild = base.lChild; // grabs gma's child
				base.lChild = gramaBase.lChild; // makes root happy
				gramaBase.lChild = base; // makes root happier

				temp = gramaBase.data; // move
				gramaBase.data = base.data; // around
				base.data = temp; // the data
				break;
		}
		base.parent = gramaBase; // makes baby happy
	}

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