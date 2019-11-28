
class Solution {
    public String solution(String p) {
        String answer = "";
        answer = getAlright(p);
        return answer;
    }
    
    private String getAlright ( String input )  {
        
        if( input == null || input.equals("") ) return "";
        StringBuilder result = new StringBuilder();
        
        String [] divided = getBalenceStr(input);
        String u = divided[0];
        String v = divided[1];
        
        if( isRight(u) ) {
            result.append(u).append(getAlright(v));
        } else {
            result.append("(");
            result.append(getAlright(v));
            result.append(")");
            result.append(reverse(u));
        }
        
        return result.toString();
    }
    
    private String reverse(String input) {
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

    private String[] getBalenceStr( String input ) {
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
    
    private boolean isRight( String input ) {
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