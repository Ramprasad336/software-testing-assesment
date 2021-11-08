package StepDefinitions;

import Factory.driverFactory;
import Pages.loginPage;
import Util.configReader;
import Util.elementUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.Properties;
import java.util.concurrent.TimeUnit;


import static org.testng.Assert.assertEquals;

public class Checkout extends driverFactory {

/*	public static WebDriver driver=null;
	public static String browser = configProps.getProperty("browser");
	public static String url = configProps.getProperty("url");*/

	private WebDriverWait wait = new WebDriverWait(driverFactory.getDriver(), 20);
	private configReader ConfigReader;
	private elementUtil util = new elementUtil(driverFactory.getDriver());
	private loginPage LoginPage = new loginPage(driverFactory.getDriver());

	@Given("User launches chrome browser with URL")
	public void user_launches_chrome_browser_with_url() {
		loginPage.OpenApplicationUrl();
	}

/*	@Given("^User navigates to swaglabs$")
	public void user_navigates_to_swaglabs() throws Throwable {
	    
		if(browser.equals("chrome")) {
			 
			 System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "\\Drivers\\chromedriver.exe");

			 ChromeOptions options = new ChromeOptions();
			 options.addArguments("javascript.enabled","true");
			 driver = new ChromeDriver(options);
		 }
		 else if(browser.equals("FF")) {

				 System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ "\\Drivers\\geckodriver.exe");	
				driver = new FirefoxDriver();
		 }
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}*/

	@When("^user enters username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void user_enters_username_and_password(String username, String password) throws Throwable {
	    
		driver.findElement(By.id("user-name")).sendKeys(username);	
		driver.findElement(By.id("password")).sendKeys(password);
	}

	@When("^user clicks on login button$")
	public void user_clicks_on_login_button() throws Throwable {
	    
		driver.findElement(By.id("login-button")).click();
	}

	@Then("^user redirects to inventory page$")
	public void user_redirects_to_inventory_page() throws Throwable {
	    
		 boolean status = driver.findElement(By.xpath("//div[@class='inventory_list']")).isDisplayed();
		 assertEquals(status,true);
		
	}

	@When("^User selects any (\\d+) products$")
	public void user_selects_any_products(int numOfItems) throws Throwable {
	    
		//String actCartCount = driver.findElement(By.xpath("//*[@class='shopping_cart_link']")).getText();
	    //assertEquals(actCartCount,"","Initial cart count should be empty");
	}

	@When("^User click on Add to cart button$")
	public void user_click_on_Add_to_cart_button() throws Throwable {
	    
		
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
	}

	@Then("^Cart should contain (\\d+) items$")
	public void cart_should_contain_items(int numOfItems) throws Throwable {
	    
		String actCartCount = driver.findElement(By.xpath("//*[@class='shopping_cart_link']")).getText();
		assertEquals(Integer.parseInt(actCartCount),2);
	}

	@When("^User clicks on Cart button$")
	public void user_clicks_on_Cart_button() throws Throwable {
	    
		driver.findElement(By.id("shopping_cart_container")).click();
	}

	@Then("^Cart Details Page is displayed$")
	public void cart_Details_Page_is_displayed() throws Throwable {
	    boolean status = driver.findElement(By.xpath("//div[@class='cart_list']")).isDisplayed();
	    assertEquals(status,true);
	    
	}

	@Then("^Cart Details contains added items$")
	public double cart_Details_contains_added_items() throws Throwable {
	    
		int size = driver.findElements(By.xpath("//div[@class='cart_list']//div[@class='cart_item']")).size();
		//Assert.assertEquals(actCartCount,size,"cart count verification");
		double itemPrice = 0;
		double totalItemPrice = 0;
		for(int i=1;i<=size;i++) {
			//String ItemName  = driver.findElement(By.xpath("//div[@class='cart_list']//div[@class='cart_item']["+i+"]//div[@class='inventory_item_name']")).getText();
			//Assert.assertEquals(ItemName,firstItemName)
			String ItemPriceTxt  = driver.findElement(By.xpath("//div[@class='cart_list']//div[@class='cart_item']["+i+"]//div[@class='inventory_item_price']")).getText();
			 ItemPriceTxt  = ItemPriceTxt.substring(ItemPriceTxt.indexOf("$")+1, ItemPriceTxt.length());
			itemPrice= Double.parseDouble(ItemPriceTxt);
			totalItemPrice =  totalItemPrice+itemPrice;
		}
		
		return totalItemPrice;
	}

	@Then("^User Verifies added items details$")
	public void user_Verifies_added_items_details() throws Throwable {
	    
	    
	}

	@When("^User clicks on Checkout button$")
	public void user_clicks_on_Checkout_button() throws Throwable {
	    
		driver.findElement(By.id("checkout")).click();
		
	}

	@Given("^User in Checkout page$")
	public void user_in_Checkout_page() throws Throwable {
	    boolean status = driver.findElement(By.id("checkout_info_container")).isDisplayed();
		assertEquals(status,true);
	}

	
	@When("^User enters firstName \"([^\"]*)\"  LastName \"([^\"]*)\" postalcode \"([^\"]*)\"$")
	public void user_enters_firstName_LastName_postalcode(String firstName, String lastName, String postalCode) throws Throwable {
	    
		driver.findElement(By.id("first-name")).sendKeys(firstName);
		driver.findElement(By.id("last-name")).sendKeys(lastName);
		driver.findElement(By.id("postal-code")).sendKeys(postalCode);
	}

	@When("^User clicks on continue button$")
	public void user_clicks_on_continue_button() throws Throwable {
	    
		driver.findElement(By.id("continue")).click();
	}

	@Then("^summary details page is displayed$")
	public void summary_details_page_is_displayed() throws Throwable {
	  boolean status=  driver.findElement(By.id("checkout_summary_container")).isDisplayed();
	  assertEquals(status,true);
	}

	@Then("^User Verifies total summary \"([^\"]*)\"$")
	public void user_Verifies_total_summary(String totalAmnt) throws Throwable {
	    
		String actTxt = driver.findElement(By.xpath("//div[@class='summary_subtotal_label']")).getText();
		actTxt =actTxt.substring(actTxt.toString().lastIndexOf("$"), actTxt.length());
		
		assertEquals(actTxt,totalAmnt);
	}

	@Given("^User in Summary page$")
	public void user_in_Summary_page() throws Throwable {
	    
		 boolean status=  driver.findElement(By.id("checkout_summary_container")).isDisplayed();
		  assertEquals(status,true);
	}

	@When("^User clicks on Finish button$")
	public void user_clicks_on_Finish_button() throws Throwable {
	    
		driver.findElement(By.id("finish")).click();
	}

	@Then("^order confirmation message is displayed$")
	public void order_confirmation_message_is_displayed() throws Throwable {
	    
		String actCheckoutCompleteTxt = driver.findElement(By.xpath("//*[@class='complete-header']")).getText();
		assertEquals(actCheckoutCompleteTxt,"THANK YOU FOR YOUR ORDER");
	}



}
