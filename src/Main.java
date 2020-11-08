import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    
    public static void main(String args[]){
        System.out.println(fastSum(50));
        
    }

    private static int fastSum(int n) {
        if( n == 1 ) {
            return 1;
        } else if(n % 2 == 1) {
            return fastSum(n-1) + n;
        } else {
            return 2*fastSum(n/2) + (n*n)/4;
        }
        
    }
 }

