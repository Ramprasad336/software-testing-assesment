package Pages;

import Factory.driverFactory;
import Util.configReader;
import Util.elementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class loginPage {

	private WebDriver driver;
	private WebDriverWait wait = new WebDriverWait(driverFactory.getDriver(), 20);
	private static configReader ConfigReader;
	Properties prop;
	private elementUtil util = new elementUtil(driverFactory.getDriver());


	public loginPage(WebDriver driver) {
		this.driver = driver;
	}


	public static void OpenApplicationUrl() {

		driverFactory.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


		String ENV = ConfigReader.Environment;

		if (ENV.equalsIgnoreCase("QA")){
			driverFactory.getDriver().get(ConfigReader.qaURL);

		} else if (ENV.equalsIgnoreCase("DEV")){
			driverFactory.getDriver().get(ConfigReader.devURL);
		}
	}

}
