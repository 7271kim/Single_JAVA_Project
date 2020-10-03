import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static List<ArrayList<Integer>> caseTest = new ArrayList<ArrayList<Integer>>();
    
    public static void main(String args[]){
        ArrayList<Integer> temp = new ArrayList<>( Arrays.asList(0,1,2) );
        caseTest.add(temp);
       
        temp = new ArrayList<>( Arrays.asList(3,7,9,11) );
        caseTest.add(temp);
        
        temp = new ArrayList<>( Arrays.asList(4,10,14,15) );
        caseTest.add(temp);
        
        temp = new ArrayList<>( Arrays.asList(0,4,5,6,7) );
        caseTest.add(temp);
        
        temp = new ArrayList<>( Arrays.asList(6,7,8,10,12) );
        caseTest.add(temp);
        
        temp = new ArrayList<>( Arrays.asList(0,2,14,15) );
        caseTest.add(temp);
        
        temp = new ArrayList<>( Arrays.asList(3,14,15) );
        caseTest.add(temp);
        
        temp = new ArrayList<>( Arrays.asList(4,5,7,14,15) );
        caseTest.add(temp);
        
        temp = new ArrayList<>( Arrays.asList(1,2,3,4,5) );
        caseTest.add(temp);
        
        temp = new ArrayList<>( Arrays.asList(3,4,5,9,13) );
        caseTest.add(temp);
        
        int[] test = { 12,9,3,12,6,6,9,3,12,9,12,9,12,12,6,6 };
        totalCase( test );
    }
    //

    private static void totalCase( int[] test ) {
        // 케이스 당 4번 , 0,1,2,3
        // 종료조건, 모두가 12시인가.
        // 종료조건, min보다 클때.
        // 종료 조건, 9개를 선택했을 때
        int min = 1048577;
        min = check( test, 0, 1048577, 0 );
        if( min == 1048577 ) {
            min = -1;
        }
    }
    
    private static int check( int[] test, int count, int min, int checkNextMin ) {
        boolean isFinish = true;
        for( int number : test ) {
            if( number != 12 ) {
                isFinish = false;
                break;
            }
        }
        if( isFinish ) {
            min = checkNextMin;
            return min;
        }
        
        if( checkNextMin > min || count == 9 ) {
            return min;
        }
        
        for( int index = 0; index < 4; index++ ) {
            rotate( test, count, index, 1 );
            count++;
            min = check(test, count, min, checkNextMin+index);
            count--;
            rotate( test, count, index, -1 );
        }
        
        return min;
        
    }

    private static void rotate( int[] test, int count, int rotateCount ,int flag ) {
        ArrayList<Integer> temp =  caseTest.get(count);
        if( flag == 1 ) {
            // 정방향 회전
            for( Integer item : temp ) {
                int clockTime = test[item];
                test[item] = (clockTime+3*rotateCount)%12 == 0 ? 12 : (clockTime+3*rotateCount)%12;
            }
        } else {
            // 역방향 회전
            for( Integer item : temp ) {
                int clockTime = test[item];
                test[item] = (clockTime-3*rotateCount) <= 0 ? 12 + (clockTime-3*rotateCount)  : (clockTime-3*rotateCount);
            }
        }
    }

 }

