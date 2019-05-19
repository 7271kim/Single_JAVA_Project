import com.seokjin.stockCalculation.Kospi_Kosdaq_Ondevi;

public class MainKospi_kosdaq {

    public static void main(String[] args) {
        // inOutFiled파일에 5일 선만 작성 할 것임
        // 2번째 줄 부턱 작성 될 것임.
        String inOutFile ="D:/StockStrategy/OneTrace/kospi_kosdaq_total_devi.xlsx"; 
        
        Kospi_Kosdaq_Ondevi kospi200 = new Kospi_Kosdaq_Ondevi(inOutFile);
        kospi200.getKospi_Kosdaq_Ondevi();
    }

}
