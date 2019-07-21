import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Main{
    public static void main(String args[]){
        /*
         * https://www.acmicpc.net/problem/5639
         * 알고리즘 문제
         * 마지막 문자열까지 받기
         * 이진트리 한번도 안해본 것.
         * 트리 데이터를 저장하는 것.
         * https://sophia2730.tistory.com/entry/Algorithm-%EB%B0%B1%EC%A4%805639-%EC%9D%B4%EC%A7%84-%EA%B2%80%EC%83%89-%ED%8A%B8%EB%A6%AC
         * 
         *참고
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String inputData = br.readLine();
            BinaryTree result = new BinaryTree(Integer.parseInt(inputData));
            while ( (inputData = br.readLine()) != null && inputData.length() != 0) {    //EOF까지 입력받음
                result.addTree(result, Integer.parseInt(inputData));
            }
            
            System.out.println("result.node : " + result.node);
            System.out.println("result.lefet.node :" +result.lefet.node);
            System.out.println("result.right :" +result.right.node);
           
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}

class BinaryTree {
    int node;
    BinaryTree lefet;
    BinaryTree right;
    
    public BinaryTree( int node ) {
        this.node   = node;
        this.lefet  = null;
        this.right  = null;
    }
    
    public BinaryTree addTree ( BinaryTree orignalTree, int value ) {
        System.out.println("하잇!!!");
        BinaryTree tempBinaryTree = new BinaryTree(value);
        if( value < orignalTree.node ) {
            System.out.println("하잇!!! 작다");
            orignalTree.lefet = tempBinaryTree;
        } else if( value > orignalTree.node ) {
            orignalTree.right = tempBinaryTree;
            System.out.println("하잇!!! 크다");
        }
        
        System.out.println(tempBinaryTree.node);
        
        return orignalTree;
    }
}
