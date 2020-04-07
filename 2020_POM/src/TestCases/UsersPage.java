package TestCases;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.TestBase;
import pageObjects.HomePage;

public class UsersPage extends TestBase {

	public UsersPage() throws FileNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	// Add user btn
	String addUserBtn = prop.getProperty("addUserBtn");
	// add UserPage Elemnts
	String eName = prop.getProperty("emp_name");
	String u_name = prop.getProperty("userName");
	String e_pwd = prop.getProperty("password");
	String cm_pwd = prop.getProperty("cnfrm_pwd");
	// save btn
	String saveBtn = prop.getProperty("save_btn");
	String delBtn= prop.getProperty("del_btn");
	
	//countUser
	String findElmns =  prop.getProperty("findElmns");
	//click ok pf del_popup
	String del_ok_popup =  prop.getProperty("del_ok_popup");	
	
	
	 

	public void addNewUser() throws InterruptedException {
		click_On_Button(addUserBtn);
		input_data(eName);
		input_data(u_name);
		input_data(e_pwd);
		input_data(cm_pwd);
		Thread.sleep(1000);
		click_On_Button(saveBtn);
	}

	public void deleteUser() throws InterruptedException {
		String linkText;

		int count = getElmn(findElmns).size();
		
		List<String> lt = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			linkText = getElmn(findElmns).get(i).getText();
			lt.add(linkText);
		}

		Iterator<String> itr = (Iterator<String>) lt.iterator();

		while (itr.hasNext()) {
			String linkName = itr.next();
			if (lt.contains(linkName)) {
				driver.findElement(
						By.xpath("//a[contains(text(),'" + linkName + "')]/parent::td/preceding-sibling::td/input"))
						.click();
			} else {
				System.out.println("no User found");
			}
		}
		
		click_On_Button(delBtn);
		Thread.sleep(1000);
		//Click on Delpopup
		click_On_Button(del_ok_popup);
		
	}

}
