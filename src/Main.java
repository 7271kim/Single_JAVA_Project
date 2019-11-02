import alorithm.dataStructureLow.MyTtrie;

public class Main {
    public static void main(String args[]){
        MyTtrie temp  = new MyTtrie();
        temp.insert("appc");
        temp.insert("appd");
        temp.insert("abc");
        
        System.out.println(temp.search("abc"));
    }
}

