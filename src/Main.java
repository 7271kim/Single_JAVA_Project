import alorithm.dataStructureLow.MyTtie;

public class Main {
    public static void main(String args[]){
        Solution temp = new Solution();
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
        temp.solution(words, queries);
        
        MyTtie tttt = new MyTtie();
        tttt.insert("apple");
        tttt.insert("air");
        tttt.insert("abc");
        tttt.insert("app");
        
        System.out.println(tttt.search("aa"));
        System.out.println(tttt.search("a"));
        System.out.println(tttt.search("air"));
        System.out.println(tttt.search("airc"));
    }
}

