import com.seokjin.stockCalculation.GetKospi200;

public class MainGetKosipi200 {

    public static void main(String[] args) {
        // 코스피 200 수집 로직 
        // inOutFile에 2번째 row부터 작성 될 것
        String inOutFile ="D:/StockStrategy/kospi200.xlsx";
        GetKospi200 kospi200 = new GetKospi200(inOutFile);
        kospi200.getKospi200();
    }

}
