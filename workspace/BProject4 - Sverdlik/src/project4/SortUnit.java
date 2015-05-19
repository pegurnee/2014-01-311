package project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * 
 * @author Eddie Gurnee
 * @version 0.0.01 03/17/2014
 * @since 03/17/2014
 */
public abstract class SortUnit {
	protected int[] theData;
	
	/**
	 * Realiza el tipo de los datos en la unidad de clasificacion.
	 * 
	 */
	public abstract void sort();
	
	/**
	 * 
	 * @return el tipo de clase de esta unidad puede hacer.
	 */
	public abstract String getSortType();
	
	/**
	 * 
	 */
	public void printToFile(String fileName) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileOutputStream(new File(fileName)));

			for (int i = 0; i < this.theData.length; i++) {
				out.println(this.theData[i]);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}
