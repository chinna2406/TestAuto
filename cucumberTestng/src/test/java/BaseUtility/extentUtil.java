package BaseUtility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class extentUtil  extends UtilityBase{
	
	
	String fileName=reportLocation+"extentreport.html";
	
	public void extentReport(){
	//First create extnt report object
		report=new ExtentReports();
		ExtentHtmlReporter htmlreport=new ExtentHtmlReporter(fileName);
		htmlreport.config().setTheme(Theme.DARK);
		htmlreport.config().setDocumentTitle("Cucumber Learn");
		htmlreport.config().setEncoding("utf-8");
		htmlreport.config().setReportName("Test Report");
		
		report.attachReporter(htmlreport);
		
		}
	public void ExtntScreenshot(){
		TakesScreenshot ts=(TakesScreenshot)driver;	
		File src=ts.getScreenshotAs(OutputType.FILE);
		 try {
			FileUtils.copyFile(src, new File("E://chinna.png"));
			scenariodef.fail("details").addScreenCaptureFromPath("E://chinna.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	}
	public void flush(){
		report.flush();
	}

}
