
public class TreeNode {
    
    private Object node;
    private TreeNode left;
    private TreeNode right;
    
    public TreeNode( Object node ) {
        this.node   = node;
        this.left  = null;
        this.right  = null;
    }
    
    
    //중위 순회(in-order traversal): 왼쪽 가지 -> 현재 노드 -> 오른쪽 가지
    public void inOrderTraversal() {
        if( left != null ) left.inOrderTraversal();
        print();
        if( right != null ) right.inOrderTraversal();
    }
    
  //전위 순회(pre-order traversal): 현재 노드 ->왼쪽 -> 오른쪽 가지
    public void preOrderTraversal() {
        print();
        if( left != null ) left.preOrderTraversal();
        if( right != null ) right.preOrderTraversal();
    }
     
  //후위 순회(post-order traversal): 왼쪽 노드 -> 오른쪽 가지 -> 현재 가지
    public void postOrderTraversal() {
        if( left != null ) left.postOrderTraversal();
        if( right != null ) right.postOrderTraversal();
        print();
    }
    
    public void print() {
        if( node != null) {
            System.out.println(node);
        }
    }
    
    public void setLeft( TreeNode input ) {
        left = input;
    }
    public void setRight( TreeNode input ) {
        right = input;
    }
}
