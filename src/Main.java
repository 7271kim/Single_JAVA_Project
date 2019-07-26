import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][4];
        long answer = 0;
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<n;i++)
            for(int j=0;j<4;j++)
                arr[i][j] = sc.nextInt();
        
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++) {
                int tmp = arr[i][0] + arr[j][1];
                if(map.containsKey(tmp))
                    map.replace(tmp, map.get(tmp)+1);
                else
                    map.put(tmp, 1);
            }
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++) {
                int tmp = (arr[i][2]+arr[j][3])*-1;
                if(map.containsKey(tmp)) //AB + CD == 0?
                    answer += map.get(tmp);
            }
        System.out.println(answer);
    }
}