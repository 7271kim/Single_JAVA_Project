// 트리의 탐색에 중점을 줌
// 트리는 root left right로 구성되므로 각각 노드에서 최대값을 구하기
class Solution2 {
    public int solution(Tree T) {
        // 중복 여부 판별을 위한 컨테이너 , 들어올수 있는 범위의 최대치로 설정
        // 1 이상일 일 시 중복됨
        int[] containNumber = new int[50001];
        int maxCount = 0;
        
        maxCount = check( T, maxCount , containNumber );
        
        return maxCount;
    }
    
    public int check( Tree T, int maxCount, int[]containNumber ) {
        int root = T.x;
        int leftMax  = 0;
        int rightMax = 0;
        // containNumber[root] += 1; 이것을 밖에 빼지 않은 이유는 맨 처음 root 접근 시. 아래 if 조건절을 항상 만존된 상태임
        if( containNumber[root] > 0 ) {
            containNumber[root] += 1;
            return maxCount;
        }
        
        // containNumber의 반복 여부를 위해  +1 증가 시킴 1 이상일시 중복됨을 의미
        containNumber[root] += 1;
        // 중복되지 않기 때문에 max값 증가시킴
        maxCount++;
        
        if(T.l != null) {
            // 왼쪽 노드의 max값 확인 
            leftMax = check( T.l, maxCount, containNumber );
            // root와 왼쪽 노드 탐색이 끝날 시 왼쪽을 초기화 시켜줘야 한다. 만약 root = 1 left = 3 right =3 인 경우 왼쪽값의 흔적을 컨테이너에 남겨놓을 경우 우측 탐색이 진행되지 않음
            containNumber[T.l.x] -= 1;
        }
        
        if(T.r != null) {
            rightMax = check( T.r, maxCount, containNumber );
         // root와 우측 노드 탐색이 끝날 시 우측을 초기화 시켜줘야 한다. 현재를 벗어난 시점에서 이 다음 left에 영향을 미치기 때문
            containNumber[T.r.x] -= 1;
        }
        
        // 왼쪽과 우측의 max비교
        maxCount = maxCount < leftMax ? leftMax : maxCount;
        maxCount = maxCount < rightMax ? rightMax : maxCount;
        
        return maxCount;
    }
    
}
