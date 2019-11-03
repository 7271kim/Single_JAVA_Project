// https://programmers.co.kr/learn/courses/30/lessons/60062
// 어렵게 생각하지 말고 전수조사를 어떻게 할 것인가 이게 이슈
// for문 바깥에 무엇을 바깥에 둘 것인가.
// 모든 취약점의 Start에 대해 하나하나 전수조사
// 찾았다.. 내가 한 것은  전수조사가 아니엿음.........
// 내가 내 자체로 순서를 주고 있었음. 순서를 준다 >> 전수조사가 아니다.
//  int[] weak   = new int []{ 0 , 4 , 6, 10, 14} ;
//  int[] dist   = new int []{ 5, 2, 1  };
//  해당의 경우 10 ~ 14 5가 , 0은 1이 4 ~ 6은 2가 들어가야 하는데 내 자체가 순서를 10에 5를 줫음 그다음에는 꼭 2가 들어가게 세팅해놓음. 
class Solution {
    public int solution(int n, int[] weak, int[] dist) {
        int answer   = 9;
        int weakSize  = weak.length;
        IndexSort temp = new IndexSort(dist,101);
        temp.descendingSrot();
        dist = temp.orignal;
        
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
                int friend       = dist[distIndex];
                int firstWeak   =  weakStart[checkWeak++]; // 지금 시작점
                
                for (int checkIndex = checkWeak; checkIndex < weakStart.length;) {
                    int weakThis = weakStart[checkIndex];
                    int minus    = weakThis - firstWeak; 
                    if( minus <= friend ) {
                        checkIndex = ++checkWeak;
                    } else {
                        break;
                    }
                }
                
                if( checkWeak >= weakSize ) {
                    int howMany = distIndex+1;
                    answer = answer > howMany ? howMany : answer;
                    break;
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
    
    class IndexSort {
        private int [] data;
        private int [] orignal;
       
        public IndexSort( int[] orignal, int maxRange ){
            this.orignal     = orignal;
            data = new int[maxRange+1];
            for (int index = 0; index < orignal.length; index++) {
                this.data[orignal[index]] += 1;
            }
        }
        public void descendingSrot() {
            sortDec();
        }
        public void sortDec() {
            int count = 0;
            
            for (int index = data.length-1; index > 0; index--) {
                int thisNumber = data[index];
                if( thisNumber != 0) {
                    for (int temp = 0; temp < thisNumber; temp++) {
                        orignal[count++] = index;
                    }
                }
            }
        }
    }
}