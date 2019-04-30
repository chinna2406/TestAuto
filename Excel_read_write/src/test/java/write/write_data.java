package write;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class write_data {
	public static void main(String a[]) throws IOException, InvalidFormatException{
		File f=new File(System.getProperty("user.dir")+"//"+"chinna.xlsx");
		FileInputStream fi=new FileInputStream(f);
		 Workbook wr=new XSSFWorkbook(fi);
		 String[] st={"chinna","durai","jjj","kk"};
		 Sheet s=wr.getSheetAt(0);
		  int rocount=s.getLastRowNum()-s.getFirstRowNum();
		 
		  Row r=s.getRow(0);//get first row
		  Row n_row=s.createRow(rocount+1);
		  
		  for(int i =0;i<r.getLastCellNum();i++){
			  Cell c=n_row.createCell(i);
			  c.setCellValue(st[i]);
		  }
		 
		  FileOutputStream fo=new FileOutputStream(f);
		  wr.write(fo);
		  System.out.println("success");
		  fi.close();
		  fo.close();
		  
	}

}
