import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
// 1번문제 풀이
class Solution {
    public int solution(String s) {
        int totalLength = s.length(); 
        int maxDive     = totalLength/2;
        int min         = totalLength;
        for (int index = 1; index <= maxDive; index++) {
            int result      = 0;
            ArrayList<String> temp = new ArrayList<String>();
            int start = 0;
            int end   = index;
            while( end < totalLength ) {
                temp.add(s.substring( start, end ));
                start = end;
                end   += index;
                if( end >= totalLength) {
                    temp.add(s.substring( start, totalLength ));
                } 
            }
            Map<String, Integer> mapData = new HashMap<String, Integer>();
            for (int tmepIndex = 0; tmepIndex < temp.size(); tmepIndex++) {
                String text      = temp.get(tmepIndex);
                result          += text.length();
                if( !mapData.containsKey(text) ) {
                    mapData.clear();
                    mapData.put(text, 1);
                } else {
                    result -= text.length();
                    int mapNumber   = mapData.get(text);
                    if(mapNumber == 1 ) {
                        result++;
                    } else {
                        int addSize     = String.valueOf((mapNumber + 1)).length();
                        int beForeSize  = String.valueOf((mapNumber)).length();
                        result +=  ( addSize - beForeSize);
                    }
                    mapData.put(text, mapNumber+1);
                }
            }
            if( min > result ) {
                min = result;
            }
        }
        return min;
    }
}