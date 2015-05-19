package project1;


/**
 * The records that are used in the SortedArrays to keep track of the location
 * of the student's records in the DataStructure.
 * 
 * @author Eddie Gurnee
 * @version 0.0.03 12/16/2013
 * @since 12/02/2013
 */
public class IndexRecord implements Comparable<IndexRecord> {
	private String key; // the specific element for the given record
	private int recordNumber; // the location where the data is kept in the data
								// structure

	/**
	 * Constructs a new IndexRecord.
	 */
	public IndexRecord() {
		super();
	}

	/**
	 * Constructs a new IndexRecord, allowing for the key and recordNumber to be
	 * input.
	 * 
	 * @param key
	 *            the key value each record will be sorted by
	 * @param recordNumber
	 *            the location where the DataStructureRecord is located inside
	 *            of the DataStructure's Array.
	 */
	public IndexRecord(String key, int recordNumber) {
		super();
		this.key = key;
		this.recordNumber = recordNumber;
	}

	/**
	 * Returns the value of the key.
	 * 
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Returns the value of the record number.
	 * 
	 * @return the recordNumber
	 */
	public int getRecordNumber() {
		return recordNumber;
	}

	/**
	 * Sets the value of the key.
	 * 
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Sets the value of the record number.
	 * 
	 * @param recordNumber
	 *            the recordNumber to set
	 */
	public void setRecordNumber(int recordNumber) {
		this.recordNumber = recordNumber;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(IndexRecord otherIndexRecord) {
		return this.key.compareTo(otherIndexRecord.getKey());
	}
}
