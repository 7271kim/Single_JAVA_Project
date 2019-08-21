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
                FenwickTree tree = new FenwickTree(dvd + show + 1);
                for (int index = 1; index <= dvd; index++) {
                    dvdPosition[index] = show+index;
                    tree.update(dvdPosition[index], 1);
                }
                
                for (int index = 0; index < shows.length; index++) {
                    int item = Integer.parseInt(shows[index]);
                    System.out.println(tree.sum(dvdPosition[item]-1));
                    tree.update(dvdPosition[item], -1);
                    dvdPosition[item] = show - index;
                    tree.update(dvdPosition[item], 1);
                }
            } 
      } catch (Exception e) {
    } 
    }
}

class FenwickTree{
    int tree [];
    
    public FenwickTree(int size) {
        tree = new int[size];
    }
    public void update(int p, int val) {
        while (p < tree.length) {
            tree[p] += val;
            p += p & (-p);
        }
    }
    public int sum(int p) {
        int res = 0;
        while (p > 0) {
            res += tree[p];
            p &= p-1;
        }
        return res;
    }
}
