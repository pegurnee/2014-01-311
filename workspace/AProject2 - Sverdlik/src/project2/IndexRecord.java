package project2;


/**
 * @author Eddie Gurnee
 * @version 0.0.03 12/16/2013
 * @since 12/02/2013
 */
public class IndexRecord {
	private String key; // the specific element for the given record
	private int recordNumber; // the location where the data is kept in the data
								// structure

	/**
	 * 
	 */
	public IndexRecord() {
		super();
	}

	/**
	 * @param key
	 * @param recordNumber
	 */
	public IndexRecord(String key, int recordNumber) {
		super();
		this.key = key;
		this.recordNumber = recordNumber;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return the recordNumber
	 */
	public int getRecordNumber() {
		return recordNumber;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @param recordNumber
	 *            the recordNumber to set
	 */
	public void setRecordNumber(int recordNumber) {
		this.recordNumber = recordNumber;
	}

}
