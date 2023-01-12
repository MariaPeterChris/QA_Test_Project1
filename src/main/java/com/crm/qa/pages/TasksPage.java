package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class TasksPage extends TestBase {
	
	@FindBy(xpath="//td[contains(text(),'Tasks')]")
	WebElement tasksLabel;
	
	@FindBy(xpath="//td/input[@id='title']")
	WebElement title;
	
	@FindBy(xpath="//td/textarea[@id='description']")
	WebElement description;
	
	@FindBy(xpath="//input[@type='submit' and @value='Save']")
	WebElement saveBtn;
	
	@FindBy(xpath="//td[contains(text(),'Task:')]")
	WebElement afterSaveVerificationLabel;
	
	public TasksPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions to be performed
	public boolean verifyTasksPage() {
		return tasksLabel.isDisplayed();		
	}
	
	public void createNewTask(String title, String description) {
		this.title.sendKeys(title);
		this.description.sendKeys(description);
		saveBtn.click();
	}
	
	public boolean verfiyTaskSaving() {
		return afterSaveVerificationLabel.isDisplayed();		
	}
	
	

}
