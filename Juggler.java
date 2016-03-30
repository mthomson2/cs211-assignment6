import java.util.Random;

/**
 *  Juggler class performs all the neccessary actions to deal with balls. 
 *  The Juggler will catch and throw balls. 
 *  
 *  @author Molly Thomson
 */
public class Juggler
{
 /**
  * Models a jugglers hands. The hands can only hold one 
  * ball at a time. 
  */
 private class Hand
 {
  private Ball ball = null;
  
  /**
   * catches a ball
   * @param ball
   * @throws RuntimeException if there is no ball to catch
   */
  public void catchBall(Ball ball)
  {
   this.ball = ball;
   if (this.ball == null) {
    throw new RuntimeException();
   }
  }
  
  /**
   * @throws RunTimeException if there are no balls to throw
   * @return ball object being thrown
   */
  public Ball throwBall()
  {
   Ball temp = this.ball;
   this.ball = null;
   if (temp == null) {
    throw new RuntimeException();
   }
   return temp;
  }
  
  /**
   * checks to see if there the juggler has a ball
   * @return boolean true if juggler has no balls in hands
   */
  public boolean hasBall()
  {
   if (ball != null) {
    return true;
   }
   return false;
  }
  
  /**
   * @return toString returns the ball number or nothing
   */
  public String toString()
  {
   if(this.ball == null)
    return "   ";
   return this.ball.toString();
  }
 }
 
 /*-------------- DO NOT CHANGE ANYTHING BELOW THIS LINE --------------*/
 
 private Air<Ball> air = new Air<>();
 private Hand[] hands = new Hand[2];
 private int numUnthrownBalls;
 private int totalBalls;
 
 /**
  * Constructor
  * creates hands and random numbers for numUnthrownBalls and totalBalls
  */
 public Juggler()
 {
  hands[0] = new Hand();
  hands[1] = new Hand();
  this.totalBalls = this.numUnthrownBalls = ((new Random()).nextInt(5))+3;
 }
 
 /**
  * Will pass the ball from one hand to the other
  */
 public void passBall()
 {
  hands[0].catchBall(hands[1].throwBall());
 }
 
 /**
  * if there are unthrown balls, the juggler will throw a ball
  * in the air. The air will add the ball and the number of 
  * unthrown balls will decrease
  */
 public void throwBall()
 {
  if(!hands[0].hasBall() && this.numUnthrownBalls > 0)
   air.add(new Ball(this.numUnthrownBalls--));
  else
   air.add(hands[0].throwBall());
 }
 
 /**
  * removes the ball from the air and puts the ball in the one hand
  */
 public void catchBall()
 {
  hands[1].catchBall(air.remove());
 }
 
 /**
  * @return the number of unthrown balls
  */
 public int getNumUnthrownBalls()
 {
  return this.numUnthrownBalls;
 }
 
 /**
  * @return the man figure with the unthrown balls on the ground
  */
 public String toString()
 {
  String spacing = "";
  if(this.totalBalls < 7) spacing += "   ";
  if(this.totalBalls < 5) spacing += "   ";
  
  return spacing + air.toString() + "\n\n      "
   + hands[0].toString() + "( )" + hands[1].toString() + "\n   "
   + "    \\__|__/\n   "
   + ((this.numUnthrownBalls > 6) ? "(7)" : "   ")
   + ((this.numUnthrownBalls > 5) ? "(6)" : "   ")
   + " |\n   "
   + ((this.numUnthrownBalls > 4) ? "(5)" : "   ")
   + ((this.numUnthrownBalls > 3) ? "(4)" : "   ")
   + " |\n   "
   + ((this.numUnthrownBalls > 2) ? "(3)" : "   ")
   + ((this.numUnthrownBalls > 1) ? "(2)" : "   ")
   + "/ \\\n   " 
   + ((this.numUnthrownBalls > 0) ? "(1)" : "   ")
   + "  /   \\\n";
 }
}