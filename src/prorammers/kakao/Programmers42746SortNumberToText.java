package prorammers.kakao;
import java.util.Arrays;
import java.util.Comparator;

// https://programmers.co.kr/learn/courses/30/lessons/42746
// 어려웠던 것은 로직을 어떻게 짜냐.
// 간단한 풀이 가능. 
// 6 , 10 이들어온 경우 결론적 핵심은 문제에 나와있듯  >> 이어붙여 가장 큰 정수
// 610 혹은 106 둘 중 더 큰 것 기준으로 정렬하면 된다.
// 이런 문제 풀이 방식 기억하기!!!!
// 추가로 0 0 0 0 인 경우 처리
class Programmers42746SortNumberToText {
    public String solution(int[] numbers) {
        String[] temp = new String[ numbers.length ];
        
        for (int index = 0; index < numbers.length; index++) {
            temp[index] = String.valueOf(numbers[index]);
        }
        
        Arrays.sort( temp, new Comparator<String>() {
            @Override
            public int compare( String target, String before ) {
                StringBuilder tg = new StringBuilder(target).append(before);
                StringBuilder bf = new StringBuilder(before).append(target);;
                return tg.toString().compareTo(bf.toString());
            }
        });
        
        if( temp[temp.length-1].equals("0")) return "0";
        
        StringBuilder tempBulder = new StringBuilder();
        for (int index = temp.length-1; index >=0 ; index--) {
            tempBulder.append(temp[index]);
        }
        
        return tempBulder.toString();
    }
    
    /*

    F int solution( 숫자의 배열 numbers ) 
        numbers배열을 String으로 변환하기 위한 temp 배열 String
        결과를 위한 SrgingBuilder 객체 answer
        
        FOR index는 0부터 numbers.legth-1만큼 1씩증가 
            String numbers[index]를 하나하나 형 변환하여 넣는다 
        
        Arrays.sort + new 컴페어 조합을 사용하여 문제 그대로 두 정수를 이어붙였을 때 큰 수를 위로 오도록 한다. new Compare
            F compare( 들어오는 수 target, 기존수 orignal )
                target + orignal을 비교하기 위한 StringBuilder변수 to = target+orignal
                orignal + target을 비교하기 위한 StringBuilder변수 or = orignal + target
                
                더 큰수가 배열 앞으로 와야 함으로 or가 to보다 클때를 1을 리턴해한다. or.compareTo(to)
        
        IF 0000처럼 모두가 0인 경우를 확인하기 위해 temp[0]이 0이면 THEN
            0을 리턴한다.
        
        FOR index는 0부터 temp.legth-1만큼 1씩 증가한다. 
            answer에 temp[index]를 이어붙인다.
        
        answer를 리턴한다.
    */
    class Solution {
        public String solution2(int[] numbers) {
          String[] temp = new String[numbers.length];
          StringBuilder ansewer = new StringBuilder();
          for (int index = 0; index < numbers.length; index++) {
              temp[index] = String.valueOf(numbers[index]);
          }
          
          Arrays.sort( temp, new Comparator<String>() {
            @Override
            public int compare(String target, String orignal) {
                StringBuilder to = new StringBuilder(target).append(orignal);
                StringBuilder ot = new StringBuilder(orignal).append(target);
                
                return ot.toString().compareTo(to.toString());
            }
          });
          
          if(temp[0].equals("0")) return "0";
          
          for (String string : temp) {
            ansewer.append(string);
        }
          return ansewer.toString();
        }
    }
}