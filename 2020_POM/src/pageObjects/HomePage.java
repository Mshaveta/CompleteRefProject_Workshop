package pageObjects;

import java.io.FileNotFoundException;

import org.openqa.selenium.By;

import TestCases.UsersPage;
import base.TestBase;

public class HomePage extends TestBase {
	
	public HomePage() throws FileNotFoundException {
		super();
		
	}

	public void logOutFunc(String wlcm_lnk , String lout_lnk) throws InterruptedException 
	{
		//welcome_link
		click_On_Button(wlcm_lnk);
		Thread.sleep(2000);
		//click on logout_link
		click_On_Button(lout_lnk); 
	}
	
	public UsersPage  homeDashBoard(String user_link) throws FileNotFoundException {
		click_On_Button(user_link);
		return new UsersPage();
	}
	
	
	 
}
