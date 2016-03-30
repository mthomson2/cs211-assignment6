import org.junit.*; //junit
import static org.junit.Assert.*; //assert functions
import java.util.*;
import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

public class Assignment6SampleTests
{
	private static class JugglingPin
	{
		public static int uniqueId = 0;
		
		public int pinId;
		
		public JugglingPin()
		{
			this.pinId = uniqueId;
			uniqueId++;
		}
		
		public String toString()
		{
			return ""+pinId;
		}
	};
	
	private Air<JugglingPin> a;
	
	@Before
	public void MakeAir()
	{
		a = new Air<>();
		JugglingPin.uniqueId = 0;
	}
	
	/**
	 * Check the ball class
	 */
	@Test(timeout=2000)
	public void A6Ball()
	{
		Ball b = new Ball(1);
		assertEquals(1,b.getNumber());
		String bString = b.toString();
		assertTrue(bString != null && bString.replaceAll("\\s","").equals("(1)"));
	}
	
	/**
	 * Check the juggler class - can't pass balls when none in the hand
	 */
	@Test(expected=RuntimeException.class,timeout=2000)
	public void A6Juggler1()
	{
		Juggler j = new Juggler();
		j.passBall();
		fail("Juggler shouldn't be able to pass imaginary balls");
	}
	
	/**
	 * Check the juggler class - can't throw more balls than numUnthrownBalls (which is between 3 and 7)
	 */
	@Test(expected=RuntimeException.class,timeout=2000)
	public void A6Juggler2()
	{
		Juggler j = new Juggler();
		for(int i = 0; i < 10; i++)
			j.throwBall();
		fail("Juggler shouldn't be able to throw more balls than are on the ground");
	}
	
	/**
	 * Check the juggler class - can't catch ball when none is in the air
	 */
	@Test(expected=RuntimeException.class,timeout=2000)
	public void A6Juggler3()
	{
		Juggler j = new Juggler();
		j.catchBall();
		fail("Juggler shouldn't be able to catch ball when none are in the air");
	}
	
	/**
	 * Check the juggler class - check that juggler throws from his hand
	 */
	@Test(timeout=2000)
	public void A6Juggler4()
	{
		Juggler j = new Juggler();
		int startNumBalls = j.getNumUnthrownBalls();
		
		j.throwBall(); //throw ball into the air
		j.catchBall(); //catch with other hand
		j.passBall(); //pass to other hand
		j.throwBall(); //throw back into the air
		
		assertEquals(startNumBalls-1, j.getNumUnthrownBalls()); //make sure only one ball was thrown
	}
	
	/**
	 * Check the air class - check size()
	 */
	@Test(timeout=2000)
	public void A6AirSize()
	{
		assertEquals(0, a.size());
		for(int i = 1; i < Air.MAX_CAPACITY+1; i++)
		{
			a.add(new JugglingPin());
			assertEquals(i, a.size());
		}
		for(int i = Air.MAX_CAPACITY; i > 0; i--)
		{
			assertEquals(i, a.size());
			a.remove();
		}
		assertEquals(0, a.size());
	}
	
	/**
	 * Check the air class - check add/remove adds & removes same element
	 */
	@Test(timeout=2000)
	public void A6AirAddRemove1()
	{
		JugglingPin pin = new JugglingPin();
		a.add(pin);
		JugglingPin pin2 = a.remove();
		assertEquals(pin.pinId, pin2.pinId);
	}
	
	/**
	 * Check the air class - check add/remove adds & removes as a queue
	 */
	@Test(timeout=2000)
	public void A6AirAddRemove2()
	{
		JugglingPin pinAdd1 = new JugglingPin();
		a.add(pinAdd1);
		
		JugglingPin pinAdd2 = new JugglingPin();
		a.add(pinAdd2);
		
		JugglingPin pinRemove1 = a.remove();
		assertEquals(pinAdd1.pinId, pinRemove1.pinId);
		
		JugglingPin pinAdd3 = new JugglingPin();
		a.add(pinAdd3);
		
		JugglingPin pinRemove2 = a.remove();
		assertEquals(pinAdd2.pinId, pinRemove2.pinId);
		
		JugglingPin pinRemove3 = a.remove();
		assertEquals(pinAdd3.pinId, pinRemove3.pinId);
	}
	
	/**
	 * Check the air class - check NoSuchElementException for remove on empty air
	 */
	@Test(expected=NoSuchElementException.class,timeout=2000)
	public void A6AirRemove1()
	{
		a.remove();
		fail("Air remove() should throw NoSuchElementException when nothing remaining.");
	}
	
	/**
	 * Check the air class - check NoSuchElementException for remove after add & remove twice
	 */
	@Test(expected=NoSuchElementException.class,timeout=2000)
	public void A6AirRemove2()
	{
		a.add(new JugglingPin());
		a.remove();
		a.remove();
		fail("Air remove() should throw NoSuchElementException when nothing remaining.");
	}
	
	/**
	 * Check the air class - check null return for poll on empty air
	 */
	@Test(timeout=2000)
	public void A6AirPoll1()
	{
		assertTrue(a.poll() == null);
	}
	
	/**
	 * Check the air class - check return for poll after add & poll twice
	 */
	@Test(timeout=2000)
	public void A6AirPoll2()
	{
		a.add(new JugglingPin());
		assertTrue(a.poll() != null);
		assertTrue(a.poll() == null);
	}
	
	/**
	 * Check the air class - check IllegalStateException for add after capacity reached
	 */
	@Test(expected=IllegalStateException.class,timeout=2000)
	public void A6AirAdd1()
	{
		for(int i = 0; i < Air.MAX_CAPACITY+1; i++)
			a.add(new JugglingPin());
		fail("Air add() should throw IllegalStateException when adding above capacity");
	}
	
	/**
	 * Check the air class - check NullPointerException for adding null
	 */
	@Test(expected=NullPointerException.class,timeout=2000)
	public void A6AirAdd2()
	{
		a.add(null);
		fail("Air add() should throw NullPointerException when adding null items");
	}
	
	/**
	 * Check the air class - check return offer
	 */
	@Test(timeout=2000)
	public void A6AirOffer1()
	{
		for(int i = 0; i < Air.MAX_CAPACITY; i++)
			assertTrue(a.offer(new JugglingPin()));
		assertTrue(!a.offer(new JugglingPin()));
	}
	
	/**
	 * Check the air class - check NullPointerException for offering null
	 */
	@Test(expected=NullPointerException.class,timeout=2000)
	public void A6AirOffer2()
	{
		a.offer(null);
		fail("Air offer() should throw NullPointerException when adding null items");
	}
	
	/**
	 * Check the air class - check element shows head
	 */
	@Test(timeout=2000)
	public void A6AirElement1()
	{
		JugglingPin pinAdd1 = new JugglingPin();
		a.add(pinAdd1);
		
		JugglingPin pinAdd2 = new JugglingPin();
		a.add(pinAdd2);
		
		JugglingPin pinRemove1 = a.element();
		assertEquals(pinAdd1.pinId, pinRemove1.pinId);
		pinRemove1 = a.element();
		assertEquals(pinAdd1.pinId, pinRemove1.pinId);
		a.remove();
		
		JugglingPin pinAdd3 = new JugglingPin();
		a.add(pinAdd3);
		
		JugglingPin pinRemove2 = a.element();
		assertEquals(pinAdd2.pinId, pinRemove2.pinId);
		pinRemove2 = a.element();
		assertEquals(pinAdd2.pinId, pinRemove2.pinId);
		a.remove();
		
		JugglingPin pinRemove3 = a.element();
		assertEquals(pinAdd3.pinId, pinRemove3.pinId);
		pinRemove3 = a.element();
		assertEquals(pinAdd3.pinId, pinRemove3.pinId);
		a.remove();
	}
	
	/**
	 * Check the air class - check NoSuchElementException for element on empty
	 */
	@Test(expected=NoSuchElementException.class,timeout=2000)
	public void A6AirElement2()
	{
		a.element();
		fail("Air element() should throw NoSuchElementException when nothing remaining.");
	}
	
	/**
	 * Check the air class - check NoSuchElementException for element on empty after add and remove
	 */
	@Test(expected=NoSuchElementException.class,timeout=2000)
	public void A6AirElement3()
	{
		a.add(new JugglingPin());
		a.remove();
		a.element();
		fail("Air element() should throw NoSuchElementException when nothing remaining.");
	}
	
	/**
	 * Check the air class - check peek shows head
	 */
	@Test(timeout=2000)
	public void A6AirPeek1()
	{
		JugglingPin pinAdd1 = new JugglingPin();
		a.add(pinAdd1);
		
		JugglingPin pinAdd2 = new JugglingPin();
		a.add(pinAdd2);
		
		JugglingPin pinRemove1 = a.peek();
		assertEquals(pinAdd1.pinId, pinRemove1.pinId);
		pinRemove1 = a.peek();
		assertEquals(pinAdd1.pinId, pinRemove1.pinId);
		a.remove();
		
		JugglingPin pinAdd3 = new JugglingPin();
		a.add(pinAdd3);
		
		JugglingPin pinRemove2 = a.peek();
		assertEquals(pinAdd2.pinId, pinRemove2.pinId);
		pinRemove2 = a.peek();
		assertEquals(pinAdd2.pinId, pinRemove2.pinId);
		a.remove();
		
		JugglingPin pinRemove3 = a.peek();
		assertEquals(pinAdd3.pinId, pinRemove3.pinId);
		pinRemove3 = a.peek();
		assertEquals(pinAdd3.pinId, pinRemove3.pinId);
		a.remove();
	}
	
	/**
	 * Check the air class - check return value for peek on empty
	 */
	@Test(timeout=2000)
	public void A6AirPeek2()
	{
		assertTrue(a.peek() == null);
	}
	
	/**
	 * Check the air class - check return for peek on empty after add and remove
	 */
	@Test(timeout=2000)
	public void A6AirPeek3()
	{
		a.add(new JugglingPin());
		a.remove();
		assertTrue(a.peek() == null);
	}
	
	/**
	 * Check the air class - check toString() method
	 */
	@Test(timeout=2000)
	public void A6AirToString()
	{
		JugglingPin pin = new JugglingPin();
		a.add(pin);
		assertEquals(pin.toString(), a.toString());
		
		JugglingPin pin2 = new JugglingPin();
		a.add(pin2);
		assertEquals(pin2.toString()+pin.toString(), a.toString());
		
		a.remove();
		assertEquals(pin2.toString(), a.toString());
	}
	
	/**
	 * Check the air class - check clear method
	 */
	@Test(expected=NoSuchElementException.class,timeout=2000)
	public void A6AirClear()
	{
		assertEquals(0, a.size());
		a.add(new JugglingPin());
		a.add(new JugglingPin());
		assertEquals(2, a.size());
		a.clear();
		assertEquals(0, a.size()); //make sure size is 0
		a.remove(); //make sure NoSuchElementException is thrown
	}
	
	/**
	 * Check the air class - check isEmpty method
	 */
	@Test(timeout=2000)
	public void A6AirIsEmpty()
	{
		assertTrue(a.isEmpty());
		a.add(new JugglingPin());
		a.remove();
		assertTrue(a.isEmpty());
	}
	
	/**
	 * Check the air class - check toArray method
	 */
	@Test(timeout=2000)
	public void A6AirToArray()
	{
		Object[] o = a.toArray();
		assertEquals(0, o.length);
		
		JugglingPin pin = new JugglingPin();
		a.add(pin);
		o = a.toArray();
		assertEquals(1, o.length);
		assertEquals(((JugglingPin)o[0]).pinId, pin.pinId);
		
		JugglingPin pin2 = new JugglingPin();
		a.add(pin2);
		o = a.toArray();
		assertEquals(2, o.length);
		//didn't specify an order, so check either
		if(((JugglingPin)o[0]).pinId == pin.pinId)
		{
			assertEquals(((JugglingPin)o[0]).pinId, pin.pinId);
			assertEquals(((JugglingPin)o[1]).pinId, pin2.pinId);
		}
		else
		{
			assertEquals(((JugglingPin)o[1]).pinId, pin.pinId);
			assertEquals(((JugglingPin)o[0]).pinId, pin2.pinId);
		}
		
		a.remove();
		o = a.toArray();
		assertEquals(1, o.length);
		assertEquals(((JugglingPin)o[0]).pinId, pin2.pinId);
	}

	public static void main(String args[])
	{
		org.junit.runner.JUnitCore.main("Assignment6SampleTests");
	}
}
