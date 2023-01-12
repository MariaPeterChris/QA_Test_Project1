package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase {
	
	HomePage homePage;
	LoginPage loginPage;
	TestUtil testUtil;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void SetUp() {
		initialization();
		loginPage = new LoginPage();
		homePage= new HomePage();
		homePage= loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil = new TestUtil();
		
	}
	
	
	@Test(priority=1)
	public void homePageTitleTest() {
		String title = homePage.verifyHomePageTitle();
		Assert.assertEquals(title, "CRMPRO");		
	}
	
	@Test(priority=2)
	public void verifyUserNameLabel() {
		testUtil.switchToFrameMain();
		boolean usLabel = homePage.verifyHomePageLabel();
		Assert.assertTrue(usLabel);		
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest() {
		testUtil.switchToFrameMain();
		homePage.clickContactsLink();
	}
	
	
	
	//WebDriver Close
	@AfterMethod
	public void Close() {
		driver.quit();
	}
	

}
