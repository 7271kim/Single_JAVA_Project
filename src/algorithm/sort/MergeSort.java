/*
정렬을 위한 원본과 같은 크기의 배열 준비 sorted[]
2개로 나누기 위한 함수 sortPartial(시작점,끝점) 생각
2개로 나누기 위한 기준점 mid를 정한다 ( mid = 시작점 + 끝점 / 2)
sortPartial( 시작점, mid ) , sortPartial( mid+1, 끝점 )을 통해 시작점 <끝점 일때까지 계속 반복한다.
최종 분할 후 sortMerge( leftStart, endPoint, rightEnd ) 함수를 통해 왼쪽과 오른쪽 분할을 정렬하며 합친다.
방법은 왼쪽은 leftStart부터 endPoint 인덱스 , 오른쪽은 endPoint+1부터 right까지 비교를 진행하는것
orignalleftStart < orignalendPoint+1 비교 후 작은것을 sorted 배열에 집어넣고 해당 인덱스를 증가 시간다
ex) 왼쪽이 작다면 sorted[특정인덱스] = 왼쪽값 , 왼쪽 인덱스 +1 증가, 왼쪽 혹은 오른쪽이 index를 벗어날때 까지 진행
왼쪽과 오른쪽 중 남은 것을 sorted에 이어 붙인다 (정렬된 데이터라 이어붙임 됨)
sorted를 orignal에 복사한다.*/

package algorithm.sort;
class MergeSort {
    private int[] data;
    private int[] forSort;
    private int pointer;
    private int size;
    
    public MergeSort(int size){
        this.data       = new int[size];
        this.forSort    = new int[size];
        this.pointer    = 0;
        this.size       = size;
    }
    
    public MergeSort(int[] data){
        this.data       = data;
        this.size       = data.length;
        this.forSort    = new int[size];
        this.pointer    = size;
    }
    
    public Boolean isFull () {
        return pointer == size;
    }
    
    public void inputData ( int inputData ) {
        if(isFull()) {
            throw new ArrayIndexOutOfBoundsException();
        }else{
            data[pointer++] = inputData;
        }
    }
    public void ascendingSrot() {
        sortPartial( 0, size - 1, 1 );
    }
    public void descendingSrot() {
        sortPartial( 0, size - 1, 0 );
    }
    
    public void sortPartial( int leftStart, int rightEnd, int position ) {
        // 절반씩 나누기
        if( leftStart < rightEnd ) {
            int mid = (rightEnd+leftStart)/2;
            sortPartial( leftStart, mid, position );
            sortPartial( mid+1, rightEnd, position );
            // 합치기
            // position : 1 오른차순정렬 
            // position : 0 내림차순정렬
            sortMerge( leftStart, mid, rightEnd, position );
        }
    }
    
    public void sortMerge( int leftStart, int endPoint, int rightEnd, int position ) {
        int rightStart         = endPoint+1; // 우측 시작
        Boolean leftFinsh      = leftStart > endPoint; // 왼쪽이 끝났는지 우측이 끝났는지 확인하기 위한 변수 
        Boolean rightFinsh     = rightStart > rightEnd; // 왼쪽이 끝났는지 우측이 끝났는지 확인하기 위한 변수
        int increseSortPointer = leftStart; // 최종 데이터 머지를 위해 필요한 변수 
        int forSortPointer     = leftStart; // 최종 데이터 머지를 위해 필요한 변수 
        
        
        // 왼쪽과 오른쪽을 비교하여 작은 것을 임시 sort데이터집합에 집어넣음
        while( !leftFinsh && !rightFinsh ) {
            Boolean positionCheck  = position == 1 ? data[leftStart] < data[rightStart] : data[leftStart] > data[rightStart];
            int compare = positionCheck ? data[leftStart++] : data[rightStart++];
            forSort[increseSortPointer++] = compare;
            if( leftStart > endPoint ) leftFinsh = true;
            if( rightStart > rightEnd ) rightFinsh = true;
        }
        
        // 왼쪽과 오른쪽 중 남은것을 이어붙임
        int addStartIndex = leftFinsh ? rightStart : leftStart;
        int addEndIndex   = leftFinsh ? rightEnd : endPoint;
        for (int index = addStartIndex; index <= addEndIndex; index++) {
            forSort[increseSortPointer++] = data[index];
        }
        
        // 정렬된 데이터를 최종 반영
        for (int index = forSortPointer; index < increseSortPointer; index++) {
            data[index] = forSort[index];
        }
    }
    
    public void print() {
        StringBuilder result = new StringBuilder();
        for (int i : data) {
            result.append(i + " ");
        }
        System.out.println(result.toString());
    }
}

