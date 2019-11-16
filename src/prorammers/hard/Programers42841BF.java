package prorammers.hard;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://programmers.co.kr/learn/courses/30/lessons/42841
// BP 난이도 좀 있음.
// 바로 떠오르지는 않음.
// 중복을 허용하지 않는다. 
// 전체를 만들어 놓고 하나하나 없애는 케이스
class Programers42841BF {
    public int solution(int[][] baseball) {
        int answer = 0;
        int[] next = new int[3];
        int[] checked = new int[3];
        int[][] total = new int[9*8*7][3];
        int[] totalCount = new int[1];
        Map<Integer, Integer> temp = new HashMap<Integer, Integer>();
        
        getTotal(checked, total, 0, totalCount, temp);
        
        for (int index = 0; index < baseball.length; index++) {
            int number = baseball[index][0];
            int strick = baseball[index][1];
            int ball   = baseball[index][2];
            
            removeTotal( total, number, strick, ball );
        }
        
        for (int index = 0; index < total.length; index++) {
            if( total[index][0] != 0 ) {
                answer++;
            }
        }
        
        return answer;
    }

    private void removeTotal( int[][] total, int number, int strick, int ball ) {
        for (int index = 0; index < total.length; index++) {
            
            if( total[index][0] == 0 ) continue;
            
            String numberString = String.valueOf(number);
            int stCount     = 0;
            int ballCount   = 0;
            
            for (int textIndex = 0; textIndex < numberString.length(); textIndex++) {
                int numberCom = numberString.charAt(textIndex) - '0';
                
                for (int indexCom = 0; indexCom < total[index].length; indexCom++) {
                    if( indexCom == textIndex && total[index][indexCom] == numberCom ) {
                        stCount++;
                    } else if(total[index][indexCom] == numberCom) {
                        ballCount++;
                    }
                }
            }
            
            if( strick != stCount || ballCount != ball ) {
                total[index][0] = 0;
            }
            
        }
    }

    private void getTotal( int[] checked, int[][] total, int index, int[] totalCount, Map<Integer, Integer> map ) {
        if( index >= 3 ) {
            int totalIndex = totalCount[0];
            totalCount[0] += 1;
            
            int[] temp = Arrays.copyOfRange(checked, 0, checked.length);
            
            total[totalIndex] = temp;
            
            return;
        }
        for (int next = 1; next < 10; next++) {
            if(checked[index] == 0 && !map.containsKey(next)) {
                checked[index] = next;
                map.put(next, next);
                getTotal(checked, total, index+1, totalCount , map);
                map.remove(next);
                checked[index] = 0;
            }
        }
    }
}