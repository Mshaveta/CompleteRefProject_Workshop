package TestCases;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.HomePage;

public class HomeTestCase extends HomePage {
	HomePage hp;
	UsersPage up;
	String wlcm_link = prop.getProperty("welcm_lnk");
	String logOut_lnk = prop.getProperty("lgOut_lnk");
	String user_link = prop.getProperty("usersLink");
	String add_User_link = prop.getProperty("addUserBtn");
	String usersCount = prop.getProperty("count_addnew_users");

	public HomeTestCase() throws FileNotFoundException {
		super();
	}

	@Test(priority = 1)
	public void clickAdminLink() throws FileNotFoundException {
		up = homeDashBoard(user_link);
	}

	@Test(priority = 2)
	public void add_usersTestCase() throws FileNotFoundException, InterruptedException {

		Thread.sleep(2000);
		int count = Integer.parseInt(usersCount);
		for (int i = 1; i <= count; i++) {
			up.addNewUser();
			Thread.sleep(3000);
		}
	}

	@Test(priority = 3)
	public void delUsersTestCase() throws InterruptedException {
		up.deleteUser();
	}

	@Test(priority = 4)
	public void logOutTc() throws InterruptedException {
		Thread.sleep(5000);
		logOutFunc(wlcm_link, logOut_lnk);
		Thread.sleep(2000);
		driver.quit();
	}
	
//	@AfterSuite
//	public void tearDown() throws InterruptedException {
//		driver.quit();
//	}


}
