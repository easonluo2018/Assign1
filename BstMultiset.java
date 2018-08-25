import java.io.PrintStream;
import java.util.Iterator;
import java.util.Queue;

public class BstMultiset<T> extends Multiset<T>
{

	private BinarySearchTree<T> bst;

	public BstMultiset() {
		// Implement me!
		super();
		bst = new BinarySearchTree<T>();
	} // end of BstMultiset()

	public void add(T item) {
		// Implement me!
		bst.insert(item);
	} // end of add()


	public int search(T item) {
		// Implement me!
		BstNode<T> node = bst.search(item);
		// default return, please override when you implement this method
		return node !=null ? node.getCounter() : 0;
	} // end of add()


	public void removeOne(T item) {
		// Implement me!
		bst.delete(item, false);
	} // end of removeOne()


	public void removeAll(T item) {
		// Implement me!
		bst.delete(item, true);
	} // end of removeAll()


	public void print(PrintStream out) {
		// Implement me!
		Queue<BstNode<T>> nodes = bst.traverse(BinarySearchTree.INORDER);
		Iterator<BstNode<T>> iter = nodes.iterator();
		while(iter.hasNext()) {
			BstNode<T> node = iter.next();
			out.println(node.getValue().toString() + " | " + node.getCounter());
		}
	} // end of print()

} // end of class BstMultiset
