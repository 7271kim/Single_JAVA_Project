public class Main {
    
    private static int inputIndex = 0;
    
    public static void main(String args[]){
        quardTreeRevers("xxwwwbxwxwbbbwwxxxwwbbbwwwwbb", 16);
        
    }

    private static void quardTreeRevers( String input , int n) {
        String result = revers( input );
        System.out.println(result);
        
    }

    private static String revers( String input ) {
        char inputItem = input.charAt(inputIndex++);
        if( inputItem == 'w' || inputItem == 'b' ) {
            return String.valueOf(inputItem);
        } else {
            String leftUp = revers(input);
            String rightUp = revers(input);
            String leftDown = revers(input);
            String rightDown = revers(input);
            
            return "x" + leftDown + rightDown + leftUp + rightUp;
        }
    }
 }

