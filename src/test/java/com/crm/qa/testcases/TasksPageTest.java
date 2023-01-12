package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.TasksPage;
import com.crm.qa.util.TestUtil;

public class TasksPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	TasksPage tasksPage;
	
	String sheetName="Tasks";
	
	public TasksPageTest() {
		super();
	}
	
	@BeforeMethod
	public void SetUp() {
		initialization();
		loginPage = new LoginPage();
		tasksPage= new TasksPage();
		homePage=loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil=new TestUtil();
	}
	
	
	@Test(priority=1)
	public void verifyTasksPage() {
		testUtil.switchToFrameMain();
		homePage.clickTasksPageLink();
		boolean label=tasksPage.verifyTasksPage();
		Assert.assertTrue(label);
	}
	
	@DataProvider
	public Object[][] getCRMTasksTestData(){
		Object [][] data = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=2, dataProvider="getCRMTasksTestData")
	public void createNewTask(String title, String description) {
		testUtil.switchToFrameMain();
		homePage.clickOnCreateNewTask();
		tasksPage.createNewTask(title, description);
		boolean afterSaveLabel=tasksPage.verfiyTaskSaving();
		Assert.assertTrue(afterSaveLabel);
	}
	
	
	
	@AfterMethod
	public void close() {
		driver.quit();
	} 

}
