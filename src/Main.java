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
                int[] treeData  = new int[ dvd + show + 1 ];
                for (int index = 1; index <= dvd; index++) {
                    treeData[show+index] = 1;
                    dvdPosition[index] = show+index;
                }
                String result = "";
                IndexTree tree = new IndexTree(treeData);
                for (int index = 0; index < shows.length; index++) {
                    int item = Integer.parseInt(shows[index]);
                    result += tree.sumTotal(dvdPosition[item]-1);
                    if(index != shows.length-1) result += " ";
                    tree.printOriginal();
                    tree.update(show, 1);
                    tree.printOriginal();
                    tree.update(dvdPosition[item], 0);
                    tree.printOriginal();
                    
                    dvdPosition[item] = show;
                    show--;
                }
                System.out.println(result);
            } 
        } catch (Exception e) {
        } 
        
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
        for (int index = 0; index < orignal.length; index++) {
            update(index, orignal[index]);
        }
    }
    public void update(int index, int value) {
        int orignalPosision = orignalSize+index+1;
        int beforeValue     = tree[orignalPosision];
        //System.out.println("orignalPosision" + orignalPosision);
        //System.out.println("beforeValue" + beforeValue);
        index += 1;
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
            update( index, orignal[index] );
        }
    }
    
    public void update( int index, int value) {
        index += originalStart;
        int beforeValue = data[index];
        while(index > 0) {
            data[index] = data[index] - beforeValue + value;
            index>>=1;
        }
    }
    
    public int sumTotal( int end ) {
        int start = 1;
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
   public int sumInterVal( int start, int end ) {
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
   
    public void printOriginal() {
        for (int index = 0; index < data.length; index++) {
            System.out.print(data[index] + " ");
        }
        System.out.println();
    }
}
