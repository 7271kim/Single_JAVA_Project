public class Main {
    public static void main(String args[]){
        Solution temp = new Solution();
        Solution3 temp3 = new Solution3();
        String[] participant   = {"mislav", "stanko", "mislav", "ana"};
        String[] completion = {"stanko", "ana", "mislav"};
        System.out.println(temp.solution(participant, completion));
    }
}
