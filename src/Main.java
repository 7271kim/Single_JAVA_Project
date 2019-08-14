import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        /*
         * https://www.acmicpc.net/problem/1572
         * 중앙값 
         * 앞뒤 빼고 넣기
         * https://matice.tistory.com/54
         */
       
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] fistInput = br.readLine().split(" ");
        int n = Integer.parseInt(fistInput[0]);
        int k = Integer.parseInt(fistInput[1]);
        int [] input = new int[n-k+1];
        int [] mid = new int[k];
        for (int index = 0; index < n; index++) {
            int number = Integer.parseInt(br.readLine());
            if( index > k-1) {
                int temp = index%k;
                mid[temp] = number;
                input[index-k+1] = getMid(mid);
            } else if(index == k-1){
                mid[index] = number;
                input[index-k+1] = getMid(mid);
            } else {
                mid[index] = number;
            }
        }
        for (int index = 0; index < input.length; index++) {
            System.out.print(input[index] + " ");
        }
        IndexTree tree = new IndexTree(input);
        System.out.println();
        System.out.println(tree.intervalSum(0, input.length-1));
    }
    public static int getMid( int [] mid) {
        int[] temp = new int[mid.length];
        System.arraycopy(mid, 0, temp, 0, mid.length);
        for (int index = 0; index < mid.length; index++) {
            System.out.print(mid[index] + " ");
        }
        System.out.println();
        Arrays.sort(temp);
        int position = (temp.length-1)/2;
        System.out.println("mid[position] : " + temp[position]);
        return temp[position];
    }
}
class IndexTree {
    private int data[];
    private int originalStart;
    
    public IndexTree( int[] orignal ) {
        int originalSize = orignal.length;
        originalStart = 1;
        
        while (originalStart < originalSize)
            originalStart <<= 1;
        
        data = new int[originalStart*2];
        
        for( int index = 0; index < orignal.length; index++ ) {
            updateTree( index, orignal[index] );
        }
    }
    
    public void updateTree( int index, int value) {
        index += originalStart;
        int beforeValue = data[index];
        while(index > 0) {
            data[index] = data[index] - beforeValue + value;
            index>>=1;
        }
    }

   public int intervalSum( int start, int end ) {
       start += originalStart;
       end += originalStart;
       int sum = 0;
       while ( start < end ) {
           if( start%2 == 1) {
               sum += data[start]; 
               start++;
           }
           if( end%2 == 0) {
               sum += data[end];
               end--;
           }
           start>>=1;
           end>>=1;
       }
       if( start == end ) {
           sum += data[start];
       }
       
       return sum;
   }
   
    public void print() {
        for (int index = 0; index < data.length; index++) {
            System.out.print(data[index] + " ");
        }
        System.out.println();
    }
}

class BinaryIndexTree{
    int tree [];
    int orignalSize;
    int treeLength;
    
    public BinaryIndexTree( int[] orignal ) {
        orignalSize = orignal.length;
        tree = new int[orignalSize*2+1];
        treeLength = tree.length-orignalSize;
        for (int index = 1; index <= orignal.length; index++) {
            update(index, orignal[index-1]);
        }
    }
    public void update(int index, int value) {
        int orignalPosision = orignalSize+index;
        int beforeValue     = tree[orignalPosision];
        tree[orignalPosision]   = value;
        while (index < treeLength) {
            tree[index] = tree[index] - beforeValue + value;
            index += (index & -index); 
        }
    }
    public int sumTotal(int index) {
        int res = 0;
        while (index > 0) {
            res += tree[index];
            index &= index-1;
        }
        return res;
    }
    
    public int sumInterVal(int start , int end) {
        return sumTotal(end) - sumTotal(start-1);
    }
    
    public void printTotal() {
        for (int index = 0; index < tree.length; index++) {
            System.out.print(tree[index] + " ");
        }
        System.out.println();
    }
    public void printFenwick() {
        for (int index = 0; index < treeLength; index++) {
            System.out.print(tree[index] + " ");
        }
        System.out.println();
    }
    public void printOriginal() {
        for (int index = treeLength; index < tree.length; index++) {
            System.out.print(tree[index] + " ");
        }
        System.out.println();
    }
}
