package prorammers.kakao;
//https://programmers.co.kr/learn/courses/30/lessons/43165
// DFS 정리.
class Programmers20191116_43165_BFS {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        int[] next        = new int[numbers.length];
        answer = getTotal( numbers, 0, next, target );
        answer = getTotal2( numbers, 0, 0, target );
        return answer;
    }

    private int getTotal(int[] numbers, int index, int[] next, int target) {
        if( index == numbers.length ) {
            int temp = 0;
            for (int indexNext = 0; indexNext < next.length; indexNext++) {
                temp += next[indexNext];
            }
            if( temp == target) {
                return 1;
            } else {
                return 0;
            }
        }
        int result = 0;
        next[index] = numbers[index];
        result += getTotal(numbers, index+1, next, target);
        next[index] = -numbers[index];
        result += getTotal(numbers, index+1, next, target);
        
        return result;
    }
    
    private int getTotal2(int[] numbers, int index, int next, int target) {
        if( index == numbers.length ) {
            if( next == target) {
                return 1;
            } else {
                return 0;
            }
        }
        
        return getTotal2(numbers, index+1, next + numbers[index], target) + getTotal2(numbers, index+1, next - numbers[index], target);
    }

}