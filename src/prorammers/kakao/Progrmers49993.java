package prorammers.kakao;
// https://programmers.co.kr/learn/courses/30/lessons/49993

class Progrmers49993 {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        out : for (int index = 0; index < skill_trees.length; index++) {
            int checkIndex = 0;
            String text = skill_trees[index];
            
            for (int indexChar = 0; indexChar < text.length(); indexChar++) {
                char thisChar = text.charAt(indexChar);
                if( skill.indexOf(thisChar) > -1 ) {
                    if( skill.charAt(checkIndex) == thisChar ) {
                        checkIndex++;
                    } else {
                        continue out;
                    }
                }
            }
            answer++;
        }
        
        return answer;
    }
}