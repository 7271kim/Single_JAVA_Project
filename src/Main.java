public class Main {
    public static void main(String args[]){
        Solution temp = new Solution();
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
        //1
        System.out.println( temp.solution("(()())()"));
        //2
        System.out.println( temp.solution(")("));
        //3
        System.out.println( temp.solution("()))((()"));
        //System.out.println( temp.solution(")(()"));
        //System.out.println( temp.solution(")(())())(("));
        //System.out.println( temp.solution("(()())()"));
        //System.out.println( temp.solution("()))((()"));
        //System.out.println( temp.solution("((()))"));
        //System.out.println( temp.solution("xababcdcdababcdcd"));
    }
}



