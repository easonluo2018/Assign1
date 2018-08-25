import java.io.PrintStream;

import SortedLinkedListMultiset.Node;

public class SortedLinkedListMultiset<T> extends Multiset<T>
{
	/** Reference to head node. */
    protected Node mHead;

    /** Length of list. */
    protected int mLength;
    

	public SortedLinkedListMultiset() {
        mHead = null; 
        mLength = 0;
	} // end of SortedLinkedListMultiset()
	
	private int compValue(T val1, T val2) {
		return val1.toString().compareTo(val2.toString());
		
	}
	
	public void add(T item) {

		Node newNode = new Node(item);
		
    	if (mHead == null) {
            mHead = newNode;
            mLength++;
            return;
        }
		Node prevNode = null;
		Node currNode = mHead;
    	while(currNode != null) {
    		int compare = compValue(newNode.getValue(), currNode.getValue());
    		if(compare>0) {
    			prevNode = currNode;
    			currNode = currNode.getNext();
    		}else {
    			break;
    		}
    	}
    	
		if(prevNode==null) {
			newNode.setNext(mHead);
			mHead = newNode;
		} else {
			prevNode.setNext(newNode);
	    	newNode.setNext(currNode);
		} 
		mLength++;
	}
	
	
	public int search(T item) {
		if (mHead == null) {
			return 0;
        }

		Node currNode = mHead;
		int count = 0;
    	while(currNode != null) {
    		int compare = compValue(item, currNode.getValue());
    		if(compare==0) {
    			count++;
    		}else if (compare<0){
    			break;
    		}
			currNode = currNode.getNext();
    	}
    	return count;
	} // end of add()
	
	public void removeOne(T item) {

		if (mHead == null) {
			return;
        }
		Node prevNode = null;
		Node currNode = mHead;
		
		while(currNode != null) {
			int compare = compValue(item, currNode.getValue());
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
            }else{
    			prevNode = currNode;
    			currNode = currNode.getNext();
        	}
		}
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		// Implement me!
		if (mHead == null) {
			return;
        }
		Node currNode = mHead;
		Node prevNode = null;
		
		while(currNode != null) {
			int compare = compValue(item, currNode.getValue());
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
            } else if (compare<0) {
            	break;
        	} else {
				prevNode = currNode;
				currNode = currNode.getNext();
        	}
		}
	} // end of removeAll()

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

 // end of print()
	
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

        public void setNext(Node next) {
            mNext = next;
        }
    } 
} // end of class SortedLinkedListMultiset