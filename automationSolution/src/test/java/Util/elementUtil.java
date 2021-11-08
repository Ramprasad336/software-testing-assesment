package Util;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class elementUtil {
	
	private WebDriver driver;
	
	public elementUtil(WebDriver driver) {
		this.driver = driver;
	}
	
	public void ImplicitlyWait(long Time)
    {
		driver.manage().timeouts().implicitlyWait(Time, TimeUnit.SECONDS);
    }
	
	public void PageLoadTimeOut(long Time)
    {
		driver.manage().timeouts().pageLoadTimeout(Time, TimeUnit.SECONDS);
    }
	
	public void HardWait(long Time)
    {
		try {
			Thread.sleep(Time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
	
	public void ClickTheKeyboardKey(String key)
    {
        /*Please carry on adding as require a key*/
        if (key == "Enter")
        {
            Actions clickAction = new Actions(driver).sendKeys(Keys.ENTER);
            clickAction.build().perform();
        } else if (key == "Delete")
        {
            Actions clickAction = new Actions(driver).sendKeys(Keys.DELETE);
            clickAction.build().perform();
        }
    }
	
	public void WaitTillElementToClickable(By by, long TimeOut) {
		WebDriverWait wait = new WebDriverWait(driver, TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}
	
	public void ScrolldownTheEntirePage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//This will scroll the web page till end.		
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public void ScrolldownByPixel(int Pixel) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// This  will scroll down the page by pixel vertical		
        js.executeScript("window.scrollBy(0,"+ Pixel + ")", "");
	}
	
	public void ScrolldownTillElementVisible(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void JavascriptExecutorElementClick(WebElement element) {
		 JavascriptExecutor js= (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].click();", element); 
	}
	
	public void ZoomInOutChrome(double percentage)
    {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom = '" + percentage + "%'");
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /*js.ExecuteScript("document.body.style.zoom = '80%'");
        Thread.Sleep(1000);
        js.ExecuteScript("document.body.style.zoom = '70%'");
        Thread.Sleep(1000);*/
    }


	
	 public void customWaitAndClickForStaleElementReferenceException(WebElement element) {
	        boolean flag = true;
	        long currentTime = System.currentTimeMillis();
	        long end = currentTime + 60000L;

	        while (!flag || System.currentTimeMillis() < end) {
	            try {
	                element.click();
	                break;
	            } catch (Exception e) {
	                flag = false;
	            }
	        }
	 }
	 
	 public int SelectRadomNumberWithinSpecificRange(int min, int max) {
			/* Generate random int value from 50 to 100 */ 
	      int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
			/*
			 * System.out.println("Random value in int from "+min+" to "+max+
			 * ":"+random_int);
			 */
	      return random_int;
	 }
	 
	public void AccessDropdownByIndex(WebElement element, int value) {
		Select dropdown = new Select(element);
		dropdown.selectByIndex(value);
    }
	
	public void AccessDropdownByValue(WebElement element, String value) {
		Select dropdown = new Select(element);
		dropdown.selectByValue(value);
    }
	
	public void AccessDropdownByVisibleText(WebElement element, String value) {
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(value);
    }

}
