public class Main {
    public static void main(String args[]){
        Solution my = new Solution();
        int n        = 12;
        int[] weak;
        int[] dist;
        weak   =  new int []{ 1, 5, 6, 10 } ;
        dist   =  new int []{ 1, 2, 3, 4 };
        
        System.out.println(my.solution(n, weak, dist));
        
        weak   = new int []{ 1, 3, 4, 9, 10 } ;
        dist   = new int []{ 3, 5, 7  };
        
        System.out.println(my.solution(n, weak, dist));
        
        weak   = new int []{ 1, 3, 4, 9, 10 } ;
        dist   = new int []{ 1, 1, 1  };
        
        System.out.println(my.solution(n, weak, dist));
        
        weak   = new int []{ 1, 3, 5, 9, 11 } ;
        dist   = new int []{ 1, 1, 1, 1, 1  };
        
        System.out.println(my.solution(n, weak, dist));
        
        
        weak   = new int []{} ;
        dist   = new int []{ 1, 1, 1, 1, 1  };
        
        System.out.println(my.solution(n, weak, dist));
        
    }
}

