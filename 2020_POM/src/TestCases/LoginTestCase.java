package TestCases;

import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LogInPage;
 

public class LoginTestCase extends LogInPage  { 
	
	LogInPage lp;
	HomePage hp ;
	
	String login_uname = prop.getProperty("loginUserName");
	String login_pwd = prop.getProperty("loginPassword");
	String login_btn = prop.getProperty("loginBtn");
	
	public LoginTestCase() throws FileNotFoundException {
		super();	
	}

	 
	@BeforeClass
	public void launchBrowser() throws FileNotFoundException {
	 
		open_Browser();
		lp = new LogInPage();
	}
	
	 
	@Test(priority=0)
	public void fbLoginTest() throws FileNotFoundException, InterruptedException{
		 
		 hp = lp.login(login_uname, login_pwd, login_btn);
		 Thread.sleep(2000); 
		  
		 
	}
	
	 
	
	 
	
	 
}  
