
public class BstNode<T> {

    private T value;

    private int counter;

    private BstNode<T> left;

    private BstNode<T> right;

    public BstNode(T value) {
        this.value = value;
        increase();
    }

    public T getValue() {return value;}
    
    public void setValue(T value) {this.value = value;}

    public int getCounter() { return counter; }
    
	public void setCounter(int counter) { this.counter = counter;}

    public BstNode<T> getLeft() {return left;}

    public BstNode<T> getRight() {return right;}

    public void setLeft(BstNode<T> newLeft) {this.left = newLeft;}

    public void setRight(BstNode<T> newRight) {this.right = newRight;}

    public void increase() { counter ++; }

    public void decrease() { counter --; }

}
