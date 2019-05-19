package com.seokjin.readWriteExcel;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface ExcelWriteReadInter {

    XSSFWorkbook readExcel();
    void writeExcel( XSSFWorkbook workbook ); // 엑셀에 쓰기 기능 
    String getCellValue ( XSSFWorkbook workbook, int sheetNum, int rowNum, int cellNum ); // workbook의  sheetNum의 rowNum의 cellNum의 값을 가지고 옴
    void createCell( XSSFWorkbook workbook, int sheetNum, int rowNum, int cellNum, String content, CellType cellType ); 
    // workbook의  sheetNum의 rowNum의 cellNum에다가 content를 새로 생성하는데 cellType에 따라 문자열 , 함수 등으로 지정할 수 있음
    
    void createRowCell( XSSFWorkbook workbook, int sheetNum, int rowNum, String content, CellType cellType );
    // workbook의  sheetNum의 rowNum(해당 줄)이 존재하다면 존재하는 row의 맨 마지막 cellNum에다가, 없으면 row를 생성해서 셀을 만든다.
    
    void modifyCell( XSSFWorkbook workbook, int sheetNum, int rowNum, int cellNum, String content, CellType cellType );
   // workbook의  sheetNum의 rowNum의 cellNum에다가 content를 수정 하는데 cellType에 따라 문자열 , 함수 등으로 지정할 수 있음
    
    void modifyAllCell( XSSFWorkbook workbook , ModifyAllCelInter outDIInterface );
   // workbook의 전체 셀에 특정 조건에 따라 모든 셀을 수정하는 것을 만든다. ModifyAllCelInter에 modifyAllCellsWithOutMethod 해당 로직이 담겨 있음
    
}
