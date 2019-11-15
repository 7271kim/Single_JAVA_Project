package prorammers.kakao;
import java.util.Arrays;
//https://programmers.co.kr/learn/courses/30/lessons/42860
//BF
class Programers42860BF {
    public int solution(String name) {
        int answer = 0;
        int startIndex = 0;
        int endIndex   = 0;
        int nameSize   = name.length();
        int totalCount = 0;
        boolean[] checked = new boolean[nameSize];
        
        for (int index = 0; index < nameSize; index++) {
            int moving = name.charAt(index) - 'A';
            if( 26 - moving < moving ) moving = 26 - moving;
            if( moving != 0 ) {
                totalCount++;
                answer += moving;
                endIndex = index;
                if( startIndex == 0 && index != 0 ) {
                    startIndex = index;
                }
            }
        }
        
        if( name.charAt(0) != 'A' ) {
            totalCount--;
            checked[0] = true;
        }
            
        answer += Math.min(getLeftRight( checked, name, 1, totalCount, 0, nameSize), getLeftRight( checked, name, nameSize-1, totalCount, 0, nameSize ));
        
        return answer;
    }
    
    public int getLeftRight( boolean[] checked, String name, int index, int count, int minSize, int result ) {
        
        if( count == 0 ) {
            int compare = result > minSize ? minSize : result;
            return compare;
        }
        
        if( minSize < result ) {
            
            if( !checked[index] && name.charAt(index) != 'A' ) {
                count--;
            }
            boolean[] next = Arrays.copyOfRange(checked, 0, checked.length);
            next[index] = true;
            minSize++;
            
            
            int temp = index + 1 == name.length() ? 0 : index + 1; 
            result = getLeftRight( next, name, temp, count, minSize, result );
            temp = index - 1 < 0 ? name.length()-1 : index - 1; 
            result = getLeftRight( next, name, temp, count, minSize, result );
            
            minSize--;
            next[index] = false;
            if( !checked[index] && name.charAt(index) != 'A' ) {
                count++;
            }
        }
        
        return result;
    }
}