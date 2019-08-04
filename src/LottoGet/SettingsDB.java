package LottoGet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class SettingsDB {
    
    public static void main(String[] args) throws Exception {
        LottoDB lottoDB = new LottoDB();
       
        allUpdate(lottoDB, 1);
        
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
                    System.out.println("date : " + date);
                    System.out.println("number : " + number);
                    lottoDB.insertLotto(date, number);
                }
                
            }
        }
    }
}

