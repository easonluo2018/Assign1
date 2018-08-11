import java.io.PrintStream;
import java.util.*;

public class LinkedListMultiset<T> extends Multiset<T>
{
	/** Reference to head node. */
    protected Node mHead;

    /** Length of list. */
    protected int mLength;

	public LinkedListMultiset() {
        mHead = null;
        mLength = 0;
		// Implement me!
	} // end of LinkedListMultiset()


	public void add(T item) {
        long startTime = System.nanoTime();
        Node newNode = new Node(item);

        // If head is empty, then list is empty and head reference need to be initialised.
        if (mHead == null) {
            mHead = newNode;
        }

        // otherwise, add node to the head of list.
        else {
            newNode.setNext(mHead);
            mHead = newNode;

        }

        mLength++;

        long endTime = System.nanoTime();
        System.out.println("time taken = " + ((double)(endTime - startTime)) / Math.pow(10, 9) + " sec");
    }
 // end of add()


	public int search(T item) {
        int count = 0;
        Node currNode = mHead;
        for (int i = 0; i < mLength; ++i) {
            if (currNode.getValue() == item) {
                count++;
            }
            currNode = currNode.getNext();
        }
        return count;

	} // end of search()


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
		// Implement me!
	} // end of removeOne()


	public void removeAll(T item) {
        if (mLength == 0) {
            System.out.println("No item to remove");;
        }
        Node currNode = mHead;
        Node prevNode = null;
        int count = 0;

        while(currNode != null) {
            if (currNode.getValue() == item) {
                count++;
                mLength--;

                if (prevNode == null) {
                    mHead = currNode.getNext();
                } else {
                    prevNode.setNext(currNode.getNext());
                }

                if (currNode.getNext() != mHead) {
                    prevNode.setValue(currNode.getValue());
                }

                currNode = currNode.getNext();


            }
            System.out.println("Total " + item + " removed: " + count);
        }
	}

	public void print(PrintStream out) {

	    Node current = mHead;

	    String[] items = new String[mLength];
	    int[] counters = new int[mLength];
	    for(int i=0;i<counters.length;i++) {
	        counters[i] =0;
        }
	    while(current != null) {

            countItem(current.getValue(), items, counters);

	        current = current.getNext();
        }

        for(int i=0;i<items.length;i++) {
	        if(items[i]==null || items[i].equals("")) {
	            out.println(items[i]+" | "+counters[i]);
            }
        }

    }

    private void countItem(T value, String[] items, int[] counters) {
	    int nextSpot = 0;
	    for(int i=0;i<items.length; i++) {
	        String itemVal = items[i];
	        if(itemVal.equals(value.toString())) {
	            counters[i]++;
	            return;
            }else{
                nextSpot++;
            }
        }
        items[nextSpot] = value.toString();
	    counters[nextSpot]++;
    }

    /**
     * Node type, inner private class.
     */

       private class Node    {

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
    } // end of inner class Node

} // end of class LinkedListMultiset
