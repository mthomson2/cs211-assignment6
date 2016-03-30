/*
 * <h1>Apple Class</h1
 * Apple class is a child class of Fruit.
 * The apple can be either green, yellow, or red.
 * This class has a unique toString method to show 
 * the color and the size.
 * 
 * @author Molly Thomson
 * @version 1.0
 * @since 2015-11-19
 */

public class Apple extends Fruit {
	
	/*
	 * Enum for constants. An appple can be 
	 * green, yellow, or red
	 */
	public enum Color {
		GREEN, YELLOW, RED;
		
		/*
		 * Color method to get a random color from the enum.
		 * values() is the elements in enum (colors)
		 *
		 * @return random color constant
		 */
		public static Color getRandomColor() {
			return values()[(int) (Math.random() * values().length)];
		}
	}
	
	protected Color color;
	
	/*
	 * Constructor for Apple class. Sets the random apple color
	 */

	public Apple() {
		super();
		this.color = Color.getRandomColor();
	}
	
	/*
	 * @return color gets the member variable 
	 */
	public Color getColor() {
		return color;
	}

	public int getAppleSize() {
		//System.out.println("Size: " + size);
		return size;
	}
	
	/*
	 * @toString containing A for apple, character of color, and size
	 */
	public String toString() {
		return "A["+(color.toString().charAt(0))+"|"+size+"]";
	}
}