package LottoGet;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class SettingsDB {
    
    public static void main(String[] args) throws Exception {
        LottoDB lottoDB = new LottoDB();
       
        allUpdate(lottoDB, 1); // 회차별 로또번호 업데이트
        //subtotal(lottoDB, 87); // 1 ~ 87 - 끝까지 전체 번호별 당첨 횟수 업데이트
        //subtotalOne(lottoDB); // 최근 1개 누적값 업데이트
        
        
        /*
        CREATE TABLE `lotto_number_total` (
                `NUMBER` int(11),
                `NUMBER_TWO` int(11),
                `VALUE` int(11),
                PRIMARY KEY (`NUMBER`,`NUMBER_TWO`),
                CONSTRAINT fk_id_two FOREIGN KEY ( NUMBER ) REFERENCES `lotto_number` (`NUMBER`) on delete cascade
              ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
        
              DROP TABLE lotto_number_total;
        */
        
        
        lottoDB.close();
    }
    
    public static void allUpdate (LottoDB lottoDB, int input) throws Exception {
        for(int index = 1; index <= input; index++ ) {
            String parsingUrl = "https://www.dhlottery.co.kr/gameResult.do?method=statByColor&currentPage="+index;
            Document doc= Jsoup.connect(parsingUrl).get();
            if(doc.select("table.tbl_data.tbl_data_col tbody")!= null){
                Element table  = doc.select("table.tbl_data.tbl_data_col tbody").get(0);
                Elements tr   = table.select("tr");
                for ( Element item : tr ) {
                    Elements td = item.select("td");
                    String date = td.get(0).text();
                    String number = td.get(2).text();
                    if(lottoDB.getQurry("SELECT * FROM lotto_data WHERE DATE='"+date+"'").size() < 1) {
                        System.out.println("date : " + date);
                        System.out.println("number : " + number);
                        lottoDB.insertLotto(date, number);
                        subtotalOne(lottoDB); // 최근 1개 누적값 업데이트
                    };
                }
                
            }
        }
    }
    public static void subtotal (LottoDB lottoDB , int n) throws Exception {
        Map<Integer, Map<Integer, Integer>> subtotal = new HashMap<Integer, Map<Integer, Integer>>();
        for(int index = 1; index <= n; index++ ) {
            String parsingUrl = "https://www.dhlottery.co.kr/gameResult.do?method=statByColor&currentPage="+index;
            Document doc= Jsoup.connect(parsingUrl).get();
            if(doc.select("table.tbl_data.tbl_data_col tbody")!= null){
                Element table  = doc.select("table.tbl_data.tbl_data_col tbody").get(0);
                Elements tr   = table.select("tr");
                for ( Element item : tr ) {
                    Elements td = item.select("td");
                    String date = td.get(0).text();
                    String[] numbers = td.get(2).text().split(" ");
                    for( int innerIndex = 0; innerIndex < numbers.length; innerIndex++) {
                        
                        int castNumber = Integer.parseInt(numbers[innerIndex]);
                        
                        if( !subtotal.containsKey(castNumber) ) {
                            Map<Integer, Integer> inner = new HashMap<Integer, Integer>();
                            subtotal.put(castNumber, inner);
                        } 
                        
                        for( int innerIndex2 = 0; innerIndex2 < numbers.length; innerIndex2++) {
                            if(innerIndex != innerIndex2) {
                                int castNumber2 = Integer.parseInt(numbers[innerIndex2]);
                                Map<Integer, Integer> temp = subtotal.get(castNumber);
                                if(temp.containsKey(castNumber2)) {
                                    temp.put(castNumber2, temp.get(castNumber2)+1);
                                } else {
                                    temp.put(castNumber2, 1);
                                }
                            }
                        }
                    }
                }
                
            }
        }
        
        //잠깐 출력 
        for( int first : subtotal.keySet() ) {
            System.out.println("KEY : " + first);
            for(int inner : subtotal.get(first).keySet()) {
                System.out.println("Inner KEY : " + inner);
                System.out.println("Valuue : " + subtotal.get(first).get(inner));
                //lottoDB.insertLottoNum(String.valueOf(first), String.valueOf(inner), String.valueOf(subtotal.get(first).get(inner)));
            }
        }
        
    }
    public static void subtotalOne (LottoDB lottoDB) throws Exception {
        for(int index = 1; index <= 1; index++ ) {
            String parsingUrl = "https://www.dhlottery.co.kr/gameResult.do?method=statByColor&currentPage="+index;
            Document doc= Jsoup.connect(parsingUrl).get();
            if(doc.select("table.tbl_data.tbl_data_col tbody")!= null){
                Element table  = doc.select("table.tbl_data.tbl_data_col tbody").get(0);
                Element tr   = table.select("tr").get(0);
                Elements td = tr.select("td");
                String[] numbers = td.get(2).text().split(" ");
                System.out.println(td.get(2).text());
                for( int innerIndex = 0; innerIndex < numbers.length; innerIndex++) {
                    
                    int castNumber = Integer.parseInt(numbers[innerIndex]);
                    
                    for( int innerIndex2 = 0; innerIndex2 < numbers.length; innerIndex2++) {
                        if(innerIndex != innerIndex2) {
                            int castNumber2 = Integer.parseInt(numbers[innerIndex2]);
                            System.out.println("castNumber : " + castNumber);
                            System.out.println("numbers[innerIndex2] : " + numbers[innerIndex2]);
                            int getOne = lottoDB.getLottoNumOne("SELECT VALUE FROM lotto_number_total WHERE NUMBER="+castNumber+" AND NUMBER_TWO="+numbers[innerIndex2]);
                            System.out.println(getOne);
                            lottoDB.updateLottoNum(numbers[innerIndex], numbers[innerIndex2], String.valueOf(getOne+1));
                            int updateOne = lottoDB.getLottoNumOne("SELECT VALUE FROM lotto_number_total WHERE NUMBER="+castNumber+" AND NUMBER_TWO="+numbers[innerIndex2]);
                            System.out.println(updateOne);
                        }
                    }
                }
            }
        }
    }
}

