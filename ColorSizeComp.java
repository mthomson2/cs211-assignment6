import java.util.Comparator;

/*
 * Sorts the size and the color of apples
 *
 * @author Molly Thomson
 * @version 1.0
 */

public class ColorSizeComp implements Comparator<Apple> {
	/**
	 * Compare method compares by color and then by size
	 *
	 * @param f1 first apple
	 * @param f2 second apple
	 * @return number based on comparisons
	 */

	public int compare(Apple f1, Apple f2) {

		Apple.Color f1Color = f1.getColor();
		Apple.Color f2Color = f2.getColor();

		if (f1Color.equals(f2Color)) {
			return f1.size-f2.size;
		}
		else {
			//implementation of compareTo method for the size (book)
			return f1Color.compareTo(f2Color);
		}
	}

}