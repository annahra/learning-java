package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if(element == null) {throw new NullPointerException("Cannot add null to List");}
		LLNode<E> endNode = new LLNode<E>(element);
		if(this.size == 0) {
			head = endNode;
			tail = endNode;
		}
		else {
			LLNode<E> temp = tail;
			tail.next = endNode;
			endNode.prev = temp;
			tail = endNode;
		}
		this.size +=1;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if(index >= size || index<0) {throw new IndexOutOfBoundsException("Index "+
							index+" is out of bounds");}
		
		return getNode(index).data;
	}
	
	public LLNode<E> getNode(int index){
		if(index >= size || index<0) {throw new IndexOutOfBoundsException("Index "+
				index+" is out of bounds");}
		LLNode<E> holder = head;
		for(int k=0;k<size;k++) {
			if(k!=index) {
				holder = holder.next;
			}
			else {break;}
		}
		return holder;
	}
	
	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if(element == null) {throw new NullPointerException("Cannot add null to List");}
		if((index >= size && index !=0) || index<0) {throw new IndexOutOfBoundsException("Index "+
				index+" is out of bounds");}
		//evaluate for special case where inserting at beginning
		if(index == 0 && size !=0) {
			LLNode<E> newNode = new LLNode<E>(element);
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
			size+=1;
		}
		else if(index == 0 && size == 0) {
			add(element);
		}
		//evaluate for special case where inserting at end
		else if(index == size-1) {
			add(element);
			size+=1;
		}
		else {
			LLNode<E> newNode = new LLNode<E>(element);
			LLNode<E> oldNode = getNode(index);
			newNode.prev = oldNode.prev;
			newNode.next = oldNode;
			oldNode.prev.next = newNode;
			oldNode.prev = newNode;
			size+=1;
		}
	}

	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if(index >= size || index<0) {throw new IndexOutOfBoundsException("Index "+
				index+" is out of bounds");}
		LLNode<E> toBeDeleted = getNode(index);
		if(index == 0 && size != 1) {
			head = toBeDeleted.next;
			toBeDeleted.next.prev = null;
			toBeDeleted.next = null;
			size = size - 1;
		}
		else if (index == 0 && size ==1) {
			head = null;
			tail = null;
			size = size - 1;
		}
		else if(index == (size-1)) {
			tail = toBeDeleted.prev;
			toBeDeleted.prev.next = null;
			toBeDeleted.prev = null;
			size = size - 1;
		}
		else {
			toBeDeleted.next.prev = toBeDeleted.prev;
			toBeDeleted.prev.next = toBeDeleted.next;
			toBeDeleted.next = null;
			toBeDeleted.prev = null;
			size = size - 1;
		}
		
		
		return toBeDeleted.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if(element == null) {throw new NullPointerException("Cannot add null to List");}
		if(index >= size || index<0) {throw new IndexOutOfBoundsException("Index "+
				index+" is out of bounds");}
		LLNode<E> newEl = getNode(index);
		E oldValue = newEl.data;
		newEl.data = element;
		
		return oldValue;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	

}
