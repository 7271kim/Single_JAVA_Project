package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
 
public class Beak1966 {

    /*
     * https://www.acmicpc.net/problem/1966
     * 큐 - 메모리 초과이슈 .. 어딘지 못찾겠음 ㅠ
     * 다른 사람 코드참고  
     */
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int total = Integer.parseInt(reader.readLine());
        StringBuilder result = new StringBuilder();
        
        for( int index = 0 ; index<total; index++ ){
            LinkedList<Integer> queue = new LinkedList<>();
            String[] input = reader.readLine().split(" ");
            String[] priority = reader.readLine().split(" ");
            int findeNumber = Integer.parseInt(input[1]);
            int count = 0;
            
            for(int cycle=0 ; cycle<priority.length ; cycle++) {
                // 큐 추가
                queue.add(Integer.parseInt(priority[cycle]));
            }
            
            while(!queue.isEmpty()){
                boolean isPriority = true;
                
                for(int indexQue=1 ; indexQue < queue.size() ; indexQue++){ 
                    // 현재 맨 위 큐가 우선순위가 높은지 확인
                    if(queue.peek() < queue.get(indexQue)){
                        isPriority = false;
                        break;
                    }
                }
                
                if(isPriority){
                    // 맨위큐가 우선순위가 높을 시 
                    count++;
                    queue.poll(); // 제거 및 최종 +1
                    
                    if(findeNumber == 0) 
                        break; // 해당 찾는 번호라면 종료 
                }
                else { 
                    // 중요도가 떨어진다면 순회
                    queue.add(queue.poll());
                }
                findeNumber = (findeNumber==0) ? queue.size()-1 : --findeNumber;
            }
            result.append(count).append("\n");
        }
        System.out.println(result);
    }
}
