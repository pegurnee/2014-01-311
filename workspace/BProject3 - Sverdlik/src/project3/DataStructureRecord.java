package project3;

/**
 * @author Eddie Gurnee
 * @version 0.0.04 12/24/2013
 * @since 12/02/2013
 * 
 */
public class DataStructureRecord {
	private String id;
	private String lastName;
	private String firstName;

	/**
	 * 
	 */
	public DataStructureRecord() {
		this("no first name", "no last name", "no id");
	}

	/**
	 * @param id
	 * @param lastName
	 * @param firstName
	 */
	public DataStructureRecord(String firstName, String lastName, String id) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}

	/**
	 * @param type
	 *            the type of data to obtain (firstName, lastName, id)
	 * @return the requested data (firstName, lastName, id)
	 */
	public String getData(int type) {
		String theData = "";
		switch (type) {
		case 0:
			theData = this.firstName;
			break;
		case 1:
			theData = this.lastName;
			break;
		case 2:
			theData = this.id;
			break;
		}
		return theData;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Student ID: " + this.id + " | " + this.firstName + " "
				+ this.lastName;
	}
}
