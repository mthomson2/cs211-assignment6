import java.util.Collection;
import java.util.Queue;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Handles everything happening in the air with the balls.
 * Is effected by the Juggler Class
 * Will add, drop, throw exceptions, etc. 
 * 
 *  For the Queue interface, see http://docs.oracle.com/javase/8/docs/api/java/util/Queue.html
 *  and http://docs.oracle.com/javase/tutorial/collections/interfaces/queue.html
 *  
 *  @author Molly Thomson
 */
public class Air<T> implements Queue<T>
{
 /**
  * Wikipedia claims max solo record is 13
  * see http://en.wikipedia.org/wiki/Juggling_world_records#Solo_records
  * Allowing room for growth...
  */
 public static final int MAX_CAPACITY = 15;

 /**
  * Node in the linked list queue Air
  * Building block for a linked list. Will use getters and setters 
  * to get member variables.
  */
 private class ListItem<T>
 {
  private T node;
  private ListItem<T> next;

  //getters and setters for node and next
  public T getNode() {
   return node;
  }

  public void setNode(T node) {
   this.node = node;
  }

  public ListItem<T> getNext() {
   return next;
  }

  public void setNext(ListItem<T> next) {
   this.next = next;
  }
 }
 
 private ListItem<T> head;
 
 //you code here... maybe you want some other variables?
 private int size;
 //did this in class
 private ListItem<T> tail;

 /**
  * add element to the list
  * @param item the element to add
  */
 public boolean add(T item) throws NullPointerException, IllegalStateException
 {
  //your code here - remember to throw all appropriate exceptions
  //check for exceptions
  if (item == null) {
   throw new NullPointerException();
  }
  if (size >= MAX_CAPACITY) {
   throw new IllegalStateException();
  }
  //create new node instance and increment list
  ListItem<T> node = new ListItem<>();
  node.setNode(item);
  size++;
  //special case for list of length one
  if (size == 1) {
   tail = node;
   head = node;
  } 
  //set the pointer to the node
  else {
   tail.next = node;
   tail = node;
  }
  
  //add always returns true, see: http://docs.oracle.com/javase/8/docs/api/java/util/Collection.html#add-E-
  return true;
 }
 
 /**
  * same as add. Difference: doesn't throw an error if no ball to offer
  * @param item to offer
  */
 public boolean offer(T item) throws NullPointerException
 {
  if (item == null) {
   //System.out.println("Throws exception: " + item);
   throw new NullPointerException();
  }
  //checks to see if we have reached the max
  if (MAX_CAPACITY <= size) {
   //System.out.println("Hit Capacity: " + size);
   return false;
  }
  else {
   //calls add() method
   add(item);
   //System.out.println("In here: " + item);
   return true;
  }
  
 }

 /**
  * Retrieve and removes the head of the queue
  * @return the element that was removed from the list
  * @throws NoSuchElementException if queue is empty
  */
 public T remove() throws NoSuchElementException
 {
  //your code here - remember to throw all appropriate exceptions
  if (size == 0) {
   throw new NoSuchElementException();
  }
  if (size == 1) {
   tail = head;
  }
  //create temporary variable to store value of head
  T tempHead = head.getNode();
  //head becomes next value
  head = head.next;
  size--; 
  //return stored value 
  return tempHead;
 }
 
 /**
  * Retrieves and removes head of the queue
  * Does not throw an exception if queue is empty
  * @return removed head element, null if size is 0
  */
 public T poll()
 {
  if (size == 0) {
   return null;
  }
  //calls remove method. Only difference is exceptions
  T pollElement = remove();
  return pollElement;
 }
 
 /**
  * retrieves but does not remove the head of the queue
  * @return the head of the queue
  * @throws NoSuchElementException when nothing is remaining
  */
 public T element() throws NoSuchElementException
 {
  //your code here - remember to throw all appropriate exceptions
  if (head == null) {
   //System.out.println("Size is 0: " + size);
   throw new NoSuchElementException();
  }
  //System.out.println("Size is: " + size);
  //gets the head of the list
  T element = head.getNode();

  return element;
 }
 
 /**
  * Retrieves but does not remove the head of the queue. 
  * @return head of queue. Null if queue is empty
  */
 public T peek()
 {
  //your code here
  if (head == null) {
   return null;
  }
  else {
   //cals element() method. Difference is exceptions
   T element = element();
   return element;
  }
 }

 /**
  * Displays object in a readable format
  * @return toString for the class
  */
 public String toString()
 {
  String str = "";
  ListItem<T> hold = head;
  if (hold == null) {
    return str;
  }
  else {
    //string will only equal the one item
    if (size == 1) {
      str = hold.getNode().toString();
    }
    //loop through list
    for(int i = 0; hold.getNext() != null; i++) {
      str = hold.getNode().toString() + str;
      //get the next item
      hold = hold.getNext();
    }
    
  }
  //System.out.println(str);
  return str;
 }
 
 /**
  * removes all the elements from the collection
  */
 public void clear()
 {
  //your code here
  //set all of the values to default.
  head = null;
  tail = null;
  size = 0;
 }
 
 /**
  * checks to see whether the collection contains elements
  * @return true if queue is empty; false otherwise
  */
 public boolean isEmpty()
 {
  //your code here
  if (size == 0) {
   return true;
  }
  return false;
 }
 
 /**
  * @return the number of elements in the collection; if size is greater than MAX.VALUE, return MAX.VALUE
  */
 public int size()
 {
  //your code here
  //will return Max value if max value is reached
  if (size > MAX_CAPACITY) {
   //System.out.println("Hit MAX CAP: " + size);
   return MAX_CAPACITY;
  }
  else {
   //System.out.println("Size: " + size);
   return size;
  }
 }
 
 /**
  * @return toArray for the class. Array of elements in the collection. Make queue into an array
  */
 public Object[] toArray()
 {
  //set up new array and check for null
  Object[] array = new Object[size];
  if (head == null) {
    return array;
  }
  //temporary hold for head variable
  ListItem<T> hold = head;
  //loop through the array and set each item to the next item
  for (int i = 0; i < size; i++) {
    array[i] = hold.getNode();
    if (hold.getNext() != null) {
      hold = hold.getNext();
    }
    else {
      break;
    }

  }
  return array;
 }
 
 /*-------------- DO NOT CHANGE ANYTHING BELOW THIS LINE --------------*/
 
 /**
  * DO NOT CHANGE THIS
  * Adds all of the elements in the specified collection to this collection
  * @param c specified collection
  * @return true if the collection changed
  * @throws UnsupportedOperationException throws if not supported
  */
 public boolean addAll(Collection<? extends T> c)  
 {
  throw new UnsupportedOperationException();
 }
 
 /**
  * DO NOT CHANGE THIS
  * @param o object checked to be in list
  * @return true if collection contains specified object
  * @throws UnsupportedOperationException throws if not supported
  */
 public boolean contains(Object o)
 {
  throw new UnsupportedOperationException();
 }
 
 /**
  * DO NOT CHANGE THIS
  * @param c element to be searched for
  * @return true if all the elements are accounted for
  * @throws UnsupportedOperationException throws if not supported
  */
 public boolean containsAll(Collection<?> c)
 {
  throw new UnsupportedOperationException();
 }
 
 /**
  * DO NOT CHANGE THIS
  * @param o object to be compared
  * @return true if objects are equal
  * @throws UnsupportedOperationException throws if not supported
  */
 public boolean equals(Object o)
 {
  throw new UnsupportedOperationException();
 }
 
 /**
  * DO NOT CHANGE THIS
  * @return hashcode value
  * @throws UnsupportedOperationException throws if not supported
  */
 public int hashCode()
 {
  throw new UnsupportedOperationException();
 }
 
 /**
  * DO NOT CHANGE THIS
  * @return an iterator over the elements in the collection
  * @throws UnsupportedOperationException throws if not supported
  */
 public Iterator<T> iterator()
 {
  throw new UnsupportedOperationException();
 }
 
 /**
  * DO NOT CHANGE THIS
  * @param o object to be removed
  * @return true if the object was removed
  * @throws UnsupportedOperationException throws if not supported
  */
 public boolean remove(Object o)
 {
  throw new UnsupportedOperationException();
 }
 
 /**
  * DO NOT CHANGE THIS
  * @param c collection to be removed
  * @return true if all elements of c were removed
  * @throws UnsupportedOperationException throws if not supported
  */
 public boolean removeAll(Collection<?> c)
 {
  throw new UnsupportedOperationException();
 }
 
 /**
  * DO NOT CHANGE THIS
  * @param c collection elements to be retained
  * @return true if all elements were retained in collection
  * @throws UnsupportedOperationException throws if not supported
  */
 public boolean retainAll(Collection<?> c)
 {
  throw new UnsupportedOperationException();
 }
 
 /**
  * DO NOT CHANGE THIS
  * @param a the array to which all of the elements are to be stored
  * @return the array containing all of the elements in the collection
  * @throws UnsupportedOperationException throws if not supported
  */
 public <E> E[] toArray(E[] a)
 {
  throw new UnsupportedOperationException();
 }
}