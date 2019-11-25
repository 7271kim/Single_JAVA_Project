package prorammers.kakao;
// https://programmers.co.kr/learn/courses/30/lessons/1829
// DFS 어떻게 했는지 꼭.... 꼭 다시 이해.
// 내 로직은 마지막은 1을 가지고 하나하나 위로 올려주는 방식으로 작업했다!!!
// DFS 잘 안되는데 내 방법 꼭 잘보기
//하나하나 출발점 , 그리고 그 출발점에서 해야하는 로직을 회귀로 반복을 제거하기 위한 checked로직
class Programmers1829DFS {
  
    private int[][] checked;
    private int mSize;
    private int nSize;
  
  public int[] solution(int m, int n, int[][] picture) {
      int[] answer      = new int[2];
      mSize = m;
      nSize = n;
      checked   = new int[m][n];
      int max = 0;
      
      for (int indexM = 0; indexM < m; indexM++) {
        for (int indexN = 0; indexN < n; indexN++) {
            if( checked[indexM][indexN] == 0 && picture[indexM][indexN] != 0) {
                int checkNum = picture[indexM][indexN];
                int temp = getData( picture, indexM, indexN, checkNum );
                if( temp != 0 ) {
                    answer[0] += 1;
                    max = temp > max ? temp : max;
                }
                
            }
         }
      }
      
      answer[1] = max;
      
      return answer;
  }
  
  public int getData ( int[][] picture, int m, int n, int checkNum ) {
      
      // 다른 케이스는 무시
      if( checkNum != picture[m][n] ) return 0;
          
      checked[m][n] = 1;
      int reusult = 1;
      
      // 우측 확인 
      if( n + 1 < nSize && checked[m][n+1] == 0 ) reusult+= getData(picture, m, n+1, checkNum);
      // 아래 확인
      if( m + 1 < mSize && checked[m+1][n] == 0 ) reusult+=getData(picture, m+1, n, checkNum);
      // 위 확인
      if( m - 1 > -1 && checked[m-1][n] == 0 ) reusult+=getData(picture, m-1, n, checkNum);
      // 좌측확인
      if( n - 1 > -1 && checked[m][n-1] == 0 ) reusult+=getData(picture, m, n-1, checkNum);
      
      return reusult;
  }
}