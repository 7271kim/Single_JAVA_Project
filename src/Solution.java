import java.util.Arrays;
import java.util.Comparator;

// https://programmers.co.kr/learn/courses/30/lessons/42746
// 어려웠던 것은 로직을 어떻게 짜냐.
// 비교를 어떻게 하냐 이 이슈
// 결론은 다음 수의 인덱스를 보면서 0 ~ 끝까지 비교하며 확인하는 것이다.
// 앞자리가 같다면 다음 자리수를 비교해야 한다. 이전 수가 끝났다면 이전수의 마지막으로 비교 
// 그렇다면 앞자리는 남아있는데 다음 자리가 끝났다면?
// 45.5 소스
class Solution {
    public String solution(int[] numbers) {
        String[] temp = new String[ numbers.length ];
        
        for (int index = 0; index < numbers.length; index++) {
            temp[index] = String.valueOf(numbers[index]);
        }
        
        Arrays.sort( temp, new Comparator<String>() {
            @Override
            public int compare( String target, String before ) {
                int result = 0;
                int beforeSize = before.length();
                
                out : for (int beforeIndex = 0; beforeIndex < before.length(); beforeIndex++) {
                    for (int targetIndex = beforeIndex >= target.length() ? target.length()-1 : beforeIndex; targetIndex < target.length(); targetIndex++) {
                        char targetChar = target.charAt(targetIndex);
                        char beforeChar;
                        
                        if( beforeSize - 1 < targetIndex ) {
                            // 이전 길이가 더 작은 케이스
                            beforeChar = before.charAt( beforeSize - 1 );
                            beforeIndex = targetIndex;
                        } else if( beforeIndex > targetIndex ){
                            // 이전 길이 더 큰 케이스를 위해
                            // 338 33 같은 경우 338이 더 앞에 와야한다.
                            beforeChar = before.charAt(beforeIndex);
                        } else {
                            beforeChar = before.charAt(targetIndex);
                            beforeIndex = targetIndex;
                        }
                        
                        if( beforeChar > targetChar ) {
                            result = -1;
                            break out;
                        } else if( beforeChar < targetChar ) {
                            result = 1;
                            break out;
                        }
                        
                        
                    }
                }
                
                return result;
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