import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import alorithm.dataStructureLow.DoubleLinkedList;
import alorithm.dataStructureLow.GraphArrayList;
import alorithm.dataStructureLow.GraphMatrix;
import alorithm.dataStructureLow.MaxHeap;
import alorithm.dataStructureLow.TreeWithArray;
import alorithm.dataStructureLow.TreeWithLinkedList;
import sun.tools.jconsole.MaximizableInternalFrame;

public class Main {
    
    
    public static void main(String args[]){
        String[][] word = {
                            {"N","N","N","N","S"},
                            {"N","E","E","E","N"},
                            {"N","E","Y","E","N"},
                            {"N","E","E","E","N"},
                            {"N","N","N","N","N"}
                        };

        System.out.println(findWord( word, "NNNNEEENNNNNNNNNNNS" ));
    }

    private static boolean findWord(String[][] word, String findText) {
        //1. 종료 조건 현재 위치가 마지막이고, 마지막 단어와 매칭 여부
        //2. return 단어 존재 여부 boolean
        //3. 한번 온 곳은 다시 가면 안됨.
        boolean result = false;
        boolean[][] checking = new boolean[word.length][word.length];
        
        out : for (int yIndex = 0; yIndex < word.length; yIndex++) {
            for (int xIndex = 0; xIndex < word.length; xIndex++) {
                if( result ) {
                    break out;
                } else {
                    result = checking( word, findText, 0, yIndex, xIndex , checking);
                }
            }
        }
        
        
        return result;
    }

    private static boolean checking(String[][] word, String findText, int checkingIndex, int yIndex, int xIndex, boolean[][] checking) {
        boolean result = false;
        int[] yCheck = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] xCheck = {-1, 0, 1, -1, 1, -1, 0 ,1};
        
        if( yIndex == word.length || yIndex < 0)  return false;
        if( yIndex == word.length || yIndex < 0) return false;
        if( xIndex == word.length || xIndex < 0) return false;
        if( checking[yIndex][xIndex] ) return false;
        
        String checkText = findText.substring(checkingIndex, checkingIndex+1);
        String wordText = word[yIndex][xIndex];
        
        if( !checkText.equals(wordText) ) {
            return false;
        }
        
        if( findText.length()-1 == checkingIndex ) {
           return checkText.equals(wordText);
        }
        
        // 8방면 검사 
        for (int cIndex = 0; cIndex < 8; cIndex++) {
            checking[yIndex][xIndex] = true;
            result = checking(word, findText, checkingIndex+1, yIndex+yCheck[cIndex], xIndex+xCheck[cIndex], checking);
            checking[yIndex][xIndex] = false;
            if( result ) {
                return true;
             }
        }
        
        return  result;
    }
}

