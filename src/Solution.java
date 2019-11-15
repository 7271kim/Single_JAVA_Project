class Solution {
    public int solution(String name) {
        int answer = 0;
        int startIndex = 0;
        int endIndex   = 0;
        int nameSize   = name.length();
        // updown
        
        for (int index = 0; index < nameSize; index++) {
            int moving = name.charAt(index) - 'A';
            if( 26 - moving < moving ) moving = 26 - moving;
            if( moving != 0 ) {
                answer += moving;
                endIndex = index;
                if( startIndex == 0 && index != 0 ) {
                    startIndex = index;
                }
            }
        }
        answer += Math.min(endIndex,  nameSize - startIndex);
        
        return answer;
    }
}