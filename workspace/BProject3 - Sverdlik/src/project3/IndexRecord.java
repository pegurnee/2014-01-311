package project3;

/**
 * @author Eddie Gurnee
 * @version 0.0.05 03/20/2013
 * @since 12/02/2013
 */
public class IndexRecord implements Comparable<IndexRecord> {
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

	@Override
	public String toString() {
		return "Key: " + this.key + " | Record Number: " + this.recordNumber;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + recordNumber;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IndexRecord other = (IndexRecord) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (recordNumber != other.recordNumber)
			return false;
		return true;
	}

	/**
	 * Include the record number as second wave of comparison. So even when a
	 * key value is the same, the unique record number will force differences.
	 * 
	 * For to be helpful maybe?
	 */
	@Override
	public int compareTo(IndexRecord o) {
		int compVal = this.key.compareTo(o.getKey());
		return (compVal == 0 ? o.recordNumber - this.recordNumber : compVal);
	}

}
