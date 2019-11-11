package algorithm.dataStructure;

import java.util.ArrayList;

class DigitGetNew {
    
    // 원하는 자리수로 자름 100 이면 1,0,0 을 가지고 옴
    public int[] getDigit( int input ) {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        int digit = input;
        while( digit!= 0 ) {
            temp.add( digit%10 );
            digit /= 10;
        }
        
        int[] result = new int[temp.size()];
        int count = 0;
        for (int index = temp.size()-1; index >= 0; index--) {
            result[count++] = temp.get(index);
        }
        
        return result;
    }
}