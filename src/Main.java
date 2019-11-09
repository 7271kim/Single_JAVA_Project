public class Main {
    public static void main(String args[]){
        Solution temp = new Solution();
        Solution3 temp3 = new Solution3();
        String[] user_id   = {"aabc","adbc","cdcd","aaa","avbc"};
        String[] banned_id = {"****"};
        System.out.println(temp3.solution(user_id, banned_id));
    }
}
