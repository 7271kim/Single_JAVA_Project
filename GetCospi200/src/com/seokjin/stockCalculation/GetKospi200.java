package com.seokjin.stockCalculation;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.seokjin.getCharcter.GetCarcterSetImpl;
import com.seokjin.getUrlInfomation.JsoupGetUrlImpl;
import com.seokjin.readWriteExcel.ExcelWriteReadImpl;

public class GetKospi200 {
    
    private String inOutFile;
    
	public GetKospi200(String inOutFile) {
        this.inOutFile = inOutFile;
    }
	
	public void getKospi200 () {
	    String readFileString =inOutFile;
        String writreFileString=inOutFile;
        GetCarcterSetImpl charcterSetImpl = new GetCarcterSetImpl();
        ExcelWriteReadImpl excel = new ExcelWriteReadImpl(readFileString, writreFileString );
        XSSFWorkbook workbook = excel.readExcel();
        int count = 1;
        try {
            //간략화된 것 , 접속 1
            for( int index = 1; index <= 21 ; index ++){
                String url = "http://finance.naver.com/sise/entryJongmok.nhn?order=market_sum&isRightDirection=true&page="+index;
                String selector = ".ctg a";
                JsoupGetUrlImpl jsop = new JsoupGetUrlImpl();
                Elements elements = jsop.getUrlNodes(url, selector);
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
