package cucumberTest;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.gherkin.model.Feature;

import BaseUtility.extentUtil;
import static BaseUtility.UtilityBase.feature;

public class ListenerTestNG implements ITestListener{
	extentUtil extenbase=new extentUtil();

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult result) {
		try{
			System.out.println("failled.... tc");
			extenbase.ExtntScreenshot();
		}catch(Exception e){
			
		}
		
		// TODO Auto-generated method stub
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		extenbase.extentReport();
		feature=extenbase.report.createTest(Feature.class, "TestNGSuite TC's");
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extenbase.flush();
		
	}
  
}
