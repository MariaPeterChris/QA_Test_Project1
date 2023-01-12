package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.*;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase{
	
	LoginPage  loginPage;
	HomePage homePage;
	
	//Calling constructor of parent class - for properties loading
	public LoginPageTest() {
		super();
	}
	
	//Initialize and create object for LoginPage
	@BeforeMethod
	public void SetUp() {
		initialization();
		loginPage = new LoginPage();
	}
	
	//TestCases
	@Test(priority=1)
	public void loginPageTitleTest() {
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "CRMPRO - CRM software for customer relationship management, sales, and support.");	
	}
	
	@Test(priority=2)
	public void validateCRMLogoTest() {
		boolean logo = loginPage.verifyCrmLogo();
		Assert.assertTrue(logo);
	}
	
	@Test(priority=3)
	public void loginTest() {
		homePage=loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	
	
	
	
	// Driver close
	@AfterMethod
	public void Close() {
		driver.quit();
	}
	
	
	
	

}
