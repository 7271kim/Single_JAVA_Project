package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * https://www.acmicpc.net/problem/4949
 * 균형잡힌 세상
 */

public class Beak4949 {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Scanner sc = new Scanner(System.in);
        try {
            String inputData;
            while ( (inputData = br.readLine()) != null && inputData.length() != 0) {    //EOF까지 입력받음
                Stack<String> brackets = new Stack<String>();
                String result        = "yes";
                String[] next = inputData.split("");
                for (int inner = 0; inner < next.length; inner++) {
                    String temp = next[inner];
                    if( temp.indexOf("(") > -1 ) {
                        brackets.push("(");
                    } else if( temp.indexOf("[") > -1 ) {
                        brackets.push("[");
                    } else if(temp.indexOf(")") > -1 ) {
                        if( !brackets.isEmpty() && brackets.get(brackets.size()-1) == "(" ) {
                            brackets.pop();
                        } else {
                            result        = "no";
                            break;
                        }
                    } else if(temp.indexOf("]") > -1 ) {
                        if( !brackets.isEmpty() && brackets.get(brackets.size()-1) == "[" ) {
                            brackets.pop();
                        } else {
                            result        = "no";
                            break;
                        }
                    }
                }
                if(!brackets.isEmpty()) result = "no";
                if(!(inputData.length() == 1 && inputData.equals("."))) {
                    System.out.println(result);
                }
            }
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}