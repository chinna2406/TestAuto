package BaseUtility;


import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


public class UtilityBase {
	
	public static WebDriver driver;
	
	public ExtentReports report;
	
	public static ExtentTest scenariodef;
	
	public static ExtentTest feature;
	public static String reportLocation="./Reports/";
	
	}
