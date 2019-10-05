import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       
       int N = sc.nextInt();
       int K = sc.nextInt();
       int findNumver = (K+1)/2;
       long result = 0;
       
       int[] lowData = new int[N+1];
       
       // 0 ~ 65536까지가 마지막 노드 범위
       IndexTreeOrignal tree = new IndexTreeOrignal(65537);
       
       for(int i = 1 ; i <= N ; i++) {
           int inputNumber = sc.nextInt();
           int treeData    = tree.getTreeData(inputNumber);
           lowData[i] = inputNumber;
           
           if(i < K) {
               tree.update(inputNumber, treeData+1);
           } else {
               if(i > K) {
                   int removeIndex = lowData[i-K];
                   int beforeData = tree.getTreeData(removeIndex);
                   tree.update(removeIndex, beforeData-1);
                   
               }
               treeData    = tree.getTreeData(inputNumber);
               tree.update(inputNumber, treeData+1);
               result += tree.search(findNumver);
           }
       }
       System.out.println(result);
       sc.close();     
   }     
}

class IndexTreeOrignal{
    int tree [];
    int originalStart;
    public IndexTreeOrignal(int originalSize) {
        originalStart = 1;
        while (originalStart < originalSize)
            originalStart <<= 1;
        tree = new int[originalStart*2];
    }
    public void update(int index, int val) {
        index += originalStart;
        int minus = tree[index];
        int pointer = index;
        while (pointer != 0) {
            tree[pointer] = tree[pointer] - minus + val;
            pointer /= 2;
        }
    }
    public int search( int findNumber ) {
        int findIndex = 1;
        int leftChild;
        while(findIndex < originalStart) {
            leftChild = tree[findIndex*2]; 
            if(findNumber <= leftChild) {
                findIndex = findIndex * 2;
            } else {
                findNumber = findNumber - leftChild;
                findIndex = findIndex * 2 + 1; 
            }
        }
        return findIndex - originalStart;
    }
    public int getTreeData ( int index ) {
        return tree[originalStart + index];
    }
}