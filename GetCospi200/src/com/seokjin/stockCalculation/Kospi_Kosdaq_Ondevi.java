package com.seokjin.stockCalculation;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.nodes.Element;

import com.seokjin.calculation.CalculationImpl;
import com.seokjin.getUrlInfomation.JsoupGetUrlImpl;
import com.seokjin.readWriteExcel.ExcelWriteReadImpl;

public class Kospi_Kosdaq_Ondevi {
    private String inOutFile;
    
    public Kospi_Kosdaq_Ondevi(String inOutFile) {
        this.inOutFile = inOutFile;
    }
    public void getKospi_Kosdaq_Ondevi() {
        String readFileString =inOutFile;
        String writreFileString=inOutFile;
        JsoupGetUrlImpl getUrlImpl = new JsoupGetUrlImpl();
        CalculationImpl cal = new CalculationImpl();
        ExcelWriteReadImpl excel = new ExcelWriteReadImpl(readFileString, writreFileString );
        XSSFWorkbook workbook = excel.readExcel();
        XSSFRow row;
        XSSFCell cell;
        String name;
        String url;
        String deviation;
        System.out.println("시작");
        try {
            XSSFSheet sheet =  workbook.getSheetAt(0);
            int rowNum = sheet.getPhysicalNumberOfRows();
            for( int index = 1; index < rowNum; index++ ){
                System.out.println("index : "+index);
                row = sheet.getRow(index);
                cell = row.getCell(0);
                name = cell.getStringCellValue();
                url = "http://finance.naver.com/sise/sise_index_day.nhn?code="+name+"&page=";
                ArrayList<Double> deviations = new ArrayList<>();
                int count = 1;
                Thread.sleep(100);
                for( int pageIndex = 1; pageIndex <= 2; pageIndex++ ){
                    String realurl = url + pageIndex;
                    if( count > 6 ){ break;}
                    System.out.println("이름 : " + row.getCell(0));
                    for( int eqIndex = 0; eqIndex <=17; eqIndex++ ){
                        Element element = getUrlImpl.getUrlNodes(realurl, "tr:eq("+eqIndex+") .number_1").first();
                        // 여기 중이였음
                        String elText = element.text().replaceAll(",", "");
                        if(!elText.equals("")){
                            double elTextValue= Double.valueOf(elText);
                            deviations.add(elTextValue);
                            if( pageIndex == 1 && count == 1 ){
                                excel.createCell(workbook, 0, index, 1, elText, CellType.NUMERIC);
                            }
                            if(count == 5){
                                deviation = String.valueOf(cal.mean(deviations) - 1.6 * cal.deviation(deviations));
                                excel.createCell(workbook, 0, index, 2, deviation, CellType.NUMERIC);
                                deviation = String.valueOf(cal.mean(deviations) + 1.6 * cal.deviation(deviations));
                                excel.createCell(workbook, 0, index, 3, deviation, CellType.NUMERIC);
                            }
                            count++;
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
