import java.io.PrintStream;

import LinkedListMultiset.Node;

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
        Node newNode = new Node(item);

        // If head is empty, then list is empty and head reference need to be initialised.
        if (mHead == null) {
            mHead = newNode;
            mLength++;
            return;
        }
        
        Node currNode = mHead;
        Node prevNode = null;
        while(currNode!=null) {
        	prevNode = currNode;
        	currNode = currNode.getNext();
        }

        prevNode.setNext(newNode);
        mLength++;
        prevNode = null;

    }// end of add()


	public int search(T item) {
		if(mHead==null) {
			return 0;
		}
        int count = 0;
        Node currNode = mHead;
        while(currNode!=null) {
        	int compare = item.toString().compareTo(currNode.getValue().toString());
        	if(compare==0) {
        		count++;
        	}
        	currNode = currNode.getNext();
        }
        return count;

	} // end of search()

	public void removeOne(T item) {

		if(mHead==null) {
			return;
		}
		
		Node currNode = mHead;
		Node prevNode = null;
		
		while (currNode != null) {
			int compare = item.toString().compareTo(currNode.getValue().toString());
            if (compare==0) {
            	if (prevNode == null) {
    				mHead = currNode.getNext();
    				currNode.setNext(null);
    			} else {
	                prevNode.setNext(currNode.getNext());
	                currNode.setNext(null);
    			}
                mLength--;
                break;
            }else {
				prevNode = currNode;
				currNode = currNode.getNext();
            }
        }
	}

	public void removeAll(T item) {
		if(mHead==null) {
			return;
		}
		
		Node currNode = mHead;
		Node prevNode = null;
		
		while (currNode != null) {
			int compare = item.toString().compareTo(currNode.getValue().toString());
            if (compare==0) {
            	if (prevNode == null) {
    				mHead = currNode.getNext();
    				currNode.setNext(null);
    				currNode = mHead;    				
    			} else {
	                prevNode.setNext(currNode.getNext());
	                currNode.setNext(null);
	                currNode = prevNode.getNext();
    			}
                mLength--;
            }else {
				prevNode = currNode;
				currNode = currNode.getNext();
            }
        }
	}

	public void print(PrintStream out) {
		if(mHead==null) {
			return;
		}
	    
	    String[] items = new String[mLength];
	    int[] counters = new int[mLength];
	    for(int i=0;i<counters.length;i++) {
	    	counters[i] = 0;
	    }
	    Node current = mHead;
	    while(current!=null) {
	    	countItem(current.getValue(), items, counters);
	    	current = current.getNext();
	    }

	    for(int i=0;i<items.length;i++) {
	    	String item = items[i];
	    	if(item==null || item.equals("")) {
	    		continue;
	    	}
	    	out.println(item + " | "+counters[i]);
	    }
    }

    private void countItem(T value, String[] items, int[] counters) {
		for(int i=0;i<items.length;i++) {
			String item = items[i];
			if(null==item) {
				items[i] = value.toString();
				counters[i]++;
				break;
			}
			if(value.toString().equals(items[i])) {
				counters[i]++;
				break;
			}
		}
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

        public void setNext(Node next) {
            mNext = next;
        }
    } // end of inner class Node

} // end of class LinkedListMultiset
