import java.util.LinkedList;
import java.util.Queue;

public class Main {
    
    private static int inputIndex = 0;
    
    public static void main(String args[]){
        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = {7,4,5,6};
        
        System.out.println(Solution33.solution(bridge_length, weight, truck_weights));
        Solution33.solution(bridge_length, weight, truck_weights);
        
    }
 }

class Solution33 {
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int result = 0;
        int sum = 0;
        Queue<Integer> queue = new LinkedList<>();
        for( int truck : truck_weights ) {
            while( true ) {
                if( queue.isEmpty() ) {
                    queue.add(truck);
                    result++;
                    sum += truck;
                    break;
                } else if( queue.size() == bridge_length ) {
                    sum -= queue.poll();
                } else {
                    result++;
                    if( sum + truck <= weight ) {
                        queue.add(truck);
                        sum += truck;
                        break;
                    } else {
                        queue.add(0);
                    }
                }
            }
        }
        
        return result+bridge_length;
    }
}