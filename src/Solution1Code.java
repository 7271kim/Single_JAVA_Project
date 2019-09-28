class Solution1Code{
    public int solution(int[] goods, int[] boxes){
        int answer = 0;
        goods = sort(goods);
        boxes = sort(boxes);
        int temp = 0;
        for (int index = 0; index < goods.length; index++) {
            int item = goods[index];
            while( temp < boxes.length-1 && boxes[temp] < item ) {
                temp++;
            }
            if( boxes[temp] >= item ) answer++;
        }
        return answer;
    }
    
    public int[] sort( int[] input ) {
        
        int temp;
        
        for (int index = 0; index < input.length-1; index++) {
            for (int indexInner = index+1; indexInner < input.length; indexInner++) {
                if( input[indexInner] < input[index] ) {
                    temp = input[indexInner];
                    input[indexInner] = input[index];
                    input[index] = temp;
                }
            }
        }
        
        return input;
    }
}