import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Scanner sc = new Scanner(System.in);
        try {
            String [] firstInput = br.readLine().split(" ");
            int total =  Integer.parseInt(firstInput[0]);
            int range =  Integer.parseInt(firstInput[1]);
            int[] ori   = new int[65537];
            int[] indexPosition   = new int[total];
            IndexTreeOrignal tree = new IndexTreeOrignal(ori.length);
            int resut = 0;
            for (int index = 0; index < total; index++) {
                int number =Integer.parseInt(br.readLine());
                ori[number] +=1;
                indexPosition[index] = number;
                if( index < range ) {
                    tree.update(number, ori[number]);
                } else {
                    tree.update(number, ori[number]);
                    if(index != range) {
                        int beforeNumber    = indexPosition[index-range-1];
                        ori[beforeNumber]  -= 1;      
                        tree.update(beforeNumber, ori[beforeNumber]);
                    }
                    resut += tree.getMid((range+1)/2);
                }
            }
            System.out.println(resut);
        } catch (Exception e) {
            System.out.println(e);
        } 
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
    public int sumTotal ( int endIndex ) {
        return sumInterVal(0, endIndex);
    }
    
    public int getMid(int midIndex) {
        int mid = 0;
        int root = 1;
        while ( root < tree.length ) {
            if(  midIndex <= tree[root] ) {
                root <<= 1;
            } else if( midIndex > tree[root] ){
                root        +=1;
                midIndex    -=1;
                root <<= 1;
            }
        }
        root>>=1;
        System.out.println("length : " + tree.length);
        System.out.println("root : " + root);
        System.out.println("originalStart : " + originalStart);
        mid = root-originalStart;
        return mid;
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
    
    public int sumInterVal(int startIndex, int endIndex) {
        int sum = 0;
        startIndex += originalStart;
        endIndex += originalStart;
        while (startIndex < endIndex) {
            if ((startIndex & 1) == 1) {
                sum += tree[startIndex];
                startIndex++;
            }
            if ((endIndex & 1) == 0) {
                sum += tree[endIndex];
                endIndex--;
            }
            startIndex /= 2;
            endIndex /= 2;
        }
        if (startIndex == endIndex)
            sum += tree[startIndex];
        return sum;
    }
    
    public void printOriginal() {
        for (int index = 0; index < tree.length; index++) {
            System.out.print(tree[index] + " ");
        }
        System.out.println();
    }
}