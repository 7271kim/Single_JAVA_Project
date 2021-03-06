package prorammers.kakao;
// https://programmers.co.kr/learn/courses/30/lessons/60058
// 실수한부분 확인 끝
// 실수한 부분은 ( 혹은 )을  뒤집는다를 순서를 뒤집는다로 넣어버림 
class KAKAO60058 {
    public String solution(String p) {
        String answer = "";
        answer = getResult(p);
        return answer;
    }
    
   
    
    public String getResult(String input) {
        if( input.equals("") ) return "";
        if( isRight( input ) ) return input;
        
        String[] group = divide ( input ); // // iput 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리
        StringBuilder first = new StringBuilder(group[0]);
        StringBuilder next  = new StringBuilder(group[1]);
        if( isRight(first.toString()) ) {
            // 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
            // 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다. 
            return first.append( getResult( next.toString())).toString(); 
        } else {
            //문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.

            //4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
            StringBuilder temp = new StringBuilder();
            temp.append('(');
            
           //4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
            temp.append(getResult(next.toString()));
            
            //4-3. ')'를 다시 붙입니다. 
            temp.append(')');
            
            //4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다. 
            temp.append(reverse( first.toString() ));
            
            //4-5. 생성된 문자열을 반환합니다.
            return temp.toString();
        }
    }
    
    public String reverse ( String input ) {
        StringBuilder result = new StringBuilder();
        //4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 리턴(여기를 잘못함)
        for (int index = 1; index < input.length()-1; index++) {
            char temp = input.charAt(index);
            if( temp == '(' ) {
                result.append(')');
            } else {
                result.append('(');
            }
        }
        return result.toString();
    }
    
    public Boolean isRight ( String input ) {
        Boolean result = true;
        int leftCount = 0;
        for (int index = 0; index < input.length(); index++) {
            char temp = input.charAt(index);
            if( index == 0 && temp == ')'  || leftCount < 0) {
                result = false;
                break;
            } else if( temp == '(' ) {
                leftCount++;
            } else {
                leftCount--;
            }
        }
        
        result = result && leftCount ==0 ? true : false;
        
        return result;
    }
    
    public String[] divide ( String input ) {
        String[] result = new String [2];
        int left    = 0;
        int right   = 0;
        StringBuilder first = new StringBuilder();
        StringBuilder next  = new StringBuilder();
        for (int index = 0; index < input.length(); index++) {
            if( left == right && left != 0 ) {
                next.append(input.charAt(index));
            } else {
                char temp = input.charAt(index);
                first.append(temp);
                if( temp == '(' ) {
                    left++;
                } else {
                    right++;
                }
            }
        }
        result[0] =  first.toString();
        result[1] =  next.toString();
                
        return result;
    }
    
    
    // 다시 풀어봄 20분 만에 품
    public String solution2(String p) {
        String answer = "";
        answer = getAlright2(p);
        return answer;
    }
    
    private String getAlright2 ( String input )  {
        
        if( input == null || input.equals("") ) return "";
        StringBuilder result = new StringBuilder();
        
        String [] divided = getBalenceStr2(input);
        String u = divided[0];
        String v = divided[1];
        
        if( isRight2(u) ) {
            result.append(u).append(getAlright2(v));
        } else {
            result.append("(");
            result.append(getAlright2(v));
            result.append(")");
            result.append(reverse2(u));
        }
        
        return result.toString();
    }
    
    private String reverse2(String input) {
        StringBuilder temp = new StringBuilder();
        for (int index = 1; index < input.length() - 1; index++) {
            if( input.charAt(index) == '(' ) {
                temp.append(')');
            } else {
                temp.append('(');
            }
        }
        return temp.toString();
    }

    private String[] getBalenceStr2( String input ) {
        String[] result = new String[2];
        int left = 0;
        for (int index = 0; index < input.length(); index++) {
            if( input.charAt(index) == '(' ) {
                left++;
            } else {
                left--;
            }
            if( left == 0 ) {
                result[0] = input.substring(0,index+1);
                result[1] = input.substring(index+1,input.length());
                break;
            }
        }
        return result;
    }
    
    private boolean isRight2( String input ) {
        if( input == null ) return false;
        boolean result = true;
        
        int left = 0;
        for (int index = 0; index < input.length(); index++) {
            if( input.charAt(index) == '(' ) {
                left++;
            } else {
                left--;
            }
            
            if(left < 0) {
                result = false;
                break;
            }
        }
        return result;
    }
}