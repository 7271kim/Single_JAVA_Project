package prorammers.kakao;
import java.util.HashSet;

// https://programmers.co.kr/learn/courses/30/lessons/42839?language=java
// 완전 탐색 정말 베이스 문제임.
class Programeres42839BruteForce {
    HashSet<Integer> total = new HashSet<Integer>();
    
    public int solution(String numbers) {
        int answer = 0;
        boolean[] isCheked = new boolean[numbers.length()]; 
        getTotal( numbers, "", isCheked );
        for (int i : total) {
            if( isPrime(i) ) {
                answer++;
            }
        }
        return answer;
    }
    
    public void getTotal( String numbers, String result, boolean[] isCheked ) {
        if( !result.equals("") ) {
            total.add(Integer.valueOf(result));
        }
        
        for(int index = 0; index<numbers.length(); index++) {
            if(isCheked[index] != true) {
                char thisChar = numbers.charAt(index);
                isCheked[index] = true; 
                result += thisChar;
                getTotal(numbers, result, isCheked);
                result = result.substring(0, result.length()-1);
                isCheked[index]  = false; 
            }
        }
    }
    
    public boolean isPrime( int number ) {
        
        if( number == 1 || number == 0 ) return false;
        
        boolean result = true;
        
        int last       = ( int )Math.sqrt ( number );
        
        for (int index = 2; index <= last; index++) {
            if( number%index == 0 ) {
                result = false;
                break; 
            }
        }
        
        return result;
    }
}