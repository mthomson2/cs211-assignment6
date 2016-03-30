/*
 * <h1>Comparing Apples and Oranges</h1 
 * This class will implement the Comparable interface.
 * It will compare apples and oranges by their size.
 *
 * @author Molly Thomson
 * @version 1.2
 * @since 2015-11-19
 *
 */

public class Fruit implements Comparable<Fruit>{
	protected int size;
	
	/*
	 * Constructor for Fruit class. 
	 * class member size will equal a (random number * 10) + 1 
	 */

	public Fruit() {
		this.size = (int) (Math.random() * 10)+1;
	}
	
	/*
	 * Getter method for member variable size
	 * @return size
	 */
	public int getSize() {
		return size;
	}
	
	/*
	 * toString method for class
	 * @return toString
	 */

	public String toString() {
		return "F[s="+size+"]";
	}

	/*
	* @overrides
	* @param fruit fruit to be compared to
 	* @return 1,0,or -1 depending on size comparison
	*/
	public int compareTo(Fruit fruit) {
		int newSize = size-fruit.size;
		return newSize;
	}
}