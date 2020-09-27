public class Main {
    
    
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
        // 현재 기준점 기준 격채를 변경하면서 채운다.
        // 종료조건 , 지금 내가 4방향을 찾아야 하는 곳이 존재하지 않을 때.
        // return total 1개씩 더해가는 것
        System.out.println(check( image ));
    }
    
    private static int check( int[][] image ) {
        int findX = -1;
        int findY = -1;
        int result = 0;
        
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
        if( findY == -1 ) {
            return 1;
        }
        // 격자 덮기 시작 4방향 
        for( int loop = 0; loop < 4; loop++ ) {
            if( orignalRota(image, findX, findY, loop) ) {
                result += check(image);
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
            image[y][x+1] = 0;
            image[y+1][x+1] = 0;
        } else if( type == 2 ) {
            image[y][x] = 0;
            image[y+1][x] = 0;
            image[y+1][x+1] = 0;
        } else if( type == 3 ) {
            image[y][x] = 0;
            image[y+1][x] = 0;
            image[y+1][x-1] = 0;
        }
    }
    
    private static boolean orignalRota( int[][] image , int x, int y, int type ) {
        // 현재 기준으로 왼쪽과 위쪽은 다 채워져 있다고 가정. 현재 점 기준으로 ㄱ자 격자를 채울 수 있는 경우는 4가지, 비어있지 않다고 생각하면 경우의 수가 더 많음.
        if( ( x+1 ) == image[0].length || ( y+1 ) == image.length || x-1 < 0 ) return false;
        boolean result = false;
        if( type == 0 && image[y][x] == 0 && image[y][x+1] == 0 && image[y+1][x] == 0) {
            result = true;
            image[y][x] = 1;
            image[y][x+1] = 1;
            image[y+1][x] = 1;
        } else if(  type == 1 && image[y][x] == 0 && image[y][x+1] == 0 && image[y+1][x+1] == 0) {
          //ㄱ 자 1차 회전
            result = true;
            image[y][x] = 1;
            image[y][x+1] = 1;
            image[y+1][x+1] = 1;
        } else if(type == 2 &&  image[y][x] == 0 && image[y+1][x] == 0 && image[y+1][x+1] == 0 ) {
            result = true;
            image[y][x] = 1;
            image[y+1][x] = 1;
            image[y+1][x+1] = 1;
        } else if( type == 3 && image[y][x] == 0 && image[y+1][x] == 0 && image[y+1][x-1] == 0 ) {
          //ㄱ 자 3차 회전
            result = true;
            image[y][x] = 1;
            image[y+1][x] = 1;
            image[y+1][x-1] = 1;
        }
        
        return result;
    }
 }

