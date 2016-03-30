/*
* <h1>Orange Class</h1>
* Orance class is a child class of Fruit
* 
* @author  Molly Thomson
* @version 1.0
* @since 2015-11-19
*/

public class Orange extends Fruit {
	
	/*
	 * Constructor for Orange class
	 * calls parent class constructor for size
	 */
	public Orange() {
		super();
	}
	
	/*
	 * @return toString statement
	 */
	public String toString() {
		return "O["+size+"]";
	}
}