package StepDefinitions;

import Factory.driverFactory;
import Util.configReader;
import Util.elementUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Properties;

public class appHooks {
	private driverFactory DriverFactory;
	private WebDriver driver;
	private configReader ConfigReader;
	Properties prop;
	private elementUtil util = new elementUtil(driverFactory.getDriver());

	@Before(order = 0)
	public void getProperty() {
		ConfigReader = new configReader();
		ConfigReader.init_prop();
	}

	@Before(order = 1)
	public void launchBrowser() {
		String browserName = ConfigReader.Browser;
		DriverFactory = new driverFactory();
		driver = DriverFactory.init_driver(browserName);	
	}




	@After(order = 0)
	public void quitBrowser() {
		if (driver != null) {
			  driver.close();
			  driver.quit(); 			 
		}
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			// take screenshot:
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(sourcePath,"image/png",screenshotName);
			//scenario.attach(sourcePath, "image/png", screenshotName);
		}
	}
	
}
