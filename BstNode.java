
public class BstNode<T> {

    private T value;

    private int counter;

    private BstNode<T> left;

    private BstNode<T> right;

    private BstNode<T> parent;

    public BstNode(T value) {
        this.value = value;
        increase();
    }

    public T getValue() {return value;}

    public int getCounter() { return counter; }

    public BstNode<T> getLeft() {return left;}

    public BstNode<T> getRight() {return right;}

    public BstNode<T> getParent() {return parent;}

    public void setLeft(BstNode<T> newLeft) {
        this.left = newLeft;
    }

    public void setRight(BstNode<T> newRight) {
        this.right = newRight;
    }

    public void increase() { counter ++; }

    public void decrease() { counter --; }

    public int countChildren() {
        int count = 0;

        if(this.left != null) count ++;

        if(this.right != null) count ++;

        return count;
    }
}
