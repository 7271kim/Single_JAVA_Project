package com.seokjin.stockCalculation;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.seokjin.getCharcter.GetCarcterSetImpl;
import com.seokjin.getUrlInfomation.JsoupGetUrlImpl;
import com.seokjin.readWriteExcel.ExcelWriteReadImpl;

public class Kosdaq100 {
    
    private String inOutFile;
    
    public Kosdaq100(String inOutFile) {
        this.inOutFile = inOutFile;
    }
    
    public void getKosdaq100() {
        String readFileString = inOutFile;
        String writreFileString= inOutFile;
        GetCarcterSetImpl charcterSetImpl = new GetCarcterSetImpl();
        ExcelWriteReadImpl excel = new ExcelWriteReadImpl(readFileString, writreFileString );
        XSSFWorkbook workbook = excel.readExcel();
        int count = 0;
        try {
            //간략화된 것 , 접속 1
            for( int index = 1; index <= 2 ; index ++){
                String url = "http://finance.naver.com/sise/sise_market_sum.nhn?sosok=1&page="+index;
                String selector = ".tltle";
                JsoupGetUrlImpl jsop = new JsoupGetUrlImpl();
                Elements elements = jsop.getUrlNodes(url, selector);
                System.out.println(elements);
                for( Element el : elements){
                    String code = el.attr("href");
                    String company = el.text();
                    code = charcterSetImpl.cuttingBackString(code, "=");
                    excel.createCell(workbook, 0, count, 0, company, CellType.STRING);
                    excel.createCell(workbook, 0, count, 1, code, CellType.STRING);
                    
                    count ++;
                    System.out.println(code + " " + company);
                    
                }
            }
            excel.writeExcel(workbook);
            System.out.println("-----끝------");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getInOutFile() {
        return inOutFile;
    }

    public void setInOutFile(String inOutFile) {
        this.inOutFile = inOutFile;
    }
    
    
}
