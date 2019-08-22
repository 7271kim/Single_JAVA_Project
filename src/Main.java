import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int exams = Integer.parseInt(br.readLine());
            for (int exam = 0; exam < exams; exam++) {
                String[] dvdShow = br.readLine().split(" ");
                String[] shows   = br.readLine().split(" ");
                int dvd     = Integer.parseInt(dvdShow[0]);
                int show    = Integer.parseInt(dvdShow[1]);
                int[] dvdPosition = new int[dvd+1];
                IndexTreeOrignal tree = new IndexTreeOrignal(dvd + show + 1);
                for (int index = 1; index <= dvd; index++) {
                    dvdPosition[index] = show+index;
                    tree.update(dvdPosition[index], 1);
                }
                
                for (int index = 0; index < shows.length; index++) {
                    int item = Integer.parseInt(shows[index]);
                    System.out.println(tree.sumTotal(dvdPosition[item]-1));
                    tree.update(dvdPosition[item], 0);
                    dvdPosition[item] = show - index;
                    tree.update(dvdPosition[item], 1);
                }
            } 
      } catch (Exception e) {
    } 
    }
}

class FenwickTreeOriganl{
    int tree [];
    
    public FenwickTreeOriganl(int size) {
        tree = new int[size];
    }
    public void update(int index, int val) {
        int orignal  = sumTotal( index)-sumTotal( index-1);
        int increase = val - orignal;
        while ( index < tree.length) {
            tree[ index ] += increase;
            index +=  index & (- index);
        }
    }
    
    public int sumTotal(int endIndex) {
        int res = 0;
        while (endIndex > 0) {
            res += tree[endIndex];
            endIndex &= endIndex-1;
        }
        return res;
    }
    
    public int sumInterVal(int startIndex, int endIndex) {
        return sumTotal(endIndex) - sumTotal(startIndex);
    }
    
    public void printOriginal() {
        for (int index = 0; index < tree.length; index++) {
            System.out.print(tree[index] + " ");
        }
        System.out.println();
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
 
    public void update(int index, int val) {
        index += originalStart;
        int minus = tree[index];
        int pointer = index;
        while (pointer != 0) {
            tree[pointer] = tree[pointer] - minus + val;
            pointer /= 2;
        }
    }
    
    public void printOriginal() {
        for (int index = 0; index < tree.length; index++) {
            System.out.print(tree[index] + " ");
        }
        System.out.println();
    }
}
