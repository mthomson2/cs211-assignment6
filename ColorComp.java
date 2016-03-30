import java.util.Comparator;

/*
 * Compares apples colors. Colors are sorted in 
 * the order of Green --> Yellow --> Red
 *
 * @author Molly Thomson
 * @version 1.0
 */

public class ColorComp implements Comparator<Apple> {

	public int compare(Apple f1, Apple f2) {
		// Green then Yellow then Red 
		Apple.Color f1Color = f1.getColor();
		Apple.Color f2Color = f2.getColor();


		if (f1Color.equals(f2Color)) {
			return 0;
		}
		else if (f1Color.equals(Apple.Color.RED)) {
			//Red is smallest color. Will always be last. 
			return 1;
		}
		else if (f1Color.equals(Apple.Color.YELLOW)) {
			if (f2Color.equals(Apple.Color.RED)) {
				return -1;
			}
			//Yellow is after Green
			else {
				return 1;
			}
		}
		//Green case. Green will always be first,
		else if (f1Color.equals(Apple.Color.GREEN)) {
			return -1;
		}
		//Default
		return 1;
	}

}