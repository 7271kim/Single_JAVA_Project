import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/4153
 */

public class Main {
    public static void main(String args[]){
        Solution test = new Solution();
        String[] sticker = {"D5","E8","G2"};
        System.out.println(test.solution(sticker));
    }
}
