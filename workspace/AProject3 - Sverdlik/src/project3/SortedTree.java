package project3;
/**
 * 
 * @author Eddie Gurnee
 * @version 0.0.06 01/08/2014
 * @since 12/30/2013
 */
public class SortedTree {
	public class SortedLeaf {
		private IndexRecord data;
		private SortedLeaf left;
		private SortedLeaf right;
		private boolean isLeftThread;
		private boolean isRightThread;
		
	}
	public class SortedIterator {

		public void restart() {
			// TODO Auto-generated method stub
			
		}

		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		public void startAtEnd() {
			// TODO Auto-generated method stub
			
		}

		public boolean hasPrev() {
			// TODO Auto-generated method stub
			return false;
		}

		public IndexRecord next() {
			// TODO Auto-generated method stub
			return null;
		}

		public IndexRecord prev() {
			// TODO Auto-generated method stub
			return null;
		}

	}
	private SortedLeaf root;
	private int nElems;
	public int find(String tempID) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int size() {
		return this.nElems;
	}

	public void insert(IndexRecord indexRecord) {
		// TODO Auto-generated method stub		
	}

	public IndexRecord getRecord(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean delete(String firstName) {
		// TODO Auto-generated method stub
		return false;
	}

	public SortedIterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
