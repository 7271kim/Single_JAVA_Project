public class Main {
    
    private static int inputIndex = 0;
    
    public static void main(String args[]){
        quardTreeDraw("xxwwwbxwxwbbbwwxxxwwbbbwwwwbb", 16);
        
    }

    private static void quardTreeDraw( String input , int n) {
        String[][] xy = new String[n][n];
        decompress(0, 0, n, input, xy );
        String result = "";
        for( int yindex = 0; yindex < n; yindex++ ) {
            for( int xIndex = 0; xIndex< n; xIndex++ ) {
                result += xy[yindex][xIndex] + " ";
            }
            result +="\n";
        }
        System.out.println(result);
        
    }

    private static void decompress( int x, int y, int n, String input, String[][] xy) {
        char inputItem = input.charAt(inputIndex++);
        if( inputItem == 'w' ) {
            for( int yIndex = 0; yIndex < n; yIndex++ ) {
                for( int xIndex = 0; xIndex < n; xIndex++ ) {
                    xy[y + yIndex][ x + xIndex] = "-";
                }
            }
        } else if( inputItem == 'b' ) {
            for( int yIndex = 0; yIndex < n; yIndex++ ) {
                for( int xIndex = 0; xIndex < n; xIndex++ ) {
                    xy[y + yIndex][ x + xIndex] = "+";
                }
            }
        } else {
            int half = n/2;
            decompress( x, y, half, input, xy );// 좌상.
            decompress( x+half, y, half, input, xy );// 우상.
            decompress( x, y+half, half, input, xy );// 좌하.
            decompress( x+half, y+half, half, input, xy );// 우하.
        }
    }
 }

