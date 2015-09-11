package org.rm.automation.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import org.rm.automation.utils.BrowserManager;

public class TestListener implements ITestListener {
	WebDriver driver=null;
	String filePath = System.getProperty("user.dir")+"\\Screenshots\\";
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd#HH.mm.ss");
	Date date = new Date();
	String current_datetime = dateFormat.format(date).toString();
	
	   
    @Override
    public void onTestFailure(ITestResult result) {
    	System.out.println("***** Error "+result.getName()+" test has failed *****");
    	String methodName= result.getName().toString().trim();
    	takeScreenShot(methodName);
    }
    
    public void takeScreenShot(String methodName) {
    	methodName+=current_datetime+".png";    	
    	System.out.println("Taking the screenshot in: "+filePath);    	
    	//get the driver
    	driver=BrowserManager.getInstance().getBrowser();
    	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	//The below method will save the screen shot in d drive with test method name
    	try {
    		FileUtils.copyFile(scrFile, new File(filePath+methodName));
    		System.out.println("*** Screen shot stored in "+filePath+" ***");
    		} 
    	catch (IOException e) {
    		e.printStackTrace();
    		}       
    }
	public void onFinish(ITestContext context) {}
  
    public void onTestStart(ITestResult result) {   }
  
    public void onTestSuccess(ITestResult result) {   }

    public void onTestSkipped(ITestResult result) {   }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {   }

    public void onStart(ITestContext context) {   }
}  
