package cucumberTest;





import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


//@RunWith(Cucumber.class)
@CucumberOptions(
 features = {"Feature"},glue={"StepDefinition"}
 ,plugin={"pretty","html:target"}
 )
//@Listeners(cucumberTest.ListenerTestNG.class)

@Test
public class TestRunner  extends AbstractTestNGCucumberTests{
}

