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
    public void sortUp() {
        sortPartial( 0, size - 1, 0 );
    }
    public void sortDown() {
        sortPartial( 0, size - 1, 1 );
    }
    
    public void sortPartial( int leftStart, int rightEnd, int position ) {
        // 절반씩 나누기
        if( leftStart < rightEnd ) {
            int mid = (rightEnd+leftStart)/2;
            sortPartial( leftStart, mid, position );
            sortPartial( mid+1, rightEnd, position );
            // 합치기
            // position : 0 오른차순정렬 
            // position : 1 내림차순정렬
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
            Boolean positionCheck  = position == 0 ? data[leftStart] < data[rightStart] : data[leftStart] > data[rightStart];
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
        // 절대 sysout으로 쓰지 않기
        StringBuilder result = new StringBuilder();
        for (int i : data) {
            result.append(i+"\n");
        }
        System.out.println(result.toString());
    }
}

