package prorammers.hard;
// https://programmers.co.kr/learn/courses/30/lessons/60062
// 어렵게 생각하지 말고 전수조사를 어떻게 할 것인가 이게 이슈
// for문 바깥에 무엇을 바깥에 둘 것인가.
// 모든 취약점의 Start에 대해 하나하나 전수조사
// 찾았다.. 내가 한 것은  전수조사가 아니엿음.........
// 내가 내 자체로 순서를 주고 있었음. 순서를 준다 >> 전수조사가 아니다.
//  int[] weak   = new int []{ 0 , 4 , 6, 10, 14} ;
//  int[] dist   = new int []{ 5, 2, 1  };
//  해당의 경우 10 ~ 14 5가 , 0은 1이 4 ~ 6은 2가 들어가야 하는데 내 자체가 순서를 10에 5를 줫음 그다음에는 꼭 2가 들어가게 세팅해놓음. 
class KAKAO60062Permutation {
    public int solution(int n, int[] weak, int[] dist) {
        int answer   = 9;
        int weakSize  = weak.length;
        int distSize  = dist.length;
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
            
            int totalSize = totalSize(distSize);
            
            Permutation per = new Permutation(totalSize, distSize, distSize);
            per.permutationDictionary(dist, 0, distSize, distSize);
            int[][] inputDistTotal = per.returnArr;
            
            for (int disIndex = 0; disIndex < inputDistTotal.length; disIndex++) {
                int checkWeak = 0;
                int[] check   = inputDistTotal[disIndex];
                
                for (int distIndex = 0; distIndex < check.length; distIndex++) {
                    int friend       = check[distIndex];
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
    
    public int totalSize( int input ) {
        if( input == 1 ) return 1;
        
        return input*totalSize(input-1);
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
    
    public class Permutation {
        private int[][] returnArr;
        private int totalCount;
        boolean[] visited;
        int[] output;
        
        public Permutation( int totalSize, int n, int r ) {
            this.returnArr = new int[totalSize][r];
            this.visited   = new boolean[n];
            this.output    = new int[n];
            totalSize = 0;
        }
        
        public void permutationDictionary(int[] input, int depth, int n, int r) {
            if(depth == r) {
                int[] temp = new int[r];
                for (int index = 0; index < r; index++) {
                    temp[index] = output[index];
                }
                returnArr[totalCount++] = temp;
            }
            for(int i=0; i<n; i++) {
                if(visited[i] != true) {
                    visited[i] = true; 
                    output[depth] = input[i]; 
                    permutationDictionary(input, depth + 1, n, r);
                    visited[i] = false;
                    output[depth] = 0; 
                }
            }
        }
    }
}