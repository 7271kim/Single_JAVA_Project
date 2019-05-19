import com.seokjin.stockCalculation.StockCalculation;
import com.seokjin.stockCalculation.StockCalculationImpl;

public class TotalAfterOneday {

    public static void main(String[] args) {
        // 1. 코스피, 코스닥 흐름 추적 
        String inOutFile ="D:/StockStrategy/Oneday/Top30_180707.xlsx"; 
        
        StockCalculation stockCalculation = new StockCalculationImpl(inOutFile);
        
        //Kospi_Kosdaq_Ondevi kospi200 = new Kospi_Kosdaq_Ondevi(inOutFile);
        //kospi200.getKospi_Kosdaq_Ondevi();
        
        //2. 핫한 종목 추적
        inOutFile ="D:/StockStrategy/Oneday/Top30_180715.xlsx"; 
        stockCalculation.setInOutFile(inOutFile);
        //stockCalculation.getTop30();
        //stockCalculation.getValueOnedeviationAdd();
        
        
        //3. 결과 추적 
        inOutFile ="D:/StockStrategy/Oneday/Top30_180701_result_180715.xlsx"; 
        stockCalculation.setInOutFile(inOutFile);
        //stockCalculation.getResultTrace();
        //stockCalculation.getAnalSite();
        
        //4. 전체 kopi 종목 가지고 오기
        inOutFile ="D:/StockStrategy/Oneday/Kosipi_total.xlsx"; 
        stockCalculation.setInOutFile(inOutFile);
        //stockCalculation.getAllStockKospi();
        //stockCalculation.getAnalSite();
        stockCalculation.getHiprice();
        
        
        //5. 전체 종목 결과 추적
        inOutFile ="D:/StockStrategy/Oneday/Kosipi_total_trace_0727.xlsx"; 
        stockCalculation.setInOutFile(inOutFile);
        //stockCalculation.getResultTrace();
    }

}
