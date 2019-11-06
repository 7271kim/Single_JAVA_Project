package prorammers.kakao;
class Programmers42854 {
    public int[] solution(int[] prices) {
        int size = prices.length;
        int[] answer        = new int[size];
        
        for (int index = 0; index < size ; index++) {
            int now = prices[index];
            for (int index2 = index+1; index2 < size; index2++) {
                int thisCount = prices[index2];
                answer[index] += 1;
                if( thisCount < now )
                    break;
            }
        }
        
        return answer;
    }
}