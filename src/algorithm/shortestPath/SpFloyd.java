package algorithm.shortestPath;
/*
distance[A][B] = 비용 2차원 배열 준비 , 큰값으로 초기화 필요
distance[시작][끝] = Math.min(distance[시작][경유] + distance[경우][끝], distance[시작][끝]);
*/
public class SpFloyd {
    private int[][] distance; 
    private int size;
    private int max = 10000000;
    
    public SpFloyd( int nodeCount ) {
        size = nodeCount+1;
        distance = new int[nodeCount+1][nodeCount+1];
        for (int start = 1; start < size; start++) {
            for (int end = 1; end < size; end++) {
                if( start == end ) {
                    distance[start][end] = 0;
                } else {
                    distance[start][end] = max;
                }
            }
        }
    }
    
    public SpFloyd( int[][] distance ) {
        this.distance = distance;
    }
    
    
    public void push( int start, int end, int weight ) {
        distance[start][end] = weight;
    }
    
    public void makeFloyd() {
        for (int waypoint = 1; waypoint < size; waypoint++) {
            for (int start = 1; start < size; start++) {
                for (int end = 1; end < size; end++) {
                    distance[start][end] = Math.min(distance[start][waypoint] + distance[waypoint][end], distance[start][end]);
                }
            }
        }
    }
    
    public void print() {
        System.out.print("\t\t");
        for (int index = 1; index < size; index++) {
            System.out.print("Index : " + index+ "\t");
        }
        System.out.println();
        for (int start = 1; start < size; start++) {
            System.out.print("Index : " + start+ "\t");
            for (int end = 1; end < size; end++) {
                if( distance[start][end] == max) {
                    System.out.print("X" + "\t\t");
                } else {
                    System.out.print(distance[start][end] + "\t\t");
                }
            }
            System.out.println();
        }
    }
    
    public void minValue(int start , int end ) {
        int temp = distance[start][end];
        if( distance[start][end] == max  ) {
            System.out.println("갈수 있는 경로가 없습니다.");
        } else {
            System.out.println(temp);
        }
    }
}

