package prorammers.kakao;
import java.util.HashMap;
import java.util.Map;

// https://programmers.co.kr/learn/courses/30/lessons/42576
class Programmers42576 {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> temp = new HashMap<String, Integer>();
        
        for (String name : participant) {
            if( temp.containsKey(name) ) {
                temp.put(name, temp.get(name)+1);
            } else {
                temp.put(name, 1);
            }
            
        }
        
        for (String name : completion) {
            if( temp.containsKey(name) && temp.get(name) > 1 ) {
                temp.replace(name, temp.get(name)-1);
            } else {
                temp.remove( name );
            }
            
        }
        
        for (String name : temp.keySet()) {
            answer = name;
        }
        return answer;
    }
    
    // 효율적인 남코드
    // getOrDefault 해당 로직 배움
    public String solution2(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> temp = new HashMap<String, Integer>();
        
        for (String player : participant) temp.put(player, temp.getOrDefault(player, 0) + 1);
        for (String player : completion) temp.put(player, temp.get(player) - 1);
        
        for (String key : temp.keySet()) {
            if (temp.get(key) != 0){
                answer = key;
            }
        }
        return answer;
    }
}