package com.seokjin.readWriteExcel;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriteReadImpl implements ExcelWriteReadInter {
    
    private String readFileName;
    private String writeFilename;
    
    public ExcelWriteReadImpl(String readFileName) {
        this.readFileName = readFileName;
    }

    public ExcelWriteReadImpl(String readFileName, String writeFilename) {
        this.readFileName = readFileName;
        this.writeFilename = writeFilename;
    }

    @Override
    public XSSFWorkbook readExcel() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        try {
            if( readFileName != null){
                FileInputStream inputStream = new FileInputStream(readFileName);
                workbook = new XSSFWorkbook(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return workbook;
    }

    @Override
    public void writeExcel( XSSFWorkbook workbook ) {
        if( writeFilename == null ){
            writeFilename = readFileName;
        }
        
      //workbook에 파일 담기 
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(writeFilename);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String getCellValue(XSSFWorkbook workbook, int sheetNum, int rowNum, int cellNum) {
        XSSFRow row;
        XSSFCell cell;
        String cellContent = "";
        XSSFSheet sheet = workbook.getSheetAt(sheetNum);
        if( sheet != null){
            row = sheet.getRow(rowNum);
            if( row != null) {
                cell = row.getCell(cellNum);
                if( cell !=null ){
                    cellContent = cell.getStringCellValue();
                }
            }
        }
        return cellContent;
    }

    @Override
    public void createCell(XSSFWorkbook workbook, int sheetNum, int rowNum, int cellNum, String cellContent, CellType cellType ) {
        XSSFRow row;
        XSSFCell cell;
        XSSFSheet sheet = workbook.getSheetAt( sheetNum );
        if( sheet != null){
            if(sheet.getRow(rowNum) == null ){
                row = sheet.createRow(rowNum);
            } else {
                row = sheet.getRow(rowNum);
            }
            cell = row.createCell(cellNum);
            if( cellType == cellType.STRING ){
                cell.setCellValue(cellContent);
            } else if( cellType == cellType.NUMERIC ){
                cell.setCellValue(Double.valueOf(cellContent));
            } else if ( cellType == cellType.FORMULA) {
                cell.setCellFormula(cellContent);
            }
        }
    }

    @Override
    public void modifyCell( XSSFWorkbook workbook, int sheetNum, int rowNum, int cellNum, String cellContent, CellType cellType ) {
        XSSFRow row;
        XSSFCell cell;
        XSSFSheet sheet = workbook.getSheetAt(sheetNum);
        if( sheet != null){
            row = sheet.getRow(rowNum);
            if( row != null) {
                cell = row.getCell(cellNum);
                if( cell !=null ){
                    if( cellType == cellType.STRING ){
                        cell.setCellValue(cellContent);
                    } else if( cellType == cellType.NUMERIC ){
                        cell.setCellValue(Double.valueOf(cellContent));
                    } else if ( cellType == cellType.FORMULA) {
                        cell.setCellFormula(cellContent);
                    }
                }
            }
        }
    }
    
    @Override
    public void modifyAllCell(XSSFWorkbook workbook , ModifyAllCelInter outDIInterface) {
        XSSFRow row;
        XSSFCell cell;

        int sheetCn = workbook.getNumberOfSheets();
        for( int cn = 0; cn < sheetCn; cn++ ){
            
            XSSFSheet sheet = workbook.getSheetAt(cn);
            int rows = sheet.getPhysicalNumberOfRows();
            
            if( rows > 0 ){
                int cells = sheet.getRow(cn).getPhysicalNumberOfCells();
                for (int r = 0; r < rows; r++) {
                    row = sheet.getRow(r);
                    if (row != null) {
                        for (int c = 0; c < cells; c++) {
                            cell = row.getCell(c);
                            if( outDIInterface != null ){
                                outDIInterface.modifyAllCellsWithOutMethod(cell);
                            }
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public void createRowCell(XSSFWorkbook workbook, int sheetNum, int rowNum, String content, CellType cellType) {
        XSSFRow row;
        XSSFCell cell;
        XSSFSheet sheet = workbook.getSheetAt( sheetNum );
        
        if( sheet != null){
            if(sheet.getRow(rowNum) == null ){
                row = sheet.createRow(rowNum);
            } else {
                row = sheet.getRow(rowNum);
            }
            int cellNumber = row.getPhysicalNumberOfCells();
            cell = row.createCell(cellNumber);
            if( cellType == cellType.STRING ){
                cell.setCellValue(content);
            } else if( cellType == cellType.NUMERIC ){
                cell.setCellValue(Double.valueOf(content));
            } else if ( cellType == cellType.FORMULA) {
                cell.setCellFormula(content);
            }
        }
    }

    public String getReadFileName() {
        return readFileName;
    }

    public void setReadFileName(String readFileName) {
        this.readFileName = readFileName;
    }

    public String getWriteFilename() {
        return writeFilename;
    }

    public void setWriteFilename(String writeFilename) {
        this.writeFilename = writeFilename;
    }

}
