package prorammers.kakao;
//https://programmers.co.kr/learn/courses/30/lessons/60057 
class KAKAO60057DivideChar {
    
    // 내껏 
    public int solution(String s) {
        int size   = s.length();
        int min    = 10000;
        
        out: for ( int divide = 1; divide < size+1; divide++ ) {
            String[] temp = new String[10002];
            StringBuilder finalText      = new StringBuilder();
            
            int tempCount = 0;
            
           // N 별로 나눈다
            StringBuilder tempBulder = new StringBuilder();
            for (int sIndex = 1; sIndex < s.length()+1; sIndex++) {
                tempBulder.append(s.charAt(sIndex-1));
                if( sIndex  % divide == 0 ) {
                    temp[tempCount++] = tempBulder.toString();
                    tempBulder = new StringBuilder();
                } else {
                    if( sIndex == s.length() ) temp[tempCount++] = tempBulder.toString();
                }
            }
            
            // 압축한다.
            int beforeCount = 1;
            for (int index = 0; index < temp.length-1; index++) {
                String thisText     = temp[index];
                String nextText     = temp[index+1];
                
                if( thisText == null ) {
                    break;
                }
                    
                if( thisText.equals(nextText) ) {
                    beforeCount++;
                } else {
                    if( beforeCount > 1) {
                        finalText.append(beforeCount).append(thisText);
                    } else {
                        finalText.append(thisText);
                    }
                    beforeCount = 1;
                }
                
                if( finalText.length() > min ) continue out;
            }
            
            min = finalText.length();
        }
        return min;
    }
    
    // 남의껏 
    // 남들 코드 1 
    // 핵심 : 4가지의 과정을 보기 
    // 1. N별로 나누기
    // 2. 압축하는방법
    // 3. 길이는 반환하는 방법
    public int solution2(String s) {
        int min = s.length();
        int len = s.length()/2+1; // 1/2 만큼만 하면 뒤에는 자동임
        for(int i = 1; i < len; i++) {
            String before = "";
            int sum = 0;
            int cnt = 1;
            for(int j = 0; j < s.length();) {               
                int start = j;
                j = (j+i > s.length()) ? s.length():j+i;
                String temp = s.substring(start, j); // 1. N으로 나누기 
                if(temp.equals(before)) {
                    cnt++;
                } else {
                    if(cnt != 1) {
                        // 길이를 반환하기
                        sum += (int)Math.log10(cnt)+1;
                    }
                    // 다를시 초기화부분
                    cnt = 1;
                    // 기존 것을 붙인다.
                    sum+=before.length();
                    before = temp;
                }
            }
            
            // 최종 나왔을 때 마지막값에 대해 한번 더 해주기 
            sum+=before.length();
            if(cnt != 1) {
                sum += (int)Math.log10(cnt)+1;
            }
            min = (min > sum) ? sum : min;
        }

        return min;
    }
}