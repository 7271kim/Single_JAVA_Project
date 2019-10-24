package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/2751
 * 단순 정렬 X , 우선 Merge sort부터 구현해놓자.
 * 절반으로 나누기
 * 시간복잡도 O ( 2NlogN + 2N ) >> 14,000,000
 * 문제는 메모리를 엄청 쓰는거같음. >> 절대 sysout출력금지
 */

public class Beak2751SortMerge {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        StringBuilder resultString = new StringBuilder();
        try {
            //String[] firstLine = br.readLine().split(" ");
            //int total   = Integer.parseInt(firstLine[0]);
            //int compare = Integer.parseInt(firstLine[1]);
            //String[] secondeLine = br.readLine().split(" ");
            //int total = Integer.parseInt(br.readLine());
            //int total = sc.nextInt();
            int total = sc.nextInt();
            MergeSort sortData = new MergeSort(total);
            
            // N회 
            for (int index = 0; index < total; index++) {
                int number = sc.nextInt();
                sortData.inputData(number);
            }
            
            //2NlogN번
            sortData.sortUp();
            
            //N번
            sortData.print();
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
class MergeSort {
    private int[] data;
    private int[] forSort;
    private int pointer;
    private int size;
    
    public MergeSort(int size){
        this.data       = new int[size];
        this.forSort    = new int[size];
        this.pointer    = 0;
        this.size       = size;
    }
    
    public Boolean isFull () {
        return pointer == size;
    }
    
    public void inputData ( int inputData ) {
        if(isFull()) {
            throw new ArrayIndexOutOfBoundsException();
        }else{
            data[pointer++] = inputData;
        }
    }
    public void sortUp() {
        sortPartial( 0, size - 1, 0 );
    }
    
    //2NlogN번
    public void sortPartial( int leftStart, int rightEnd, int position ) {
        if( leftStart < rightEnd ) {
            int mid = (rightEnd+leftStart)/2;
            
            //logN번
            sortPartial( leftStart, mid, position );
            sortPartial( mid+1, rightEnd, position );
            
            //2N번
            sortMerge( leftStart, mid, rightEnd, position );
        }
    }
    
    public void sortMerge( int leftStart, int endPoint, int rightEnd, int position ) {
        int rightStart         = endPoint+1; 
        Boolean leftFinsh      = leftStart > endPoint;
        Boolean rightFinsh     = rightStart > rightEnd;
        int increseSortPointer = leftStart;
        int forSortPointer     = leftStart;
        
        //N번
        while( !leftFinsh && !rightFinsh ) {
            Boolean positionCheck  = position == 0 ? data[leftStart] < data[rightStart] : data[leftStart] > data[rightStart];
            int compare = positionCheck ? data[leftStart++] : data[rightStart++];
            forSort[increseSortPointer++] = compare;
            if( leftStart > endPoint ) leftFinsh = true;
            if( rightStart > rightEnd ) rightFinsh = true;
        }
        
        int addStartIndex = leftFinsh ? rightStart : leftStart;
        int addEndIndex   = leftFinsh ? rightEnd : endPoint;
        for (int index = addStartIndex; index <= addEndIndex; index++) {
            forSort[increseSortPointer++] = data[index];
        }
        
        //N번
        for (int index = forSortPointer; index < increseSortPointer; index++) {
            data[index] = forSort[index];
        }
    }
    
    public void print() {
        // 절대 sysout으로 쓰지 않기
        StringBuilder result = new StringBuilder();
        for (int i : data) {
            result.append(i+"\n");
        }
        System.out.println(result.toString());
    }
}

