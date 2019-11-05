import algorithm.math.Permutation2;

public class Main {
    public static void main(String args[]){
        int[]input = {1,2,3,4};
        int r = 3;
        Permutation2 pe2 = new Permutation2(input, r);
        int[][] result = pe2.getReturnArr();
        for (int index = 0; index < result.length; index++) {
            for (int index2 = 0; index2 < result[index].length; index2++) {
                System.out.print(result[index][index2] + " ");
            }
            System.out.println();
        }
        
        System.out.println("새로시작");
        input = new int[]{1,2,3,4,5};
        pe2.newSetting(input, 5);
        result = pe2.getReturnArr();
        
        for (int index = 0; index < result.length; index++) {
            for (int index2 = 0; index2 < result[index].length; index2++) {
                System.out.print(result[index][index2] + " ");
            }
            System.out.println();
        }
        
    }
}