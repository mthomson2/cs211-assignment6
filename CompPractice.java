import java.util.*;

/*
 * Main class for testing 
 * Will test orange and apple comparisons
 */

public class CompPractice {
	public static void main(String[] args) {
		//get some oranges
		List<Orange> oranges = CompPractice.makeOrangeList(5);
		System.out.println("Original List:\t\t" + oranges);
		
		//sort oranges by their "natural ordering" (their size, smallest to biggest)
		//#
		Collections.sort(oranges);
		System.out.println("Sorted by Size Only:\t" + oranges);
		
		//sort oranges in reverse order by size
		Collections.sort(oranges, new SizeRevComp()); //#2
		System.out.println("Sorted in Reverse:\t" + oranges);
		
		System.out.println();
		
		//get some apples
		List<Apple> apples = CompPractice.makeAppleList(5);
		System.out.println("Original List:\t\t" + apples);
		
		//sort apples by their "natural ordering" (their size, smallest to biggest)
		//#1
		Collections.sort(apples);
		System.out.println("Sorted by Size Only:\t" + apples);
		
		//sort apples in reverse order by size
		Collections.sort(apples, new SizeRevComp()); //#2
		System.out.println("Sorted in Reverse:\t" + apples);
		
		//sort apples by their color (green -> yellow -> red)
		//#3
		Collections.sort(apples, new ColorComp());
		System.out.println("Sorted by Color:\t" + apples);
		
		//sort apples by their color and size (green -> yellow -> red, by size within color group)
		//#4
		Collections.sort(apples, new ColorSizeComp());
		System.out.println("Sorted Color/Size:\t" + apples);
	}
	
	/*
	 * creates an ArrayList of orange sizes
	 *
	 * @param size the size the ArrayList will be
	 * @return 0 the new ArrayList 
	 */
	public static List<Orange> makeOrangeList(int size) {
		List<Orange> o = new ArrayList<>(size);
		for(int i = 0; i < size; i++) {
			o.add(new Orange());
		}
		return o;
	}
	
	/*
	 * creates an ArrayList of apple sizes
	 *
	 * @param size the size the ArrayList will be
	 * @return a the ArrayList of apples
	 */
	public static List<Apple> makeAppleList(int size) {
		List<Apple> a = new ArrayList<>(size);
		for(int i = 0; i < size; i++) {
			a.add(new Apple());
		}
		return a;
	}
}