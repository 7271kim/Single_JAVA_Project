package algorithm.dataStructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WhileItor {
    
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputData = br.readLine();
        BinaryTree result = new BinaryTree(Integer.parseInt(inputData));
        while ( (inputData = br.readLine()) != null && inputData.length() != 0) {    //EOF까지 입력받음
            result.addTree(result, Integer.parseInt(inputData));
        }
    }
}
