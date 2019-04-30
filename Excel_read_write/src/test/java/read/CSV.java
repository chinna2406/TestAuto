
package read;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

public class CSV {
@DataProvider(name="test")
public Object[][] data() throws IOException{
	CSVReader cs=new CSVReader(new FileReader("E:/SELIUM_WITH_JAVA/CSVR.csv"));
	List<String[]> l=cs.readAll();
	String[][]str=new String[l.size()][];
	for(int i=0;i<l.size();i++){
		str[i]=l.get(i);
	}
	return str;
}
@Test(dataProvider="test")
public void main(String name, String pss){
	System.out.println(name+" "+pss);
}
}

