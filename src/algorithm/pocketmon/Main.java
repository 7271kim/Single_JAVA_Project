package algorithm.pocketmon;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;
public class Main{
    public static void main(String args[]){
        /*
         * https://www.acmicpc.net/problem/1620
         * 알고리즘 문제
         * 내것 
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> pocketMonData         = new ArrayList<String>();
        pocketMonData.add("");
        Map<String, Integer> pocketMonDataMap   = new HashMap<String, Integer>(); 
        try {
            String [] inputFirst        = br.readLine().split(" ");
            int pocketMonsterCount      = Integer.parseInt(inputFirst[0]);
            int questionCount           = Integer.parseInt(inputFirst[1]);
            for ( int index = 0; index < pocketMonsterCount; index++ ) {
                String data = br.readLine();
                pocketMonData.add(data);
                pocketMonDataMap.put(data, index+1);
            }
            for ( int index = 0; index < questionCount; index++) {
                String question = br.readLine();
                if(Pattern.matches("^[0-9]*$", question)) {
                    System.out.println(pocketMonData.get(Integer.parseInt(question)));
                } else {
                    System.out.println( pocketMonDataMap.get(question) );
                }
            }
        } catch (Exception e) {
        
        }
        
        /*
         * https://www.acmicpc.net/problem/1620
         * 알고리즘 문제
         * 남의것 == > 구지 어레이 리스트로 나눌 필요가 있었나.
         */
        Scanner sc = new Scanner(System.in);
        HashMap<String, String> map = new HashMap<>();
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        for(int i=1;i<=n;i++) {
            String s = sc.next();
            map.put(s, Integer.toString(i));
            map.put(Integer.toString(i),s);
        }
        for(int i=0;i<m;i++) {
            String s = sc.next();
            System.out.println(map.get(s));
        }
    }
}

