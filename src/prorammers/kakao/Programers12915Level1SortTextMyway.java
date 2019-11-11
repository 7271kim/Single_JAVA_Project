package prorammers.kakao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

// https://programmers.co.kr/learn/courses/30/lessons/12915
// 문자열 원하는 인덱스로 적용
class Programers12915Level1SortTextMyway {

    // 그냥 더 좋은 방법이라 이거 사용
    // 배열 안에 ArrayList 넣기
    public String[] solution(String[] strings, int n) {
        String[] answer = new String[strings.length];
        
        ArrayList<String>[] sort = new ArrayList[26];
        for (int index = 0; index < 26; index++) {
            sort[index] = new ArrayList<String>();
        }
        
        for (int index = 0; index < strings.length; index++) {
            String text     = strings[index];
            int indexText   = text.charAt(n) - 'a';
            sort[indexText].add(text);
        }
        
        int count = 0;
        for (int index = 0; index < sort.length; index++) {
            if( !sort[index].isEmpty() ) {
                ArrayList<String> temp = sort[index];
                Collections.sort(temp);
                for (int indexTemp = 0; indexTemp < temp.size(); indexTemp++) {
                    answer[count++] = temp.get(indexTemp);
                }
            }
        }
        
        
        return answer;
    }
    
    // 특정 문자열 정렬 후 나머지느 그냥 text정렬일 경우
    // 일반 노드 이용 정렬하기
    public String[] solution2(String[] strings, int n) {
      String[] answer = {};
      ArrayList<NodeTemp> temp = new ArrayList<NodeTemp>();
      for (String text : strings) {
        temp.add(new NodeTemp(text, n));
      }
      Collections.sort(temp);
      
      answer = new String[strings.length];
      for (int index = 0; index < temp.size(); index++) {
          answer[index] = temp.get(index).text;
      }
      
      return answer;
  }
    public String[] solution3(String[] strings, int n) {
        String[] answer   = strings;
        
        Arrays.sort(answer, new Comparator<String>() {
            @Override
            public int compare(String before, String target) {
                if(before.charAt(n) > target.charAt(n)) return 1;
                else if(before.charAt(n) == target.charAt(n)) return before.compareTo(target);
                else if(before.charAt(n) < target.charAt(n)) return -1;
                else return 0;
            }
        });
        
        return answer;
    }
    
    
  private class NodeTemp implements Comparable<NodeTemp>{
      String text;
      int index;
      
      private NodeTemp( String text , int index ) {
          this.text  = text;
          this.index = index;
      }
      
      // 
      @Override
      public int compareTo( NodeTemp target) {
            char thisChar   = text.charAt(index);
            char targetChar = target.text.charAt(target.index);
            int result = 0;
            if( thisChar > targetChar ) {
                return 1;
            } else if( thisChar < targetChar ) {
                result = -1;
            } else if( thisChar == targetChar ) {
                // 같을 경우 그냥  a b c 정렬 하고 싶은 경우
                result = text.compareTo(target.text);
            }
            
            return result;
      }
  }
  // 남 풀이
  public String[] solution4(String[] strings, int n) {
      String[] answer = {};
      ArrayList<String> arr = new ArrayList<>();
      for (int i = 0; i < strings.length; i++) {
          arr.add("" + strings[i].charAt(n) + strings[i]);
      }
      Collections.sort(arr);
      answer = new String[arr.size()];
      for (int i = 0; i < arr.size(); i++) {
          answer[i] = arr.get(i).substring(1, arr.get(i).length());
      }
      return answer;
  }
  
}