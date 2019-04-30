package write;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

public class demoexcel {
	@DataProvider(name="tr")
	public static Object main() throws  InvalidFormatException, IOException{
		File f=new File("E://SELIUM_WITH_JAVA//chi.xls");
		
		Workbook wr=WorkbookFactory.create(f);
		Sheet s=wr.getSheetAt(0);
		int rowc=s.getLastRowNum()-s.getFirstRowNum();
		Object ob[][]=new Object[rowc][2];
		for(int i=0;i<rowc;i++){
			Row nr=s.getRow(i);
			for(int j=0;j<2;j++){
				Cell c=nr.getCell(j);
				String ss=c.getStringCellValue();
				ob[i][j]=ss;
			}
		}
		return ob;
	}
@Test(dataProvider="tr")
public void n(String name,String pass){
	System.out.println(name+" "+pass);
}
}
