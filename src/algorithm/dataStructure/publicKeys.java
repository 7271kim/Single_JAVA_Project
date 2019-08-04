package algorithm.dataStructure;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class publicKeys {
    public static void main(String[] args) throws NumberFormatException, IOException {
        /*
         * https://www.acmicpc.net/problem/9322
         * 알고리즘 문제
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(br.readLine());
        for( int example = 0; example < n; example++ ) {
            int scale= Integer.parseInt(br.readLine());
            String[] publicKey = br.readLine().split(" ");
            String[] privateKey = br.readLine().split(" ");
            String[] cryptogram = br.readLine().split(" ");
            int resultSet[] = new int[scale];
            String[] result = new String[scale];
            for(int publicItem = 0; publicItem < publicKey.length; publicItem++) 
                for( int privateItem = 0; privateItem < privateKey.length; privateItem++)
                    if( publicKey[publicItem].equals(privateKey[privateItem]) ) resultSet[privateItem] = publicItem;
            for(int index = 0 ; index < resultSet.length; index++) {
                result[resultSet[index]] = cryptogram[index];
            }
            for( int index = 0; index < result.length; index++ ) {
                System.out.print(result[index]+" ");
            }
        }
    }
}

