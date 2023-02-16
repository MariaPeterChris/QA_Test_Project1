package com.crm.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {
	
	//Page Factory or Object Repository
	@FindBy(xpath="//td[contains(text(),'User: Maria Peter')]")
	WebElement userNameLabel;
	
	@FindBy(xpath="//a[@title='Contacts']")
	WebElement contactsLink;
	
	@FindBy(xpath="//li/a[@title='New Contact']")  
	WebElement newContactsLink;
	
	@FindBy(xpath="//a[@title='Deals']")
	WebElement dealsLink;
	
	@FindBy(xpath="//a[@title='Tasks']")
	WebElement tasksLink;
	
	@FindBy(xpath="//li/a[@title='New Task']")
	WebElement newTaskLink;
	
	@FindBy(linkText="Full Search Form")
	WebElement FullSearchForm;
	
	//Initialize page Elements using initElements
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions in the homePage
	public String verifyHomePageTitle() {
		return driver.getTitle();
	}
	
	public boolean verifyHomePageLabel() {
		return userNameLabel.isDisplayed();		
	}
	
	public ContactsPage clickContactsLink() {
		contactsLink.click();
		return new ContactsPage();
	}
	
	public DealsPage clickDealsPageLink() {
		dealsLink.click();
		return new DealsPage();
	}
	
	public TasksPage clickTasksPageLink() {
		tasksLink.click();
		return new TasksPage();
	}
	
	public void clickOnCreateNewContact() {
		Actions actions = new Actions(driver);
		actions.moveToElement(contactsLink).build().perform();
		actions.moveToElement(newContactsLink).build().perform();
		newContactsLink.click();	
	}
	
	public void clickOnCreateNewTask() {
		Actions actions= new Actions(driver);
		actions.moveToElement(tasksLink).build().perform();
		actions.moveToElement(newTaskLink).build().perform();
		newTaskLink.click();
	}
	
	public void clickFullSearchForm() {
		
		Actions actions= new Actions(driver);
		actions.moveToElement(tasksLink).build().perform();
		actions.moveToElement(FullSearchForm).build().perform();
		FullSearchForm.click(); 
		
	}

}
