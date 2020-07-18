package alorithm.dataStructureLow;

public class TreeWithLinkedList<T> {
    private T value;
    private TreeWithLinkedList<T> leftNode;
    private TreeWithLinkedList<T> rightNode;
   
    public TreeWithLinkedList( T value ) {
        this.value = value;
    }
    
    /**
     * left -> root -> right
     */
    public void inOrderTraversal() {
        if( leftNode != null ) leftNode.inOrderTraversal();
        System.out.println(value.toString());
        if( rightNode != null ) rightNode.inOrderTraversal();
    }
    
    /**
     * root -> left -> right
     */
    public void preOrderTraversal() {
        System.out.println(value.toString());
        if( leftNode != null ) leftNode.preOrderTraversal();
        if( rightNode != null ) rightNode.preOrderTraversal();
    }

    /**
     * left -> right -> root
     */
    public void postOrderTraversal() {
        if( leftNode != null ) leftNode.postOrderTraversal();
         if( rightNode != null ) rightNode.postOrderTraversal();
        System.out.println(value.toString());
    }


    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public TreeWithLinkedList<T> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeWithLinkedList<T> leftNode) {
        this.leftNode = leftNode;
    }

    public TreeWithLinkedList<T> getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeWithLinkedList<T> rightNode) {
        this.rightNode = rightNode;
    }
}
