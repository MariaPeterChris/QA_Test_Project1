package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {
	
	//PageFactory Or OBJ Repository
	@FindBy(xpath="//td[contains(text(),'Contacts')]")
	WebElement contactsPageLabel;
	
	@FindBy(xpath="//td/select[@name='title']")
	WebElement titleDropdown;
	
	@FindBy(id="first_name")
	WebElement firstName;
	
	@FindBy(id="surname")
	WebElement lastName;
	
	@FindBy(name="client_lookup")
	WebElement company;
	
	@FindBy(xpath="//input[@value='Save' and @type='submit']")
	WebElement saveBtn;
	
	//initialize page Elements
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions to be performed
	public boolean verifyContactsPageLabel() {
		return contactsPageLabel.isDisplayed();
	}
	
	public void createNewContact(String title, String fName, String lName, String Company) {
		Select select = new Select(titleDropdown);
		select.selectByValue(title);
		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		company.sendKeys(Company);
		saveBtn.click();		
	}

}
