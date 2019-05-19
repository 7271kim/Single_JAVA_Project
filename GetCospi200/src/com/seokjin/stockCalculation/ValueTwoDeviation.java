package com.seokjin.stockCalculation;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.seokjin.calculation.CalculationImpl;
import com.seokjin.getCharcter.GetCarcterSetImpl;
import com.seokjin.getUrlInfomation.JsoupGetUrlImpl;
import com.seokjin.readWriteExcel.ExcelWriteReadImpl;

public class ValueTwoDeviation {
    private String inOutFile;
    
    public ValueTwoDeviation(String inOutFile) {
        this.inOutFile = inOutFile;
    }
    public void getValueTwoDeviation() {
        String readFileString =inOutFile;
        String writreFileString=inOutFile;
        GetCarcterSetImpl charcterSetImpl = new GetCarcterSetImpl();
        JsoupGetUrlImpl getUrlImpl = new JsoupGetUrlImpl();
        CalculationImpl cal = new CalculationImpl();
        ExcelWriteReadImpl excel = new ExcelWriteReadImpl(readFileString, writreFileString );
        XSSFWorkbook workbook = excel.readExcel();
        XSSFRow row;
        XSSFCell cell;
        String code;
        String url;
        String deviation;
        System.out.println("시작");
        try {
            XSSFSheet sheet =  workbook.getSheetAt(0);
            int rowNum = sheet.getPhysicalNumberOfRows();
            for( int index = 1; index < rowNum; index++ ){
                System.out.println("index : "+index);
                row = sheet.getRow(index);
                cell = row.getCell(1);
                code = cell.getStringCellValue();
                url = "http://finance.naver.com/item/sise_day.nhn?code="+code+"&page=";
                ArrayList<Double> deviations = new ArrayList<>();
                int count = 1;
                Thread.sleep(100);
                for( int pageIndex = 1; pageIndex <= 2; pageIndex++ ){
                    String realurl = url + pageIndex;
                    System.out.println("이름 : " + row.getCell(0));
                    for( int eqIndex = 0; eqIndex <=17; eqIndex++ ){
                        Elements elements = getUrlImpl.getUrlNodes(realurl, "tr:eq("+eqIndex+") td:eq(1) .tah.p11");
                        for(Element el : elements){
                            String elText = el.text().replaceAll(",", "");
                            if(!elText.equals("")){
                                double elTextValue= Double.valueOf(elText);
                                deviations.add(elTextValue);
                                if( pageIndex == 1 && count == 1 ){
                                    excel.createCell(workbook, 0, index, 2, elText, CellType.NUMERIC);
                                }
                                if(count == 5){
                                    deviation = String.valueOf(cal.mean(deviations) - 2 * cal.deviation(deviations));
                                    excel.createCell(workbook, 0, index, 3, deviation, CellType.NUMERIC);
                                    deviation = String.valueOf(cal.mean(deviations) + 2 * cal.deviation(deviations));
                                    excel.createCell(workbook, 0, index, 4, deviation, CellType.NUMERIC);
                                }
                                if(count == 10){
                                    deviation = String.valueOf(cal.mean(deviations) - 2 * cal.deviation(deviations));
                                    excel.createCell(workbook, 0, index, 5, deviation, CellType.NUMERIC);
                                    deviation = String.valueOf(cal.mean(deviations) + 2 * cal.deviation(deviations));
                                    excel.createCell(workbook, 0, index, 6, deviation, CellType.NUMERIC);
                                }
                                if(count == 20){
                                    deviation = String.valueOf(cal.mean(deviations) - 2 * cal.deviation(deviations));
                                    excel.createCell(workbook, 0, index, 7, deviation, CellType.NUMERIC);
                                    deviation = String.valueOf(cal.mean(deviations) + 2 * cal.deviation(deviations));
                                    excel.createCell(workbook, 0, index, 8, deviation, CellType.NUMERIC);
                                }
                                count++;
                            }
                        }
                    }
                }
            }
            excel.writeExcel(workbook);
            System.out.println("끝");
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
