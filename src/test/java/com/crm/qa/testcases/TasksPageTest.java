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
	
	String taskSheet="Tasks";
	
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
	
	//data provider for getting data from excel and assigning it to each test cases
	@DataProvider
	public Object[][] getCRMTasksTestData(){
		Object [][] data = TestUtil.getTestData(taskSheet);
		return data;
	}
	
	@Test(priority=2, dataProvider="getCRMTasksTestData")
	public void createNewTask(String title, String description, String searchText) {
		testUtil.switchToFrameMain();
		homePage.clickOnCreateNewTask();
		tasksPage.createNewTask(title, description);
		boolean afterSaveLabel=tasksPage.verfiyTaskSaving();
		Assert.assertTrue(afterSaveLabel);
	}
	
	@Test(priority=3, dataProvider="getCRMTasksTestData")
	public void searchUsingFullSearchForm(String title, String description,String searchText) {
		testUtil.switchToFrameMain();
		homePage.clickFullSearchForm();
		boolean searchPageLabel=tasksPage.verifyFullSearchForm();
		Assert.assertTrue(searchPageLabel);
		
		tasksPage.searchTask(searchText);
		tasksPage.searchButtonClick();
	}
	
	@Test(priority=4,dataProvider="getCRMTasksTestData")
	public void deleteLastTaskafterSearch(String title, String description,String searchText) {
		testUtil.switchToFrameMain();
		homePage.clickFullSearchForm();
		boolean searchPageLabel=tasksPage.verifyFullSearchForm();
		Assert.assertTrue(searchPageLabel);		
		tasksPage.searchTask(searchText);
		tasksPage.searchButtonClick();
		
		testUtil.scrollPage();
		tasksPage.taskCount();
		System.out.println("Count "+tasksPage.taskCount());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tasksPage.clickDeleteOnLastTask();
		tasksPage.alertSwitchAccept();
	}
	
	
	
	@AfterMethod
	public void close() {
		driver.quit();
	}  

}
