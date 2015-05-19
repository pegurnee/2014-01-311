/**
 * This will be the main driver program for many of your programs. Specifically,
 * you will need to define a data structure and related algorithms to use with
 * this program. We will be using the data file I have provided for you: a file
 * of 68 records. Each record is composed of three fields: String lastName
 * String firstName String ID ID may be implemented as an integer, but it is
 * easier to implement as a string. Both lastName and firstName may not be
 * unique, but the ID **is** unique.
 * 
 * @author Bill Sverdlik
 * @version Version 1.0
 */

/*
 * IMPORTANT!!!!!! Your projects should not contain any code in this file that
 * modifies the class DataStructure directly. You may find it convenient (but
 * not required) that the file DataStructure contain an inner class.
 */
package project2;

import java.util.Scanner;

/**
 * @author Eddie Gurnee
 * @version 0.0.06 01/08/2013
 * @since 12/02/2013
 */
public class COSC311Driver {
	private static DataStructure myStructure;
	private static Scanner kb;

	public static void main(String[] args) {
		/*
		 * The following declaration declares a data structure that will change
		 * from one assignment to the next. For example, you will need to
		 * implement the following as a doubly linked list, as well as a tree.
		 */

		myStructure = new DataStructure();
		kb = new Scanner(System.in);

		//loads initial data
		myStructure.insertFromFile("DataSet.txt");
		
		int response;

		do {
			System.out.println("1: Add a new student");
			System.out.println("2: Delete a student");
			System.out.println("3: Find a student by ID");
			System.out.println("4: List students");
			System.out.println("");
			System.out.println("0: End");

			System.out.print("-" + System.getProperty("user.name") + "$ ");
			response = kb.nextInt();

			switch (response) {
			case 0:
				break;
			case 1:
				addIt();
				break;
			case 2:
				deleteIt();
				break;
			case 3:
				findIt();
				break;
			case 4:
				listIt();
				break;
			default:
				System.out.println("Invalid response.");
			}
			System.out.println();
		} while (response != 0);

		kb.close();

	}

	public static void addIt() {
		String fName, lName, tempID;
		System.out.print("Enter a unique ID number to add: ");
		do {
			tempID = kb.next().trim();

			if (myStructure.search(tempID)) {
				System.out.println("The entered ID already in use.");
				System.out.print("Please enter a unique ID: ");
			} else {
				break;
			}
		} while (true);

		System.out.print("Enter first name: ");
		fName = kb.next();
		System.out.print("Enter last name: ");
		lName = kb.next();
		try {
			myStructure.insert(fName, lName, tempID);
		} catch (IDAlreadyExistsException e) {
			// this certainly will not happen, as the ID was forced to be
			// wicked legit.
		}
		System.out.println("Student Added.");
	}

	public static void deleteIt() {
		System.out.print("Enter the ID number "
				+ "of the student's record to delete: ");
		String tempID = kb.next();

		if (myStructure.search(tempID)) {
			if (myStructure.delete(tempID)) {
				System.err.println("There was an error deleting data.");
			} else {
				System.out.println("Student deleted.");
			}
		} else {
			System.out.println("That student ID does not exist.");
		}
	}

	public static void findIt() {
		System.out.print("Enter the ID number "
				+ "of the student's record to view: ");
		String tempID = kb.next();

		if (myStructure.search(tempID)) {
			myStructure.view(tempID);
		} else {
			System.out.println("That student ID does not exist.");
		}
	}

	public static void listIt() {
		System.out.println("List students by:");
		System.out.println("1: First name");
		System.out.println("2: Last name");
		System.out.println("3: ID");
		System.out.print("-" + System.getProperty("user.name") + "$ ");
		int type = kb.nextInt();
		System.out.println();

		System.out.println("Order of students:");
		System.out.println("1: Ascending");
		System.out.println("2: Descending");
		System.out.print("-" + System.getProperty("user.name") + "$ ");
		int order = kb.nextInt();
		System.out.println();

		myStructure.listIt(type - 1, (0 == (order - 1)));
	}
}