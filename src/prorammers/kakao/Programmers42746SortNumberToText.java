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
}