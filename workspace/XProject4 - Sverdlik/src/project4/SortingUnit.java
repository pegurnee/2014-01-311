package project4;
/**
 * 
 * @author Eddie Gurnee
 * @version 0.0.01 03/17/2014
 * @since 03/17/2014
 */
public interface SortingUnit {

	/**
	 * Realiza el tipo de los datos en la unidad de clasificacion.
	 * 
	 */
	void sort();
	
	/**
	 * 
	 * @return el tipo de clase de esta unidad puede hacer.
	 */
	String getSortType();
	
	/**
	 * 
	 */
	void printToFile(String fileName);
}
