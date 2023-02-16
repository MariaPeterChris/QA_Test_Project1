package com.crm.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
	
	@FindBy(xpath="//td/fieldset/legend[contains(text(),'Search Task')]")
	WebElement verifyFullSearchFormPage;
	
	@FindBy(xpath="//input[@name='cs_keyword']")
	WebElement search_title;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement search_Btn;
	
	@FindBy(xpath="//form[1]/table[2]/tbody[1]/tr[3]/td[count-1]")
	WebElement showingAllTasksRowCount;
	
	@FindBy(xpath="(//i[@title='Delete'])[4]")
	WebElement rowDeleteIcon;
	
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
	
	public boolean verifyFullSearchForm() {
		return verifyFullSearchFormPage.isDisplayed();
	}
	
	public void alertSwitchAccept() {
	driver.switchTo().alert().accept();
	
	}
	
	public void searchTask(String taskname) {
		search_title.click();
		search_title.sendKeys(taskname);	
	}
	
	public void searchButtonClick() {
		search_Btn.click();
	}
	
	public int  taskCount() {		
		List<WebElement> tasksCount=driver.findElements
				(By.xpath("(//i[@title='Delete'])"));
		int size=tasksCount.size();
		return size;
	}
	
	public void clickDeleteOnLastTask() { 
		rowDeleteIcon.click();				
	}
	
	public void clickPagination() {
		
	}

}
