import java.io.PrintStream;

public class SortedLinkedListMultiset<T> extends Multiset<T>
{
	/** Reference to head node. */
    protected Node<T> mHead;

    /** Length of list. */
    protected int mLength;
    

	public SortedLinkedListMultiset() {
        mHead = null; 
        mLength = 0;
		// Implement me!
	} // end of SortedLinkedListMultiset()
	
	private int compValue(T val1, T val2) {
		return val1.toString().compareTo(val2.toString());
		
	}
	
	public void add(T item) {

		Node<T> newNode = new Node<T>(item);
        // If head is empty, then list is empty and head reference need to be initialised.
   	
    	if (mHead == null) {
            mHead = newNode;
            mLength++;
            return;
        }

		Node<T> prevNode = null;
		Node<T> currNode = mHead;
    	while(currNode != null) {
    		if(compValue(newNode.getValue(), currNode.getValue())>0) {
    			prevNode = currNode;
    			currNode = currNode.getNext();
    		}
    		break;	
    	}
    	prevNode.setNext(newNode);
    	newNode.setNext(currNode);
        
	}    
		// Implement me! // end of add()
	
	
	public int search(T item) {
		if (mHead == null) {
			return 0;
        }

		Node<T> currNode = mHead;
		int count = 0;
    	while(currNode != null) {
    		if(compValue(item, currNode.getValue())==0) {
    			count++;
    		}
    		if(compValue(item, currNode.getValue())<0) {
        		break;	
    		}
			currNode = currNode.getNext();
    	}
    	return count;
	} // end of add()
	
	
	private void remove(T item, boolean all) {
		if (mHead == null) {
			return;
        }
		Node<T> currNode = mHead;
		Node <T> prevNode = null;
		
		
		while(currNode != null) {
    		if(compValue(item, currNode.getValue())>0) {
    			prevNode = currNode;
    			currNode = currNode.getNext();
    			
    		} else if (compValue(item, currNode.getValue())==0) {
    			if (prevNode == null) {
    				mHead = currNode.getNext();
    				currNode.setNext(null);
    				currNode = mHead;    				
    			}
    			else {
        			prevNode.setNext(currNode.getNext());
        			currNode.setNext(null);
        			currNode = currNode.getNext();
        			mLength--;  
        			if(!all) break;
    				
    			}

    			
    		} else {
    			
    			break;
    		}
		}
			
	}

	
	public void removeOne(T item) {
		remove(item, false);
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		// Implement me!
		remove(item, true);
	} // end of removeAll()
	
	
	public void print(PrintStream out) {

	    Node<T> current = mHead;

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

 // end of print()
	
    private class Node<K>  {
    	   
        /** Stored value of node. */
        protected K mValue;
        /** Reference to next node. */
        protected Node<K> mNext;

        public Node(K value) {
            mValue = value;
            mNext = null;
        }

		public K getValue() {
            return mValue;
        }

        public Node<K> getNext() {
            return mNext;
        }

        public void setNext(Node<K> next) {
            mNext = next;
        }
    } 
} // end of class SortedLinkedListMultiset