class Solution3Code{
    public int solution(int[] sticker) {
        int answer = 180;
        answer = max(sticker);
        return answer;
    }
    
    public int max( int[] input ) {
        int max=0;
        if( input.length == 2 ) {
            max = input[0] > input[1] ? input[0] : input[1];
            return max;
        } else if( input.length == 1) {
            return input[0];
        } else if( input.length == 0 ){
            return 0;
        }
        
        int[] firstSelection = new int[input.length-2];
        int[] secondSelection = new int[input.length-3];
        for (int index = 2; index < input.length; index++) {
            firstSelection[index-2] = input[index];
            if( index > 2 ) {
                secondSelection[index-3] = input[index];
            }
        }
        int firstMax  = input[0]  + max(firstSelection);
        int secondMax = input[1] +  max(secondSelection);
        
        max = firstMax > secondMax ? firstMax : secondMax;
        
        return max;
    }
}