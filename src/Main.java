import java.util.ArrayList;

public class Main {
    
    
    public static void main(String args[]){
        String[] name = {"석진", "우리", "현식", "희범","성우","밥"};
        int[][] friend = {{0,1},{0,2},{1,2},{1,3},{1,4},{2,3},{2,4},{3,4},{3,5},{4,5}};

        check( name, friend );
    }
    //

    private static void check(String[] name, int[][] friend) {
        // 사람 수와 친구 쌍이 주어졌을 때, 친구끼리 쌍을 짓는 경우의 수 도출
        // 2명을 짝을 짓고, 친구인지 확인, 친구인 경우 제거, 나머지로 반복한다. 전체를 다 돌았음에도 짝을 못짓는 경우는 불가능 하다.
        // 친구 여부를 확인하기 위한 2차원 boolean 배열 [0][1] = true일 시 둘은 친구이다. 
        boolean[][] isFriend = new boolean[name.length][name.length];
        for( int[] fi : friend ) {
            isFriend[fi[0]][fi[1]] = true;
            isFriend[fi[1]][fi[0]] = true;
        }
        
        // 종료 조건 친구 쌍을 다 찾았을 때 >> 남은 친구가 없을 때 사람수가 0일때.
        // return 친구 쌍 조합을 ArrayList
        // 확인 여부를 위한 캐쉬
        ArrayList<String> result = new ArrayList<>();
        boolean[] check = new boolean[name.length];
        StringBuilder temp = new StringBuilder();
        compair( name.length, result, check, temp, isFriend, name);
        System.out.println(result.size());
    }

    private static void compair( int length, ArrayList<String> result, boolean[] check, StringBuilder temp, boolean[][] isFriend, String[] name) {
        if( length == 0 ) {
            result.add(temp.toString());
            return;
        }
        int findIndex = 0;
        for( int index = 0; index < check.length; index++ ) {
            if( !check[index] ) {
                findIndex = index;
                break;
            }
        }
        for( int second = findIndex+1; second < name.length; second++  ) {
            if( !check[second] && isFriend[findIndex][second] ) {
                StringBuilder copy = new StringBuilder(temp.toString());
                check[findIndex] = true;
                check[second] = true;
                temp.append("( "+name[findIndex] + " ");
                temp.append(name[second] + " )");
                compair(length-2, result, check, temp, isFriend, name);
                check[findIndex] = false;
                check[second] =false;
                temp = copy;
            }
        }
    }
 }

