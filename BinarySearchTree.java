public class BinarySearchTree<T> {

    private BstNode<T> root;

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

        if (root == null) return null;

        return delete(root, value, all);

    }

    /**
     * delete a item from sub tree, return the deleted node, if it's the root then restructure
     * @param root
     * @param item
     * @param all
     * @return
     */
    private BstNode<T> delete(BstNode<T> root, T item, boolean all) {

        BstNode<T> deletingNode = search(root, item);

        if (deletingNode == null) {
            return null;
        }

        int countChildren = deletingNode.countChildren();

        if(countChildren==0) {

        } else if (countChildren==1) {

        } else {

        }
        // if root then remove

        return null;
    }


    private BstNode<T> insert(BstNode<T> root, T value) {

        if(null==root) return new BstNode<T>(value);

        int result = compareValue(value, root);
        if(result==0) {
            root.increase();
        }else if(result<0) {
            root.setLeft(insert(root.getLeft(), value));
        }else {
            root.setRight(insert(root.getRight(), value));
        }

        return root;
    }

    public BstNode search(T value) {
        return search(root, value);
    }

    private BstNode<T> search(BstNode<T> root, T value) {

        if(null==root) return null;

        int result = compareValue(value, root);

        if(result==0) return root;

        if(result<0) {
            return search(root.getLeft(), value);
        } else {
            return search(root.getRight(), value);
        }
    }

    private int compareValue(T value, BstNode<T> node) {
        String strNewVal = value.toString();
        String strCurrentNodeVal = node.getValue().toString();
        return strNewVal.compareTo(strCurrentNodeVal);
    }

}

