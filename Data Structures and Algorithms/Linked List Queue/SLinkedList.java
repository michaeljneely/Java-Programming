package llistqueue;

/** M Madden, M Schukat, 2005 - 2015 */
public class SLinkedList 
{
  protected Node head;	 // head node of the list
  protected Node tail;	 // tail node of the list
  protected Node curr;	 // node referencing current position in the list  
  protected long size;	 // number of nodes in the list
  
  /** Default constructor that creates an empty list. */
  public SLinkedList() {
    tail = curr = head = null;
    size = 0;
  }

  public long size() { 
	  return size;
  }
  
  public boolean isEmpty() { 
	  return (head == null);
  }

  public Object getCurr() {
	  if (curr == null)   // Verify that there is a current node
		  return null;
	  return curr.getElement();	
  }
  
  public boolean gotoHead() {
	  if (isEmpty()) 
		  return false;
	  curr = head;
	  return true;
  }
  
  //**********************
  //NEW -- Go To Tail Node
  public boolean gotoTail(){
	  if(isEmpty()) return false;
	  curr = tail;
	  return true;
  }
  //**********************

  public boolean gotoNext() {
	  if (curr == null) return false; // should only arise when list is empty
	  if (curr.getNext() == null) return false;  // no next node: at end
	  curr = curr.getNext();
	  return true;
  }

  public void insertNext(Object el) {
	  if (head == null) { 
         insertHead(el);  // If haven't inserted a head, do so now (for convenience)
   	     return;
	  }
	  
	  Node newnode = new Node(el, curr.getNext());  // create new node with its next node equal to curr's next node	  
	  curr.setNext(newnode); // update the next node of the current node to point to the new one
	  size++;

	  // Modification for tail 
      if (tail == curr) tail = newnode;

	  // make this new node the current one
	  curr = newnode;	  
  }
  
  public void deleteNext() {
	  if (curr == null || curr.getNext() == null) return; // no next: list empty or already at end

	  curr.setNext(curr.getNext().getNext());  // set curr's next equal to the next node's next
	  // Note: Garbage collector will automatically clear up the node no longer referenced

	  if (curr.getNext() == null) tail = curr; // We just removed the tail, so update with new tail
	  
	  size--;
  }

  public void insertHead(Object el) {
	  Node oldhead = head;
	  head = new Node(el, oldhead);
	  size++;	  
	  curr = head;
	  if (tail == null) tail = curr; // if list was empty, update tail
}
  
  public void deleteHead() {
	  if (head == null) return; // list already empty
	  
	  head = head.getNext(); // New head is next node
	  size--;
	  curr = head;
	  
	  if (size == 1) tail = curr;
  }
}
