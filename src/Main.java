public class Main {
    public static void main(String args[]){
        Solution test = new Solution();
        Tree root = new Tree();
        Tree rootLeft = new Tree();
        Tree rootLeftLeft = new Tree();
        Tree rootLeftRight = new Tree();
        
        Tree rootRigth = new Tree();
        Tree rootRigthLeft = new Tree();
        Tree rootRigthRight = new Tree();
        
        Tree rootRigthRightRight = new Tree();
        
        root.x = 1;
        rootLeft.x = 3;
        rootLeftLeft.x = 2;
        rootLeftRight.x = 2;

        rootRigth.x = 4;
        rootRigthLeft.x = 2;
        rootRigthRight.x = 2;
        
        rootRigthRightRight.x = 1;
        
        root.l = rootLeft;
        
        rootLeft.l = rootLeftLeft;
        rootLeft.r = rootLeftRight;
        
        
        root.r = rootRigth;
        
        rootRigth.l = rootRigthLeft;
        rootRigth.r = rootRigthRight;
        
        rootRigthRight.r = rootRigthRightRight;
        
        
        
        System.out.println(test.solution(root));
    }
}