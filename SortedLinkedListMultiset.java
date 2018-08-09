import java.io.PrintStream;
import java.util.*;

public class SortedLinkedListMultiset<T> extends Multiset<T>
{
	/** Reference to head node. */
    protected Node mHead;

    /** Length of list. */
    protected int mLength;
    

	public SortedLinkedListMultiset() {
        mHead = null; 
        mLength = 0;
		// Implement me!
	} // end of SortedLinkedListMultiset()
	
	
	public void add(T item) {
		Node newNode = new Node(item);
		Node prevNode = null;
		Node currNode;
		
        // If head is empty, then list is empty and head reference need to be initialised.
       	
        	if (mHead == null) {
        	    currNode = newNode;
        	    prevNode= null;
                mHead = currNode;
                mLength++;
               }
  
        	else if(mHead != null) {
            	currNode = mHead;
            	for(int i=0; i < mLength; i++) {
            		if (((Comparable) newNode.getValue()).compareTo(currNode.getValue()) < 0) {
            			prevNode = newNode;
            			mHead = prevNode;
            			currNode = prevNode.getNext();
            			mLength++;
           			}
            			

            		if (((Comparable) newNode.getValue()).compareTo(currNode.getNext().getValue()) <= 0){
            			
            		    currNode.setNext(newNode);
            		    prevNode = currNode;
            		    currNode = newNode.getNext(); 
            		    mLength++;

            		    }
            		    
                    currNode = currNode.getNext();
                    
                    if(currNode == null) {
                    	break;
                    	}
            }

        	}
	}    
		// Implement me! // end of add()
	
	
	public int search(T item) {
		Node currNode = mHead;
		Node prevNode = null;
		Node newNode = new Node(item);
		for(int i = 0; i< mLength; i++) {
		    if(newNode.getValue() == currNode.getValue()) {
			    return (int) newNode.getValue();
		    }
		    else {
		 	    currNode = currNode.getNext();
		 	     }
		}
		
		// Implement me!		
		
		// default return, please override when you implement this method
		return 0;
	} // end of add()
	
	
	public void removeOne(T item) {
    	if (mLength == 0) {
    		System.out.println("No item to remove");;
    	}
        Node currNode = mHead;
        Node prevNode = null;

        // check if value is head node
        if (currNode.getValue() == item) {
            mHead = currNode.getNext();
            mLength--;
            System.out.println(item + " removed");
        }
		
        prevNode = currNode;
        currNode = currNode.getNext();

        while (currNode != null) {
            if (currNode.getValue() == item) {
                prevNode.setNext(currNode.getNext());
                currNode = null;
                mLength--;
                System.out.println(item + " removed");
            }
            prevNode = currNode;
            currNode = currNode.getNext();
        }
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		// Implement me!
	} // end of removeAll()
	
	
	public void print(PrintStream out) {
		// Implement me!
	} // end of print()
	
    private class Node  {
    	   
        /** Stored value of node. */
        protected T mValue;
        /** Reference to next node. */
        protected Node mNext;

        public Node(T value) {
            mValue = value;
            mNext = null;
        }

        public T getValue() {
            return mValue;
        }


        public Node getNext() {
            return mNext;
        }


        public void setValue(T value) {
            mValue = value;
        }


        public void setNext(Node next) {
            mNext = next;
        }
    } 
} // end of class SortedLinkedListMultiset