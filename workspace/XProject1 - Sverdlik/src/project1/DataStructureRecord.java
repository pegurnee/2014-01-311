package project1;

/**
 * A record of a student. First name, last name, and ID is stored.
 * 
 * @author Eddie Gurnee
 * @version 0.0.05 01/08/2013
 * @since 12/02/2013
 * @see DataStructure
 */
public class DataStructureRecord {
	private String firstName;
	private String lastName;
	private String id;

	/**
	 * Constructs a new DataStructureRecord.
	 */
	public DataStructureRecord() {
		this("no first name", "no last name", "no id");
	}

	/**
	 * Constructs a new DataStructureRecord, allowing for first name, last
	 * name, and ID to be input.
	 * 
	 * @param firstName
	 *            the first name of a student
	 * @param lastName
	 *            the last name of a student
	 * @param id
	 *            the id number of a student
	 */
	public DataStructureRecord(String firstName, String lastName, String id) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}

	/**
	 * Returns the type of data requested, either first name, last name, or
	 * student ID.
	 * 
	 * @param type
	 *            the type of data to obtain (firstName, lastName, id)
	 * @return the requested data (firstName, lastName, id)
	 * @see DataStructure#delete(String)
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
	 * Returns the first name of the student record.
	 * 
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Returns the last name of the student record.
	 * 
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Returns the student ID of the student record.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the first name of the student record.
	 * 
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Sets the last name of the student record.
	 * 
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Sets the student ID of the student record.
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
