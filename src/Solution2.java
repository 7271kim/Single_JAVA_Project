class Solution2 {
    public int[] solution(String s) {
        int[] answer = {};
        s = s.replace("{{", "").replace("}}", "");
        String[] split = s.split("\\},\\{");
        String Index[] = new String [split.length+1];
        for (int index = 0; index < split.length; index++) {
            String temp = split[index];
            int size = temp.split(",").length;
            Index[size] = temp;
        }
        
        StringBuilder result = new StringBuilder(); 
        
        for (int index = 0; index < Index.length; index++) {
            String thisText = Index[index];
            if( thisText != null ) {
                String[] temp = thisText.split(",");
                
                if( index == 1 ) {
                    result.append(temp[0]+ " ");
                    continue;
                }
                
                String[] before = Index[index-1].split(",");
                
                out : for (int indexTemp = 0; indexTemp < temp.length; indexTemp++) {
                    String isNew = temp[indexTemp];
                     for (int indexBefore = 0; indexBefore < before.length; indexBefore++) {
                        String beforeText = before[indexBefore]; 
                        if( isNew.equals(beforeText)) continue out;
                    }
                     result.append(isNew+" ");
                     break;
                }
            }
        }
        
        String[] lastText = result.toString().split(" ");
        answer = new int[lastText.length];
        for (int index = 0; index < lastText.length; index++) {
            answer[index] = Integer.valueOf(lastText[index]);
        }
        
        return answer;
    }
}