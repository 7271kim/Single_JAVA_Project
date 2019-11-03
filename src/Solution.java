// https://programmers.co.kr/learn/courses/30/lessons/60062
// 어렵게 생각하지 말고 전수조사를 어떻게 할 것인가 이게 이슈
// for문 바깥에 무엇을 바깥에 둘 것인가.
// 모든 취약점의 Start에 대해 하나하나 전수조사
class Solution {
    public int solution(int n, int[] weak, int[] dist) {
        int answer   = 9;
        int weakSize  = weak.length;
        QuickSort temp = new QuickSort(dist);
        temp.descendingSrot();
        dist = temp.data;
        
        //시작점을 바꿔가면서 하나하나 
        for (int index = 0; index < weakSize; index++) {
            
            // 시작지점 만들기
            int[] weakStart = new int[weakSize];
            for (int cont = 0; cont < weakSize; cont++) {
                boolean isOver = ( index+cont ) >= weakSize;
                if( isOver ) {
                    weakStart[cont] = weak[ ( index+cont ) % weakSize] + n;
                } else {
                    weakStart[cont] = weak[ ( index+cont ) % weakSize];
                }
            }
            
            //각각의 시작점에 대해 한명 한명 대입하여 최소 카운트 확인
            int checkWeak = 0;
            for (int distIndex = 0; distIndex < dist.length; distIndex++) {
                if( checkWeak >= weakSize ) {
                    int howMany = distIndex;
                    answer = answer > howMany ? howMany : answer;
                    break;
                }
                int friend       = dist[distIndex];
                int firstWeak   =  weakStart[checkWeak++];
                
                for (int checkIndex = checkWeak; checkIndex < weakStart.length;) {
                    int weakThis = weakStart[checkIndex];
                    if( weakThis - firstWeak <= friend ) {
                        checkIndex = ++checkWeak;
                    } else {
                        break;
                    }
                }
            }
        }
        
        if( answer == 9 ){
            if( weakSize <= dist.length ) {
                answer = weak.length;
            } else {
                answer = -1;
            }
        }
        
        return answer;
    }
    
    private class QuickSort {
        private int [] data; 
        private int size;
       
        private QuickSort( int[] data ){
            this.data = data;
            size      = data.length;
        }
        
        private void descendingSrot() {
            sort( 0, size-1, 0);
        }
        
        private void sort( int leftStart, int rightEnd , int position) {
            if( rightEnd < 0 || leftStart == rightEnd ) return;
            
            int tempLeft     = leftStart;
            int tempRight    = rightEnd;
            int pivot        = data[tempLeft];
            
            while( tempLeft < tempRight ) {
                int tempLeftValue = data[tempLeft];
                int tempRightValue  = data[tempRight];
                
                Boolean nextLeft   = position == 1 ? tempLeftValue <= pivot  : tempLeftValue >= pivot;
                Boolean nextRight  = position == 1 ? tempRightValue >= pivot  : tempRightValue <= pivot;
                
                while(tempLeft < rightEnd && nextLeft ) {
                    tempLeftValue = data[++tempLeft];
                    nextLeft  = position == 1 ? tempLeftValue <= pivot  : tempLeftValue >= pivot ;
                }
                
                while( tempRight > leftStart && nextRight ) {
                    tempRightValue = data[--tempRight];
                    nextRight  = position == 1 ? tempRightValue >= pivot  : tempRightValue <= pivot;
                }
                
                if( tempLeft < tempRight ) {
                    data[tempLeft++] = tempRightValue;
                    data[tempRight--]  = tempLeftValue;
                }
            }
            
            data[leftStart] = data[tempRight];
            data[tempRight]   = pivot;
            
            if( leftStart < tempRight-1 ) sort( leftStart, tempRight-1 , position);
            if( tempRight+1 < rightEnd ) sort( tempRight+1,rightEnd , position); 
        }
    }
}