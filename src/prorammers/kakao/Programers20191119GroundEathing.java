package prorammers.kakao;
//https://programmers.co.kr/learn/courses/30/lessons/12913
class Programers20191119GroundEathing {
    int solution(int[][] land) {
        int answer = 16;
        
        for (int row  = 1; row  < land.length; row ++) {
            for (int columns = 0; columns < land[row].length; columns++) {
                int tempMax  = 0;
                for (int index = 0; index < 4; index++) {
                    if( index != columns ) {
                        tempMax = Math.max(tempMax, land[row-1][index]);
                    }
                }
                land[row][columns] += tempMax;
            }
        }
        
        for (int index = 0; index < 4; index++) {
            answer = Math.max(answer, land[land.length-1][index]);
        }
        
        return answer;
    }
}