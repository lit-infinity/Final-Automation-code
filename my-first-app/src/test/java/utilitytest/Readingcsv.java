package utilitytest;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;

public class Readingcsv {
	
	static Sheet sheet;

    static {
        try {
            FileInputStream file = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/valid_credentials.xlsx"
            );
            Workbook wb = WorkbookFactory.create(file);
            sheet = wb.getSheet("Sheet1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getData(int row, int col) {
        return sheet.getRow(row).getCell(col).getStringCellValue();
    }
}