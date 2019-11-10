// https://programmers.co.kr/learn/courses/30/lessons/42862 >> 탐욕법
class Solution {
    public int solution( int n, int[] lost, int[] reserve ) {
        int answer = 0;

        int[] uniform = new int[n+1];
        
        for (int index = 1; index < n+1; index++) {
            uniform[index] = 1;
        }
        
        for (int index = 0; index < lost.length; index++) {
            int lostIndex = lost[index];
            uniform[lostIndex] -= 1;
        }
        
        for (int index = 0; index < reserve.length; index++) {
            int addIndex = reserve[index];
            uniform[addIndex] += 1;
        }
        
        for (int index = 1; index < uniform.length; index++) {
            if(uniform[index] == 2 ) {
                boolean hasBefore = index-1 > 0 ? uniform[index-1] == 1 : true;
                boolean hasBeNext =  index+1 < uniform.length ? uniform[index+1] == 1 : true;
                if(!hasBefore ) {
                    uniform[index-1] = 1;
                    uniform[index] = 1;
                } else if( !hasBeNext ) {
                    uniform[index+1] = 1;
                    uniform[index] = 1;
                }
            }
        }
        
        for (int index = 1; index < uniform.length; index++) {
            if( uniform[index] > 0 ) answer++;
        }
        
        return answer;
    }
}