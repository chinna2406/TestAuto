package StepDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;




import com.aventstack.extentreports.GherkinKeyword;

import BaseUtility.UtilityBase;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestSteps extends UtilityBase {
	
	//WebDriver driver= null;
private UtilityBase base;
	
	//public static WebDriver driver;
	public TestSteps(UtilityBase base){
		this.base=base;
	}
	
	 By username99 =By.name("uid");
		By password99=By.name("password");
		By titleText99 =By.className("barone");
		By login99 = By.name("btnLogin");
		By new_custom=By.xpath("//a[contains(text(),'New Customer')]");
		By cusname=By.name("name");
		By date=By.id("dob");
		By addrs=By.name("addr");
		By city=By.name("city");
		By state=By.name("state");
		By pinno=By.name("pinno");
		By telephoneno=By.name("telephoneno");
		By emailid=By.name("emailid");
		By password=By.name("password");
		By subTN=By.name("sub");
		
			 
	 @When("^User Navigate to LogIn Page$")
	 public void user_Navigate_to_LogIn_Page() throws Throwable {
		 //scenariodef.createNode(new GherkinKeyword("Then"), "Login Message ");
		 By homeobj =By.xpath("//div[@class='heading3']");
		 base.driver.findElement(homeobj).getText();
	 }
	 
	 @When("^User enter \"(.*)\" and \"(.*)\"$")
	 public void user_enters_and(String arg1, String arg2) throws Throwable {
		scenariodef.createNode(new GherkinKeyword("When"), "Login process");
		 base.driver.findElement(username99).sendKeys(arg1);
		 base.driver.findElement(password99).sendKeys(arg2);
		 base.driver.findElement(login99).click();
	 }

	
	 
	 @Then("^Message displayed Login Successfully$")	 
	 public void message_displayed_Login_Successfully() throws Throwable {
		 scenariodef.createNode(new GherkinKeyword("Then"), "Message displayed Login Successfully");
	 System.out.println("Login Successfully");
	 }
	 
	@SuppressWarnings("static-access")
	 @Then("^User use new customers from the Application$")
	 public void user_use_new_customer_from_the_Application(DataTable table) throws Throwable {
		 scenariodef.createNode(new GherkinKeyword("Then"), "User use new customer from the Application");
		 List<usedetails> datas=new ArrayList<usedetails>();
	     datas=table.asList(usedetails.class);
	     
	     for(usedetails user:datas){
	    	 base.driver.findElement(new_custom).click();
	   //This is to get the first data of the set (First Row + First Column)
	    	 base.driver.findElement(cusname).sendKeys(user.cunames);
	    	 base.driver.findElement(date).sendKeys(user.dobs);
	    	 base.driver.findElement(addrs).sendKeys(user.addrs);
	    	 base.driver.findElement(city).sendKeys(user.cit);
	    	 base.driver.findElement(state).sendKeys(user.st);
	    	 base.driver.findElement(pinno).sendKeys(user.pin);
	    	 base.driver.findElement(telephoneno).sendKeys(user.pno);
	    	 base.driver.findElement(emailid).sendKeys(user.mail);
	    	 base.driver.findElement(password).sendKeys(user.pass);
	    	 base.driver.findElement(subTN).click();
	 }
	 /*
	 @Then("^Message displayed Logout Successfully$")
	 public void message_displayed_Logout_Successfully() throws Throwable {
	        //driver.quit();
		 System.out.println("LogOut Successfully");
	 }
	 */
	 
	 }}