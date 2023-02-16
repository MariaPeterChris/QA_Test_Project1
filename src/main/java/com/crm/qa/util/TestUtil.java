package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {
	
	
	public static long PAGE_LOAD_TIMEOUT=20;
	public static long IMPLICIT_WAIT=10;
	
	
	public static String TESTDATA_SHEET_PATH="D:\\EclipseProjects\\FreeCrmTest\\src\\main\\java"
			+ "\\com\\crm\\qa\\testdata\\FreeCrmTestData.xlsx";
	
	 static Workbook book;
	 static Sheet sheet;
	
	 //Switching to the frame in HomePage for clicking the heads
	public void switchToFrameMain() {
		driver.switchTo().frame("mainpanel");
	}
		
	//Calling test data from the Excel sheet
	public static Object[][] getTestData(String sheetName){
		FileInputStream file= null;
		try{
			file = new FileInputStream(TESTDATA_SHEET_PATH);			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try { 
			book=WorkbookFactory.create(file);
		}catch(IOException e) {
			e.printStackTrace();
		}
		sheet=book.getSheet(sheetName);
		Object[][] data= new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		//sheet gets iterated on the basis of rows and columns
		for(int i =0;i<sheet.getLastRowNum();i++) {
			for(int k=0;k<sheet.getRow(0).getLastCellNum();k++) {
				data[i][k]=sheet.getRow(i+1).getCell(k).toString();
			}
		}
		return data;		
	}
	
	public static void takeScreenshotAtEndOfTest() throws IOException{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destFile_path = System.getProperty("D:\\EclipseProjects\\FreeCrmTest");	
		FileUtils.copyFile(scrFile,new File(destFile_path + "/screenshots/" +  System.currentTimeMillis() + ".png"));
		
	}
	
	public void scrollPage() {
		JavascriptExecutor js = (JavascriptExecutor)driver;  
		js.executeScript("scrollBy(0, 4500)");
	}
	
		
}
