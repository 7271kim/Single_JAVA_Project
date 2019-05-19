import java.awt.Color;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsStyleshhet {

	public static void main(String[] args) {

		XSSFRow row;

		XSSFCell cell;

		//     전체 도움말

		try {
			//workbook에 파일 담기 
			FileOutputStream fileOut = new FileOutputStream("D:/StockStrategy/TwoStandardDeviation2.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook();
			
			//Font지정부
			//https://poi.apache.org/apidocs/org/apache/poi/xssf/usermodel/XSSFFont.html#setColor-org.apache.poi.xssf.usermodel.XSSFColor-
			XSSFFont defaultFont = workbook.createFont(); 
			defaultFont.setFontHeightInPoints((short)11);
			defaultFont.setFontName("맑은고딕");
			defaultFont.setBold(true);
			defaultFont.setItalic(false);
			//defaultFont.setColor(new XSSFColor(new Color(0, 0, 0)).getIndexed());
			defaultFont.setColor(new XSSFColor(new Color(122, 94, 176)));
			
			//제목 스타일
			//https://poi.apache.org/apidocs/org/apache/poi/xssf/usermodel/XSSFCellStyle.html#XSSFCellStyle-int-int-org.apache.poi.xssf.model.StylesTable-org.apache.poi.xssf.model.ThemesTable-
			XSSFCellStyle headStyle = workbook.createCellStyle(); 
			headStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
			headStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			headStyle.setFillBackgroundColor(new XSSFColor(new Color(255, 255, 0)));	
			headStyle.setRightBorderColor(new XSSFColor(new Color(255, 255, 0)));
			headStyle.setFont(defaultFont);
			
			
			XSSFSheet sheet = workbook.createSheet("sheet의 이름");
			row = sheet.createRow(0);
			cell = row.createCell(0);
			cell.setCellValue("=1+2");
			cell.setCellStyle(headStyle);
			workbook.write(fileOut);
			
			fileOut.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
