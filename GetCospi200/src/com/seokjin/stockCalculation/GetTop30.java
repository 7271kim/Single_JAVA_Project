package com.seokjin.stockCalculation;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.seokjin.getCharcter.GetCarcterSetImpl;
import com.seokjin.getUrlInfomation.JsoupGetUrlImpl;
import com.seokjin.readWriteExcel.ExcelWriteReadImpl;

public class GetTop30 {
    
    private String inOutFile;
    
    public GetTop30(String inOutFile) {
        this.inOutFile = inOutFile;
    }
    
	public void getTop30() {
	    String readFileString =inOutFile;
	    String writreFileString=inOutFile;
	    GetCarcterSetImpl charcterSetImpl = new GetCarcterSetImpl();
	    ExcelWriteReadImpl excel = new ExcelWriteReadImpl(readFileString, writreFileString );
	    XSSFWorkbook workbook = excel.readExcel();
	    int count = 1;
	    try {
            //간략화된 것 , 접속 1
            String url = "http://finance.naver.com/sise/lastsearch2.nhn";
            JsoupGetUrlImpl jsop = new JsoupGetUrlImpl();
            Elements elements = jsop.getUrlNodes(url, ".box_type_l tbody tr");
            //System.out.println(elements);
            for( Element el : elements){
                String ratio = el.select(".number").eq(0).text();
                String upDown = el.select("td .tah.p11").eq(1).text();
                if(upDown.indexOf("+") > -1){
                    String code = el.select("a").attr("href");
                    String company = el.select("a").text();
                    upDown  = upDown.replaceAll("%", "");
                    code = charcterSetImpl.cuttingBackString(code, "=");
                    if(!code.equals("")){
                        excel.createCell(workbook, 0, count, 0, company, CellType.STRING);
                        excel.createCell(workbook, 0, count, 1, code, CellType.STRING);
                        excel.createCell(workbook, 0, count, 2, ratio, CellType.STRING);
                        excel.createCell(workbook, 0, count, 3, upDown, CellType.NUMERIC);
                        count ++;
                    }
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
