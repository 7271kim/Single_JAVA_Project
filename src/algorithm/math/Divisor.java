package algorithm.math;

import java.util.ArrayList;

// 약수를 구하는 방법
// 입력의 최대치를 정하고 그 최대치까지의 모든 카운트를 하여 갯수만을 구하는 방법
// 정말 해당수가 필요한경우 확인.
public class Divisor {
    // 갯수만을 원할 경우
    public int divisorCount( int maxInputSize, int findNum ) {
        
        int[] temp     = new int[maxInputSize+1];
        
        for (int first = 1; first <= maxInputSize; first++) {
            for (int second = 1; second <= maxInputSize/first; second++) {
                temp[first*second]++;
            }
        }
        
        return temp[findNum];
    }
    
    // 약수 전체를 원할경우
    public ArrayList<Integer> getDivisor( int maxInputSize, int findNum ) {
        ArrayList<Integer>[] datas = new ArrayList[maxInputSize+1];
        
        for (int first = 1; first <= maxInputSize; first++) {
            for (int second = 1; second <= maxInputSize/first; second++) {
                if( datas[first*second] == null ) {
                    datas[first*second] = new ArrayList<Integer>();
                }
                datas[first*second].add(first);
            }
        }
        
        return datas[findNum];
    }
    
}