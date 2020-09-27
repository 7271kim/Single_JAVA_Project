public class Main {
    
    private static final int coverType[][][] = { { {0, 0}, {1, 0}, {0, 1} },{ {0, 0}, {0, 1}, {1, 1} },{ {0, 0}, {1, 0}, {1, 1} },{ {0, 0}, {1, 0}, {1, -1} }};
    
    public static void main(String args[]){
         int[][] image = {
                            {1,1,1,1,1,1,1,1,1,1},
                            {1,0,0,0,0,0,0,0,0,1},
                            {1,0,0,0,0,0,0,0,0,1},
                            {1,0,0,0,0,0,0,0,0,1},
                            {1,0,0,0,0,0,0,0,0,1},
                            {1,0,0,0,0,0,0,0,0,1},
                            {1,0,0,0,0,0,0,0,0,1},
                            {1,1,1,1,1,1,1,1,1,1}
                       };
        totalCase( image );
    }
    //

    private static void totalCase( int[][] image ) {
        // 4 방향 회전
        // 종료조건 , 지금 내가 4방향을 찾아야 하는 곳이 존재하지 않을 때.
        // return total 1개씩 더해가는 것
        System.out.println(check( image, 0 ));
    }
    
    private static int check( int[][] image, int result ) {
        int findX = -1;
        int findY = -1;
        
        out : for( int y = 0; y < image.length; y++ ) {
            for( int x = 0; x < image[0].length; x++  ) {
                if( image[y][x] == 0 ) {
                    findY = y;
                    findX = x;
                    break out;
                }
            }
        }
        // 종료조건 : 찾을 곳이 없을 때,
        if( findY == -1 ) return 1;
        // 격자 덮기 시작 4방향 
        for( int loop = 0; loop < 4; loop++ ) {
            if( orignalRota(image, findX, findY, loop) ) {
                int tempResult = result;
                result += check(image ,tempResult);
                returnRota(image, findX, findY, loop);
                
            }
        }
        
        return result;
    }
    
    // 복구 
    private static void returnRota( int[][] image, int x, int y, int type ) {
        if( type == 0 ) {
            image[y][x] = 0;
            image[y][x+1] = 0;
            image[y+1][x] = 0;
        } else if( type == 1 ) {
            image[y][x] = 0;
            image[y+1][x+1] = 0;
            image[y+1][x] = 0;
        } else if( type == 2 ) {
            image[y][x] = 0;
            image[y][x+1] = 0;
            image[y+1][x+1] = 0;
        } else if( type == 3 ) {
            image[y][x] = 0;
            image[y+1][x] = 0;
            image[y+1][x-1] = 0;
        }
    }
    
    private static boolean orignalRota( int[][] image , int x, int y, int type ) {
     // 범위 벗어날 시 불필요
        if( ( x+1 ) == image[0].length || ( y+1 ) == image.length || ( x -1 ) < 0 ) return false;
        boolean result = false;
        if( type == 0 && image[y][x] != 1 && image[y][x+1] != 1 && image[y+1][x] != 1) {
            result = true;
            image[y][x] = 1;
            image[y][x+1] = 1;
            image[y+1][x] = 1;
        } else if(  type == 1 && image[y][x] != 1 && image[y+1][x+1] != 1 && image[y+1][x] != 1) {
            result = true;
            image[y][x] = 1;
            image[y+1][x+1] = 1;
            image[y+1][x] = 1;
        } else if(type == 2 &&  image[y][x] != 1 && image[y][x+1] != 1 && image[y+1][x+1] != 1  ) {
          //ㄱ 자 2차 회전
            result = true;
            image[y][x] = 1;
            image[y][x+1] = 1;
            image[y+1][x+1] = 1;
        } else if( type == 3 && image[y][x] != 1 && image[y+1][x] != 1 && image[y+1][x-1] != 1  ) {
          //ㄱ 자 3차 회전
            result = true;
            image[y][x] = 1;
            image[y+1][x] = 1;
            image[y+1][x-1] = 1;
        }
        
        return result;
    }
 }

