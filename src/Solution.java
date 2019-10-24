class Solution {
  public int[] solution(int n, long k) {
      int[] answer = new int[n];
      for (int index = 0; index < n; index++) {
          answer[index] = (int)Math.ceil(k/index+n);
      }
      return answer;
  }
}