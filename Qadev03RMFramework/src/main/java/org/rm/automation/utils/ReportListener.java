package org.rm.automation.utils;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ReportListener extends TestListenerAdapter{
	  
	  @Override
	  public void onTestSuccess(ITestResult testResult){
		  String testName = testResult.getTestContext().getName();
		  LogManager.info("Test : \"" + testName + "\" PASSED" );
		  ReportManager.appendTestCaseName(testName);
	  }
	  
	  @Override
	  public void onTestFailure(ITestResult testResult){
		  String testName = testResult.getTestContext().getName();
		  
		  String errorMessage = testResult.getThrowable().getMessage();
		  LogManager.info("Test : \"" + testName + "\" FAILED" );
		  
		  /*
		   * TestListener's going to take screenshots. 
		   */
		  String filePath = TestListener.takeScreenShot(testName);
		  if(testResult.getThrowable().getClass().isAssignableFrom(AssertionError.class)){
			  ReportManager.appendTestCaseErrorMessage(testName, errorMessage);
		  }
		  else{
			  ReportManager.appendTestCaseName(testName);
		  }
		  ReportManager.appendImageHyperLink(filePath);
	  }
	  
	  @Override
	  public void onTestStart(ITestResult testResult){
		  String testName = testResult.getTestContext().getName();
		  LogManager.info("Test : \"" + testName + "\" STARTED" );
		  ReportManager.appendTestCaseName(testName);
	  }
}
