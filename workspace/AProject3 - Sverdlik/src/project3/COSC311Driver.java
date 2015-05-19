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
package project3;

import java.util.Scanner;

/**
 * @author Eddie Gurnee
 * @version 0.0.05 01/06/2013
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

		int response;

		do {
			System.out.println("1: Add a new student");
			System.out.println("2: Delete a student");
			System.out.println("3: Find a student by ID");
			System.out.println("4: List students by ID increasing");
			System.out.println("5: List students by first name increasing");
			System.out.println("6: List students by last name increasing");
			System.out.println("7: List students by ID decreasing");
			System.out.println("8: List students by first name decreasing");
			System.out.println("9: List students by last name decreasing");
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
				listItIncreasingID(); // or see below for programming trick
				break;
			case 5:
				listItIncreasingfName();
				break;
			case 6:
				listItIncreasinglName();
				break;
			case 7:
				listItDecreasingID();
				break;
			case 8:
				listItDecreasingfName();
				break;
			case 9:
				listItDecreasinglName();
				break;
			case 100:
				for (int i = 0; i < 100; i++) {
					System.out.println(myStructure.allTheData[i]);
				}
				break;
			default:
				System.out.println("Invalid response.");
			}
			System.out.println();
		} while (response != 0);

		kb.close();

	}

	// OK Folks. I won't write all of these, but I'll give you an idea

	// Case 1: Add a new student. We need a unique ID number

	public static void addIt() {
		String fName, lName, tempID;
		System.out.print("Enter a unique ID number to add: ");
		do {
			tempID = kb.next().trim();

			// is it unique ?
			if (myStructure.search(tempID)) {
				System.out.println("The entered ID already in use.");
				System.out.print("Please enter a unique ID: ");
			} else {
				break;
			}
		} while (true);

		// We found a unique ID. Now ask for first and last name

		System.out.print("Enter first name: ");
		fName = kb.next();
		System.out.print("Enter last name: ");
		lName = kb.next();

		// add to our data structure
		myStructure.insert(fName, lName, tempID);
		System.out.println("Student Added.");
	}

	// Case 2: delete a student. A student ID must be prompted for. If the ID
	// number does not exist in the database,
	// print out a message indicating a such, otherwise delete the entire record

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

	// Case 3: find a student. A student ID must be prompted for. If the ID
	// number is not found, print out a
	// message indicating as such. Otherwise print out the entire record

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

	// Cases 4,5,6,7,8,9
	// A little programming trickery. All of the listing methods below can call
	// the SAME method in DataStructure.
	// We'll pass 2 parameters: the first indicates which field, the second
	// indicates which order
	// fieldNum=0 first name
	// fieldNum=1 last name
	// fieldNum=2 ID
	// orderNum=0 increasing
	// orderNum=1 decreasing
	public static void listItIncreasingID() {
		myStructure.listIt(2, 0);
	}

	public static void listItIncreasingfName() {
		myStructure.listIt(0, 0);
	}

	public static void listItIncreasinglName() {
		myStructure.listIt(1, 0);
	}

	private static void listItDecreasingID() {
		myStructure.listIt(2, 1);
	}

	private static void listItDecreasingfName() {
		myStructure.listIt(0, 1);
	}

	private static void listItDecreasinglName() {
		myStructure.listIt(1, 1);
	}

}