package utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Parametization {

	public static String getExcelData(String Sheetname ,int row ,int cell) throws EncryptedDocumentException, IOException {
		String value=null;
		
		
		try {
			String filePath=" ";
			FileInputStream file = new FileInputStream(filePath);
			WorkbookFactory.create(file).getSheet(Sheetname).getRow(row).getCell(cell).getStringCellValue();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return value;
		
	}
	
}
