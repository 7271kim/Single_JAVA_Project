package com.seokjin.readWriteExcel;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface ExcelWriteReadInter {

    XSSFWorkbook readExcel();
    void writeExcel( XSSFWorkbook workbook );
    String getCellValue ( XSSFWorkbook workbook, int sheetNum, int rowNum, int cellNum );
    void createCell( XSSFWorkbook workbook, int sheetNum, int rowNum, int cellNum, String content );
    void modifyCell( XSSFWorkbook workbook, int sheetNum, int rowNum, int cellNum, String content );
    void modifyAllCell( XSSFWorkbook workbook , ModifyAllCelInter outDIInterface );
}
