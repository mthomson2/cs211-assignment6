import java.util.Scanner;

/**
 *  Main method for Assignment 6. Begins the program for the Juggler Simulation.
 *  Takes input from the user and prints to the screen options. 
 * 
 *  @author Molly Thomson
 */
public class Assignment6
{
	/**
	 * Prompts the user for actions. Continues with simulation, failing if Stephen drops the balls
	 * and restarts 
	 * @param args command line arguments. None are taken
	 */
	public static void main(String[] args)
	{
		Juggler stephen = new Juggler();
		System.out.println("\nStephen, the juggler, is learning to do a shower trick...");
		System.out.println("He has " + stephen.getNumUnthrownBalls() + " balls");
		Scanner in = new Scanner(System.in);
		
		while(true)
		{
			System.out.println("\n" + stephen);
			int choice = doMenu(in);
			
			//try to throw a ball
			if (choice == 1) {
				try {
					stephen.throwBall();
				}
				catch (RuntimeException e) {
					e.toString();
					System.out.println("Stephen dropped everything.");
					System.out.println("Stephen wants to try again...\nHe has " + stephen.getNumUnthrownBalls() + " balls");
					stephen = new Juggler();
				}
			}
			//try to pass a ball
			else if (choice == 2) {
				try {
					stephen.passBall();
				}
				catch (Exception e) {
					System.out.println("Stephen dropped everything.");
					System.out.println("Stephen wants to try again...\nHe has " + stephen.getNumUnthrownBalls() + " balls");
					stephen = new Juggler();
				}
			}
			//try to catch a ball
			else if (choice == 3) {
				try {
					stephen.catchBall();
				}
				catch (Exception e) {
					System.out.println("Stephen dropped everything.");
					System.out.println("Stephen wants to try again...\nHe has " + stephen.getNumUnthrownBalls() + " balls");
					stephen = new Juggler();
				}
			}
			//quit
			else if (choice == 4) {
				//System.out.println("Goodbye.");
				break;
			}
			//exception if something is wrong, such as wrong input
			else {
				//System.out.println("Incorrect Input.");
				throw new RuntimeException();
			}
		
			//the following messages may be useful as well:
			//System.out.println("Stephen dropped everything.");
			//System.out.println("Stephen wants to try again...\nHe has " + stephen.getNumUnthrownBalls() + " balls");
		}
	}
	
	/**
	 * @param in user input
	 * @return number on the menu to perform an action
	 */
	public static int doMenu(Scanner in)
	{
		System.out.println("\nStephen can:");
		System.out.println("1) Throw a ball into the air");
		System.out.println("2) Pass a ball between hands");
		System.out.println("3) Catch a ball from the air");
		System.out.println("4) Quit");
		System.out.print("\nWhat should he do? ");
		int choice = in.nextInt();
		in.nextLine();
		return choice;
	}
}