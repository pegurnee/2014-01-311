package project3;

/**
 * This exception is thrown if a student's ID already exists in the data
 * structure.
 * 
 * @author Eddie Gurnee
 * @version 0.0.01 01/08/2014
 * @since 01/08/2014
 */
@SuppressWarnings("serial")
public class IDAlreadyExistsException extends Exception {

	/**
	 * Constructs a new IDAlreadyExistsException.
	 */
	public IDAlreadyExistsException() {
		super("That ID already exists.");
	}

	/**
	 * Constructs a new IDAlreadyExistsException with a given ID as part of the
	 * message.
	 * 
	 * @param targetID
	 *            the ID that threw the exception
	 */
	public IDAlreadyExistsException(String targetID) {
		super("The ID " + targetID + " already exists.");
	}

}
