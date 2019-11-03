class Solution3 {
    public int[][] solution(int n, int[][] build_frame) {
        n +=1;
        int count = 0;
        int [][][]building = new int[n][n][2];
        
        for(int c=0; c<build_frame.length; c++) {
            int [] command = build_frame[c];
            int i = command[1];
            int j = command[0];
            int a = command[2];
            int b = command[3];

            if(a == 0) { // 기둥
                if(b==0) {

                    if(can_destroy0(i, j, building, n)){
                        building[i][j][0] = 0;// 기둥
                        count--;
                    }

                }else if(b==1){
                    if(check0isOk(i, j, building)) {
                        building[i][j][0] = 1;// 기둥
                        count++;
                    }
                }
            }else if(a == 1) {//보
                if(b==0) {
                    if(can_destroy1(i, j, building, n)){
                        building[i][j][1] = 0; //보
                        count--;
                    }
                }else if(b==1){
                    if(check1isOk(i, j, building, n)) {
                        building[i][j][1] = 1; //보
                        count++;
                    }
                }
            }

        }

        int [][]newbuildings = print(building, n, count);
        //System.out.print("test");
        return newbuildings;

    }

    public static int [][] print(int [][][]buildings, int n, int count) {
        int [][]newbuildings = new int[count][3];
        int b = 0;
        for(int j=0; j<n; j++) {
            for(int i=0; i<n; i++) {
                if(buildings[i][j][0] == 1) {
                    newbuildings[b][0] = j;
                    newbuildings[b][1] = i;
                    newbuildings[b++][2] = 0;

                }
                if(buildings[i][j][1] == 1) {
                    newbuildings[b][0] = j;
                    newbuildings[b][1] = i;
                    newbuildings[b++][2] = 1;
                }
            }
        }
        return newbuildings;
    }



    public static boolean can_destroy0(int i, int j, int [][][]buildings, int n){
        //기둥삭제
        buildings[i][j][0]=0;

        for(int si=0; si<n; si++) {
            for(int sj=0; sj<n; sj++) {

                if(buildings[si][sj][0] == 1)
                    if(!check0isOk(si, sj, buildings)) {
                        buildings[i][j][0]=1;
                        return false;
                    }
                if(buildings[si][sj][1] == 1)
                    if(!check1isOk(si, sj, buildings, n)) {
                        buildings[i][j][0]=1;
                        return false;
                    }
            }
        }
        return true;
    }

    public static boolean can_destroy1(int i, int j, int [][][]buildings, int n){

        //보삭제
        buildings[i][j][1]=0;

        for(int si=0; si<n; si++) {
            for(int sj=0; sj<n; sj++) {
                if(buildings[si][sj][0] == 1)
                    if(!check0isOk(si, sj, buildings)) {
                        buildings[i][j][1]=1;
                        return false;
                    }
                if(buildings[si][sj][1] == 1)
                    if(!check1isOk(si, sj, buildings, n)) {
                        buildings[i][j][1]=1;
                        return false;
                    }
            }
        }
        return true;
    }

    public static boolean check0isOk(int i, int j, int [][][]buildings){
        //기둥
        //바닥에 있는경우
        if(i ==0) {
            return true;
        }else {
            //다른 기둥위에 있는경우
            if(i-1 >= 0 &&  buildings[i-1][j][0] == 1) {//기둥
                return true;
            } 

            if(buildings[i][j][1] == 1) {//보의 한쪽끝에 있는경우
                //보의 바로위
                return true;
            }
            if(j-1 >= 0 && buildings[i][j-1][1] == 1) {//보의 한쪽끝에 있는경우
                //보의 오른쪽끝
                return true;
            }
        }
        return false;
    }


    public static boolean check1isOk(int i, int j, int [][][]buildings, int n){


        //보
        if(i-1 >= 0 && buildings[i-1][j][0] == 1) {//한쪽 끝이 기둥위에 있다.
            return true;
        }else if(j+1 < n && i-1 >=0 && buildings[i-1][j+1][0] == 1) {//한쪽 끝이 기둥위에 있다.
            return true;
        }else {
            //양쪽 끝부분이 다른 보와 동시에 연결
            boolean left = false;
            boolean right = false;

            if(j-1 >= 0 && buildings[i][j-1][1] == 1) {
                left = true;
            }
            if(j+1 < n && buildings[i][j+1][1] == 1) {
                right = true;
            }

            if(left && right)
                return true;
            else 
                return false;
        }

    }
}