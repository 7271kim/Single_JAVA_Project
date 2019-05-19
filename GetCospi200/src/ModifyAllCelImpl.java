import org.apache.poi.xssf.usermodel.XSSFCell;

import com.seokjin.readWriteExcel.ModifyAllCelInter;

public class ModifyAllCelImpl implements ModifyAllCelInter {
    
    @Override
    public void modifyAllCellsWithOutMethod(XSSFCell cell) {
        String cellValue = cell.getStringCellValue();
        cellValue.replaceAll("z","한글쓰자");
        cell.setCellValue(cellValue);
    }

}
