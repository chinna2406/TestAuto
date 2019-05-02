package StepDefinition;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import BaseUtility.UtilityBase;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hook extends UtilityBase {
	private UtilityBase base;
	
	//public static WebDriver driver;
	public Hook(UtilityBase base){
		this.base=base;
	}
	
	@SuppressWarnings("static-access")
	@Before()
	public void setUp( Scenario scenario) throws MalformedURLException{
		String url="http://localhost:4444/wd/hub";
		scenariodef=base.feature.createNode(scenario.getName().toUpperCase());
		DesiredCapabilities capabilities=new DesiredCapabilities().chrome();
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/Lib/chromedriver.exe");
		base.driver=new RemoteWebDriver(new URL(url),capabilities);
		//base.driver= new ChromeDriver(capabilities);
        base.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        base.driver.get("http://demo.guru99.com/V4/");
		base.driver.manage().window().maximize();

	}
@SuppressWarnings("static-access")
 @After
	public void TearDown(){
		base.driver.quit();
	}
	
}
