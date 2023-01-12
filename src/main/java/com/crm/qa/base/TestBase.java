package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener e_listener;
	
	
	public TestBase(){
		try{
		prop = new Properties();
		FileInputStream ip = new FileInputStream("D:\\EclipseProjects\\FreeCrmTest\\src\\main\\java\\com\\"
				+ "crm\\qa\\config\\Config.properties");
		prop.load(ip);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}					
	}
	
	public static void initialization() {
		String browserName = prop.getProperty("browser");
		if(browserName.equals("Chrome")){
			System.setProperty("webdriver.chrome.driver", "D:\\EclipseProjects\\Drivers\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equals("FF")) {
			System.setProperty("webdriver.geckod.driver","D:\\EclipseProjects\\Drivers\\geckodriver-v0.32.0-win32\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		//initializing the EventFiringWebDriver and registering EventFiringWebDriver  with the WebEventListenerHandler
		e_driver= new EventFiringWebDriver(driver);
		// Creating object of eventlistenerHandler for registering with eventFiringwebDriver
		e_listener = new WebEventListener();
		e_driver.register(e_listener);
		driver=e_driver;
		
        //MAximizing browser window and implementing the timeouts
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		//Load project url
		driver.get(prop.getProperty("url"));
	}
	

}
