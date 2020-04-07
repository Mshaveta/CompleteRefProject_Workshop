package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

 

public class TestBase {
	public static Properties prop;
	public static WebDriver driver;
	public static By by;
	public static String url;
	public String browserName;
	public String dPath;
	
	public TestBase() throws FileNotFoundException{
		try{ 
			prop = new Properties();
			String propFoileURL = System.getProperty("user.dir")+"\\src\\config\\config.properties";
			FileInputStream fis = new FileInputStream(propFoileURL);
			prop.load(fis);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public  void  open_Browser() 
	{
		browserName = prop.getProperty("browser");
		 
		dPath = prop.getProperty("driverPath");
		//System.out.println(browserName+"--------"+dPath);
		//System.out.println(driver);
		try {
			
			if (browserName.equalsIgnoreCase("Firefox")) {
				url = System.getProperty("user.dir")+"\\src\\"+dPath;
				System.setProperty("webdriver.gecko.driver", url);
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("chrome")) {
				url = System.getProperty("user.dir")+"\\src\\"+dPath;
				System.setProperty("webdriver.chrome.driver",url);
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("IE")) {
				url = System.getProperty("user.dir")+"\\src\\"+dPath;
				System.setProperty("webdriver.ie.driver",
						url);
				driver = new InternetExplorerDriver();
			}
		} catch (Exception e) { 
			System.out.println(e.getMessage());
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
//		driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
//		
		System.out.println(driver);
		
		enter_URL();
	}
	
	private  String enter_URL() {
		url = prop.getProperty("url");
		driver.navigate().to(url);
		String actualUrl = driver.getCurrentUrl();
		return actualUrl;
	}
	 
	public String validateTitle(){
		return driver.getTitle();
			 
	}
	 public  By locatorValue(String locatorTpye, String value) 
	 {
		
		switch (locatorTpye) {
		case "id":
			by = By.id(value);
			break;
		case "name":
			by = By.name(value);
			break;
		case "xpath":
			by = By.xpath(value);
			break;
		case "css":
			by = By.cssSelector(value);
			break;
		case "linkText":
			by = By.linkText(value);
			break;
		case "partialLinkText":
			by = By.partialLinkText(value);
			break;
		default:
			by = null;
			break;
		}
		
		return by;
		 
		
		 
	} 
	 
	public String input_data(String locator)
	{
		Random rand = new Random(); 
		String[] arr = locator.split("###");
		String locatorType = arr[0];
		String locValue = arr[1];
		String locData = arr[2];
		
		if(locData.contains("@mul_user")) {
			String test_data_arr[] = locData.split("@mul_user");
			
			locData=test_data_arr[0]+rand.nextInt(1000);
		}else {
			locData=locData;
		}
		String returnTxt = null;
		try {
			by = locatorValue(locatorType,locValue); 
			driver.findElement(by).sendKeys(locData);
			returnTxt =  driver.findElement(by).getText();
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to enter text" + e);
		}
		return returnTxt;
	}
	
	 public void click_On_Link(String locatorType, String value) 
	 {
		try {
			By locator;
			locator = locatorValue(locatorType, value);
			WebElement element = driver.findElement(locator);
			element.click();
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to enter text" + e);
		}
	} 

	public  void click_On_Button(String locator) 
	{
		String[] arr = locator.split("###");
		String locatorType = arr[0];
		String locValue = arr[1];
	 
		try {
			 
			by = locatorValue(locatorType, locValue);
			WebElement element = driver.findElement(by);
			element.click();
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to perform click" + e);
		}
	}
	
	
	public  boolean isElmDisplay(String locator) 
	{
		boolean flag = false ;
		String[] arr = locator.split("###");
		String locatorType = arr[0];
		String locValue = arr[1];
		 
		try {
			 
			by = locatorValue(locatorType, locValue);
			WebElement element = driver.findElement(by);
			flag = element.isDisplayed();
		} catch (NoSuchElementException e) {
			e.getStackTrace();
		}
		return flag;
	}
	
	
	public  void clear(String locatorType, String value) 
	{
		try {
			By locator;
			locator = locatorValue(locatorType, value);
			WebElement element = driver.findElement(locator);
			element.clear();
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to perform click" + e);
		}
	}
	
	public  List<WebElement> getElmn(String locator) 
	{
		List<WebElement> listElmns = null;
		String[] arr = locator.split("###");
		String locatorType = arr[0];
		String locValue = arr[1];
	 
		try {
			 
			by = locatorValue(locatorType, locValue);
			listElmns = driver.findElements(by);
			 
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to perform click" + e);
		}
		
		return listElmns;
	}
	
	 
	
	public void close_Browser() {
		driver.quit();
	} 
	
	 
}
