package Factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Collections;
import java.util.logging.Level;


public class driverFactory {

    public static WebDriver driver;

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /**
     * This method is used to initialize the thradlocal driver on the basis of given
     * browser
     *
     * @param browser
     * @return this will return tldriver.
     */
    public WebDriver init_driver(String browser) {
		
        System.out.println("browser value is: " + browser);

        if (browser.equals("Chrome")) {
            WebDriverManager.chromedriver().setup();
        	//System.setProperty("webdriver.chrome.driver", "./src/test/resource/chromedriver.exe");
        	driver = new ChromeDriver(getChromeOptions());
            //tlDriver.set(new ChromeDriver());
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            tlDriver.set(new FirefoxDriver());
        } else if (browser.equals("safari")) {
            tlDriver.set(new SafariDriver());
        } else if (browser.equals("ie")) {
        	WebDriverManager.iedriver().setup();
            tlDriver.set(new InternetExplorerDriver());
        } else if (browser.equals("edge")) {
        	WebDriverManager.edgedriver().setup();
            tlDriver.set(new EdgeDriver());
        }else {
            System.out.println("Please pass the correct browser value: " + browser);
        }

//        getDriver().manage().deleteAllCookies();
//        getDriver().manage().window().maximize();
//        return getDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        EventFiringWebDriver firingDriver = new EventFiringWebDriver(driver);
        EventListenerHighlighter myEventListener = new EventListenerHighlighter(driver);
        firingDriver.register(myEventListener);
        driverFactory.driver = firingDriver;
        return driver;

    }

    /**
     * this is used to get the driver with ThreadLocal
     *
     * @return
     */
    public static synchronized WebDriver getDriver() {
//        return tlDriver.get();
        return driver;
    }

    //Get Chrome Options
    public static ChromeOptions getChromeOptions() {
    	
		/*
		 * options.addArguments( "--verbose", "--headless", "--disable-web-security",
		 * "--ignore-certificate-errors", "--allow-running-insecure-content",
		 * "--allow-insecure-localhost", "--no-sandbox", "--disable-gpu" );
		 */
    	
        ChromeOptions options = new ChromeOptions();
        
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability(CapabilityType.LOGGING_PREFS, logs);
        options.setAcceptInsecureCerts(true);
        
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        
        //options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--incognito");
//        options.addArguments("headless");
        return options;
    }
    //Get Firefox Options
    public static FirefoxOptions getFirefoxOptions () {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        //Accept Untrusted Certificates
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(false);
        //Use No Proxy Settings
        profile.setPreference("network.proxy.type", 0);
        //Set Firefox profile to capabilities
        options.setCapability(FirefoxDriver.PROFILE, profile);
        return options;
    }

}
