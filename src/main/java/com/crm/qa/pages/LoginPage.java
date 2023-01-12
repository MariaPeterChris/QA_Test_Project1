package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	//Page Factory OR Object Repository
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath="//a[@class='navbar-brand']")
	WebElement SignUpBtn;
	
	@FindBy(xpath="//img[@class='img-responsive']")
	WebElement crmLogo;
	
	
	//Initializing page elements using initElements
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public boolean verifyCrmLogo() {
		return crmLogo.isDisplayed();
	}
	
	public HomePage Login(String usrnme, String passwd) {
		username.sendKeys(usrnme);
		password.sendKeys(passwd);
		loginBtn.click();
		
		return new HomePage();
	}
	
	
	
	
	

}
