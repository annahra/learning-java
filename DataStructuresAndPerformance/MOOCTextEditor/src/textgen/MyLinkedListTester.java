/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		//try adding null element, expect npe
		try {
			shortList.add(null);
			fail("Did not catch null pointer");
		}
		catch (NullPointerException e){
			
		}
		//test that next pointer of last node throws NPE
		assertEquals("Check endNode.next is null",null,shortList.tail.next);
		//check that tail is pointing to the last element
		assertEquals("Check tail is pointing to element holding b","B",shortList.tail.data);
		//test if node at size-1 index gives the node we just added
		assertEquals("Check index size - 1","B",shortList.get(shortList.size-1));
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		//check if adding null element
		try {
			shortList.add(null);
			fail("Did not catch null pointer");
		}
		catch (NullPointerException e){
			
		}
		//SPECIAL CASE: at to front of list
		//add something to the front
		//shortList.add(0,"Z");
		//check that prev pointer of first node is null
//		assertEquals("Check firstNode.prev is null",null,shortList.head.prev);
//		//check if head points to front node
//		assertEquals("Check if head points to front node","Z",shortList.head.data);
//		//check if index 0 gives that node
//		assertEquals("Check index 0","Z",shortList.get(0));
		
		//GENERAL CASE: add to somewhere in between, add to index 5 of longerList
		//longerList.add(5,182);
//		LLNode<Integer> holder = longerList.head;
//		LLNode<Integer> addedNode = longerList.head;
//		LLNode<Integer> prevNode = longerList.head;
//		LLNode<Integer> nextNode = longerList.head;
//		for(int k=0;k<7;k++) {
//			if(k==4) {prevNode = holder;}
//			else if(k==5) {addedNode = holder;}
//			else if(k==6) {nextNode = holder;}
//			holder = holder.next;
//		}
//		assertEquals("Check addedNode.next is nextNode",(Integer)5, addedNode.next.data);
//		assertEquals("Check addedNode.prev is prevNode",(Integer)4, addedNode.prev.data);
//		assertEquals("Check nextNode.prev is addedNode",(Integer)182, nextNode.prev.data);
//		assertEquals("Check prevNode.next is addedNode",(Integer)182, prevNode.next.data);
//		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
	    
	}
	
	
	// TODO: Optionally add more test methods.
	
}
