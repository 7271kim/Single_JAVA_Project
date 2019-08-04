package LottoGet;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class SettingsDB {
    
    public static void main(String[] args) throws Exception {
        LottoDB lottoDB = new LottoDB();
        //allUpdate(lottoDB, 1);
        
        lottoDB.close();
    }
    
    public static void allUpdate (LottoDB lottoDB, int input) throws Exception {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(System.getenv("ASOG_ENCRYPTION_KEY"));
        String url = encryptor.decrypt("N9E8Gx8OgK3sSTU5lvKukxCZK1JvDJ6yYH00Oo2QGgSArGdet42ZOjulkKYItVCCgMcccyM5U4c=");
        String id = encryptor.decrypt("Ma/9nT/AbenjlE85W0D+di1tNfRHyLTC");
        String pw = encryptor.decrypt("I7qV/0X33au3v+j7swW36uxKBFCrBbg4");
        lottoDB.connectionDB(url,id,pw);
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

