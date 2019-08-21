import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Scanner sc = new Scanner(System.in);
        
        try {
            String inputData = br.readLine();
            TempTree tree = new TempTree(Integer.parseInt(inputData));
            while ( (inputData = br.readLine()) != null && inputData.length() != 0) {   
                tree.add(tree, Integer.parseInt(inputData));
            }
            tree.post();
        } catch (Exception e) {
        } 
        
    }
}
class TempTree {
    int root;
    TempTree left;
    TempTree right;
    public TempTree ( int root ) {
        this.root  = root;
        this.left  = null;
        this.right = null;
    }
    public TempTree add(TempTree tree , int next) {
        if(tree == null) return new TempTree(next);
        
        if( next < tree.root ) tree.left  = add(tree.left, next);
        if( next > tree.root ) tree.right = add(tree.right, next);
        
        return tree;
    }
    public void post() {
        if(left != null) left.post();
        if(right != null) right.post();
        System.out.println(root);
    }
    public void pre() {
        System.out.println(root);
        if(left != null) left.pre();
        if(right != null) right.pre();
    }
}
