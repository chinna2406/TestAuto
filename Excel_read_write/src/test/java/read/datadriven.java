package read;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class datadriven {

	@DataProvider(name="tdc")
	public Object[][] all() throws InvalidFormatException, IOException{
		Object ob[][]=null;
		File f=new File("E://SELIUM_WITH_JAVA//chi.xls");
		FileInputStream in=new FileInputStream(f);
		Workbook wr=WorkbookFactory.create(in);
		//Workbook ws=new XSSFWorkbook();
		Sheet s=wr.getSheetAt(0);
		int row=s.getLastRowNum()-s.getFirstRowNum();
		ob=new Object[row][2];
		for(int i=0;i<row;i++){
			Row r=s.getRow(i+1);
for(int j=0;j<2;j++){
	ob[i][j]=r.getCell(j).getStringCellValue();
}
		}
		return ob;
	}
	@Test(dataProvider="tdc")
	public void main(String name,String pass){
		System.out.println(name+" "+pass);
	}
}
