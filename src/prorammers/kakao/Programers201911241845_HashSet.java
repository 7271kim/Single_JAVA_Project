package prorammers.kakao;
import java.util.HashSet;

//https://programmers.co.kr/learn/courses/30/lessons/1845

class Programers201911241845_HashSet {
    public int solution(int[] nums) {
        int answer = 0;
        int numsSize = nums.length;
        
        HashSet<Integer> datas = new HashSet<Integer>();
        for (int index = 0; index < numsSize; index++) {
            datas.add(nums[index]);
        }
        
        int n = datas.size();
        
        answer = n > numsSize/2 ? numsSize/2 : n;
        
        return answer;
    }
}