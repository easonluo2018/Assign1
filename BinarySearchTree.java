import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BinarySearchTree<T> {

    private BstNode<T> root;
    
    private int size;
    
    public static final int PREORDER = -1;
    public static final int INORDER = 0;
    public static final int POSTORDER = 1;

    public void insert(T value) {
        root = insert(root, value);
    }

    /**
     * delete item and return the node deleted
     * @param value
     * @param all
     * @return
     */
    public BstNode<T> delete(T value, boolean all) {
        root = delete(root, value, all);
        return root;
    }

    /**
     * delete a item from sub tree, return the deleted node, if it's the root then restructure
     * @param root
     * @param item
     * @param all
     * @return
     */
    private BstNode<T> delete(BstNode<T> root, T item, boolean all) {

        if(root==null) return null;
        
        int result = compare(item, root.getValue());
        if(result<0) root.setLeft(delete(root.getLeft(), item, all));
        else if (result>0) root.setRight(delete(root.getRight(), item, all));
        else {
        	if(!all && root.getCounter()>1) {
    			root.decrease();
    			return root;
    		}

        	if(root.getLeft()==null && root.getRight() == null) {
        		root = null;
        	} else if(root.getLeft() == null) {
    			root = root.getRight();
        	} else if(root.getRight() == null) {
        		root = root.getLeft();
        	} else {
        		BstNode<T> minRight = findMin(root.getRight());
        		root.setRight(delete(root.getRight(), minRight.getValue(), true));
        		root.setValue(minRight.getValue());
        		root.setCounter(minRight.getCounter());
        	}
        }
        return root;
    }
    
	private BstNode<T> findMin(BstNode<T> root) {
    	BstNode<T> min = root;
    	while(min.getLeft() != null) {
    		min = min.getLeft();
    	}
		return min;
	}

	private BstNode<T> insert(BstNode<T> root, T value) {

        if(null==root) {
        	size++;
        	return new BstNode<T>(value);
        }

        int result = compare(value, root.getValue());
        if(result==0) {
            root.increase();
        }else if(result<0) {
            root.setLeft(insert(root.getLeft(), value));
        }else {
            root.setRight(insert(root.getRight(), value));
        }

        return root;
    }

    public BstNode<T> search(T value) {
        return search(root, value);
    }

    private BstNode<T> search(BstNode<T> root, T value) {

        if(null==root) return null;

        int result = compare(value, root.getValue());

        if(result==0) return root;

        if(result<0) {
            return search(root.getLeft(), value);
        } else {
            return search(root.getRight(), value);
        }
    }

    private int compare(T value1, T value2) {
        return value1.toString().compareTo(value2.toString());
    }
    
    public int size() {return size;}

	public Queue<BstNode<T>> traverse(int order) {
		Queue<BstNode<T>> nodes = new LinkedBlockingQueue<BstNode<T>>();
		switch(order) {
			case PREORDER: {
				traversePreOrder(root, nodes);
				break;
			}
			case INORDER: {
				traverseInOrder(root, nodes);
				break;
			}
			case POSTORDER: {
				traversePostOrder(root, nodes);
				break;
			}
			default: break;
		}
		return nodes;
	}

	private void traverseInOrder(BstNode<T> root, Queue<BstNode<T>> nodes) {
		if(root==null) return;
		if(root.getLeft()!=null) traverseInOrder(root.getLeft(), nodes);
		nodes.add(root);
		if(root.getRight()!=null) traverseInOrder(root.getRight(), nodes);
		
	}

	private void traversePostOrder(BstNode<T> root, Queue<BstNode<T>> nodes) {
		if(root==null) return;
		if(root.getLeft()!=null) traverseInOrder(root.getLeft(), nodes);
		if(root.getRight()!=null) traverseInOrder(root.getRight(), nodes);
		nodes.add(root);
	}

	private void traversePreOrder(BstNode<T> root, Queue<BstNode<T>> nodes) {
		if(root==null) return;
		nodes.add(root);
		if(root.getLeft()!=null) traverseInOrder(root.getLeft(), nodes);
		if(root.getRight()!=null) traverseInOrder(root.getRight(), nodes);
	}

}

