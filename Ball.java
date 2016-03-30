/**
 * The basis for the Ball being juggled.
 * Gets the numer of the ball that is in the air
 *  
 *  @author Molly Thomson
 */
public class Ball
{
	private int number;
	
	/**
	 * Constructor
	 * @param number number of ball 
	 */
	public Ball(int number)
	{
		this.number = number;
	}
	
	/**
	 * @return String toString the number in brackets
	 */
	public String toString()
	{
		return "("+this.number+")";
	}
	
	/**
	 * @return the number of the ball in the air
	 */
	public int getNumber()
	{
		return number;
	}
}