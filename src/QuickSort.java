/* 
 - input배열 준비
 - index의 첫번째 원소를 pivot으로 한다. 피봇보다 작은수를 왼쪽으로 피봇보다 큰수를 오른쪽으로 만들기
 - pivot을 기준으로 low왼쪽에서 오른쪽으로 가면서 pivot보다 큰 수를 찾고, hight는 오른쪽 끝에서 왼쪽으로 가면서 pivot보다 작은 수를 찾는다.
 - 두 수를 교환한다.
 - 반복하면서 low와 height가 교체하는 순간 로직을 멈춘다.
 - pivot을 중앙으로 둔다.
 - 피봇값을 제외한 sort( int leftStart, int rightEnd)을 통해 해당을 반복한다.
 */

class QuickSort {
    int [] data; 
    int size;
   
    QuickSort( int[] data ){
        this.data = data;
        size      = data.length;
    }
    // 오름차순 정렬 1 2 3 4 5
    public void ascendingSrot() {
        sort( 0, size-1, 1);
    }
    
    public void descendingSrot() {
        sort( 0, size-1, 0);
    }
    
    // 1이면 오름차순 , 0이면 내림차순
    public void sort( int leftStart, int rightEnd , int position) {
        //크기가 0이거나 1개일때 필요없음
        if( rightEnd < 0 || leftStart == rightEnd ) return;
        
        int tempLeft     = leftStart;
        int tempRight    = rightEnd;
        int pivot        = data[tempLeft];
        
        while( tempLeft < tempRight ) {
            int tempLeftValue = data[tempLeft];
            int tempRightValue  = data[tempRight];
            
            Boolean nextLeft   = position == 1 ? tempLeftValue <= pivot  : tempLeftValue >= pivot;
            Boolean nextRight  = position == 1 ? tempRightValue >= pivot  : tempRightValue <= pivot;
            
            //피봇보다 큰수찾기
            while(tempLeft < rightEnd && nextLeft ) {
                tempLeftValue = data[++tempLeft];
                nextLeft  = position == 1 ? tempLeftValue <= pivot  : tempLeftValue >= pivot ;
            }
            
            //피봇보다 작은수 찾기
            while( tempRight > leftStart && nextRight ) {
                tempRightValue = data[--tempRight];
                nextRight  = position == 1 ? tempRightValue >= pivot  : tempRightValue <= pivot;
            }
            
            // 데이터 교체
            if( tempLeft < tempRight ) {
                data[tempLeft++] = tempRightValue;
                data[tempRight--]  = tempLeftValue;
            }
        }
        
        // 피봇 가운데로 보내기 
        data[leftStart] = data[tempRight];
        data[tempRight]   = pivot;
        
        // pivot을 뺀 좌 우 분할 반복
        if( leftStart < tempRight-1 ) sort( leftStart, tempRight-1 , position);
        if( tempRight+1 < rightEnd ) sort( tempRight+1,rightEnd , position); 
    }
    
    public void print() {
        StringBuilder result = new StringBuilder();
        for (int i : data) {
            result.append(i + " ");
        }
        System.out.println(result.toString());
    }
}