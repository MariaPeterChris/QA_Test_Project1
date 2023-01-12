package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase {

	ContactsPage contactsPage;
	HomePage homePage;
	LoginPage loginPage;
	TestUtil testUtil;
	
	String sheetName="Contacts";
	
	
	public ContactsPageTest(){
		super();		
	}
	
	@BeforeMethod
	public void SetUp() {
		initialization();
		loginPage = new LoginPage();
		contactsPage = new ContactsPage();
		homePage= loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil=new TestUtil();
		//contactsPage= homePage.clickContactsLink();
		
	}
	
	@Test(priority=1)
	public void verifyContactsPage() {
		testUtil.switchToFrameMain();
		homePage.clickContactsLink();
		boolean label = contactsPage.verifyContactsPageLabel();
		Assert.assertTrue(label);
	}
	
	
	@DataProvider
	public Object[][] getCRMTestData() {
		Object data[][]=TestUtil.getTestData(sheetName);
		return data;
		
	}
	
	@Test(priority=2, dataProvider="getCRMTestData")
	public void createNewContact(String Title, String FirstName, String LastName, String Company) {
		testUtil.switchToFrameMain();
		homePage.clickOnCreateNewContact();
		contactsPage.createNewContact(Title, FirstName, LastName, Company);		
	}
	
	@AfterMethod

	public void Close() {
		driver.quit();
	}
}
