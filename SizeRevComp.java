import java.util.Comparator;

/*
 * <h1>SizeRevComp Class</h1>
 * Example comparator that implements Comparator<Fruit>
 * Actually compares the values of oranges and apples
 *
 * @author Raven Russel & Molly Thomson
 * @version 1.0
 * @since 2015-11-19
 */
public class SizeRevComp implements Comparator<Fruit> {

	/*
	 * Will return positive number, negative number,
	 * or zero depending on result
	 *
	 * @param Fruit f1 first fruit to be compared
	 * @param Fruit f2 fruit to be compared to
	 * @return +#,-#, or 0 (if equal)
	 */
	public int compare(Fruit f1, Fruit f2) {
		return f2.getSize()-f1.getSize();
	}
}