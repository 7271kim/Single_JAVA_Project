package prorammers.kakao;
import java.util.Stack;

// https://programmers.co.kr/learn/courses/30/lessons/42883
// 탐욕법 및 Stack에 대한 응용이 잘 안됨.
// 핵심은 하나하나 지워가면서 4개가 되면 더이상 지울 수 없음. 이것에 대해 헷갈림.

class Programmers42883Greedy {
    public String solution( String number, int k ) {
        int count = 0;
        int size = number.length();
        int totalPrint = size - k;
        
        Stack<Character> temp = new Stack<Character>();
        
        for ( int index = 0; index < size; index++ ) {
            char thisChar = number.charAt(index);
            
            while( !temp.isEmpty() && temp.peek() < thisChar && count != k ) {
                temp.pop();
                count++;
            }
            
            temp.add(thisChar);
        }
        StringBuilder tempString = new StringBuilder();
        for (int index = 0; index < totalPrint; index++) {
            tempString.append(temp.get(index));
        }
        return tempString.toString();
    }
}