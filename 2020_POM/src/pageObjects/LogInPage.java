package pageObjects;
import java.io.FileNotFoundException;

import org.openqa.selenium.*;
import base.TestBase;
 
 
public class LogInPage extends TestBase 
{
	public LogInPage() throws FileNotFoundException {
		super();
	}

	public String validatePageTitle(){
		return validateTitle();	 
	}
	
	public boolean validatePageLogo(String logo){
		return isElmDisplay(logo);
	}
	
	public HomePage login(String un, String pwd, String btn) throws FileNotFoundException{
		 
		input_data(un);
		input_data(pwd);
		click_On_Button(btn);
		return new HomePage();
	}
}
