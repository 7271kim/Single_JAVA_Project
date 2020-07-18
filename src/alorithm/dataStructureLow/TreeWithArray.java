package alorithm.dataStructureLow;

public class TreeWithArray<T> {
    private Object tree [];
    private int treeSize;
   
    public TreeWithArray( int arraySize ) {
       treeSize = 1;
        
        while (treeSize < arraySize) {
            treeSize <<= 1;
        }
        treeSize *= 2;
        tree = new Object[ treeSize ];
    }
    
    /**
     * left -> root -> right
     */
    public void inOrderTraversal( int checkIndex ) {
        if(  checkIndex < treeSize && tree[checkIndex] != null ) {
            int leftChildIndex = getLeftNodeIndex(checkIndex);
            int rightChildIndex =  getRightNodeIndex(checkIndex);
            inOrderTraversal( leftChildIndex );
            System.out.println(tree[checkIndex].toString());
            inOrderTraversal( rightChildIndex );
        } 
    }
    
    /**
     * root -> left -> right
     */
    public void preOrderTraversal( int checkIndex ) {
        if(  checkIndex < treeSize && tree[checkIndex] != null ) {
            int leftChildIndex = getLeftNodeIndex(checkIndex);
            int rightChildIndex =  getRightNodeIndex(checkIndex);
            System.out.println(tree[checkIndex].toString());
            preOrderTraversal( leftChildIndex );
            preOrderTraversal( rightChildIndex );
        } 
    }

    /**
     * left -> right -> root
     */
    public void postOrderTraversal( int checkIndex ) {
        if(  checkIndex < treeSize && tree[checkIndex] != null ) {
            int leftChildIndex = getLeftNodeIndex(checkIndex);
            int rightChildIndex =  getRightNodeIndex(checkIndex);
            postOrderTraversal( leftChildIndex );
            postOrderTraversal( rightChildIndex );
            System.out.println(tree[checkIndex].toString());
        } 
    }


    @SuppressWarnings("unchecked")
    public T getValue( int index ) {
        return (T)tree[index];
    }

    public void setValue( T value, int index ) {
        if( index >= treeSize ) {
            throw new IndexOutOfBoundsException("size를 확인하세요");
        }
        tree[index] = value;
    }

    @SuppressWarnings("unchecked")
    public T getLeftNode( int parentIndex ) {
        int childIndex = getLeftNodeIndex(parentIndex);
        if( childIndex >= treeSize ) {
            throw new IndexOutOfBoundsException("size를 확인하세요");
        }
        return (T)tree[childIndex];
    }

    public void setLeftNode( T leftNode, int parentIndex ) {
        int childIndex = getLeftNodeIndex( parentIndex );
        if( childIndex >= treeSize ) {
            throw new IndexOutOfBoundsException("size를 확인하세요");
        }
        tree[childIndex] = leftNode;
    }

    @SuppressWarnings("unchecked")
    public T getRightNode( int parentIndex ) {
        int childIndex =  getRightNodeIndex( parentIndex );
        if( childIndex >= treeSize ) {
            throw new IndexOutOfBoundsException("size를 확인하세요");
        }
        return (T)tree[childIndex];
    }

    public void setRightNode( T rightNode, int parentIndex) {
        int childIndex = getRightNodeIndex( parentIndex );
        if( childIndex >= treeSize ) {
            throw new IndexOutOfBoundsException("size를 확인하세요");
        }
        tree[childIndex] = rightNode;
    }
    
    public int getLeftNodeIndex( int parentIndex ) {
        return parentIndex*2 +1;
    }
    
    public int getRightNodeIndex( int parentIndex ) {
        return parentIndex*2 +2;
    }
}
