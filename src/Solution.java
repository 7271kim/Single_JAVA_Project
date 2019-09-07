// 문제 2번 - 시키는 대로 하기
class Solution {
    public String solution(String p) {
        String answer = "";
        answer = recall(p);
        return answer;
    }
    
    public String recall( String p ) {
        if( p.equals("") ) return "";
        //입력이 빈 문자열인 경우, 빈 문자열을 반환합니다. 
        
        int closed = 0;
        int open   = 0;
        String[] input = p.split("");
        String result = "";
        String first = "";
        String second = "";
        Boolean isPerfect = true;
        
        for (int index = 0; index < input.length; index++) {
            String temp = input[index];
            if( temp.equals("(") ) {
                open++;
            } else {
                closed++;
                if( open - closed < 0 ) isPerfect = false;
            }
            if( open == closed ) {
                // 균형잡힌 괄호 문자열" u, v로 분리
                first    = p.substring(0, index+1);;
                second   = p.substring(index+1, p.length());
                break;
            }
        }
        if( isPerfect ) {
            //올바른 괄호 문자열  이라면 문자열 v에 대해 1단계부터 다시 수행합니다. 
            result += first;
            result += recall(second);
        } else {
            // 가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다
            //빈 문자열에 첫 번째 문자로 '('를 붙입니다
            result+= "(";
            //문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다. 
            result += recall(second);
            // ')'를 다시 붙입니다. 
            result+= ")";
            
            // u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다. 
            String[] temp = first.split("");
            temp[0] = "";
            temp[temp.length-1] = "";
            for (int index = 0; index < temp.length; index++) {
                String innerText = temp[index];
                if( innerText.equals("(") ) {
                    result+= ")";
                } else if( innerText.equals(")")) {
                    result+= "(";
                }
            }
        }
        
        return result;
    }
}