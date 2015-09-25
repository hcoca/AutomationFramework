package org.rm.automation.utils;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * ReportListener extends the TestNG TestListenerAdapter class, which implements from IResultListener2, it means
 * that onTestSuccess, onTestFailure and onTestStart must be overwritten.
 * 
 * Also TestListenerAdapter stores all the tests
 * that were run.  You can retrieve these results with the
 * following methods:
 * getPassedTests()
 * getFailedTests()
 * getSkippedTests()
 *
 */
public class ReportListener extends TestListenerAdapter{

	/* 
	 * The method receives an ITestResult that describes the result of a test.
	 */
	@Override
	  public void onTestSuccess(ITestResult testResult){
		  String testName = testResult.getTestContext().getName();
		  LogManager.info("Test : \"" + testName + "\" PASSED" );
		  ReportManager.appendTestCaseName(testName);
	  }
	  
	/*
	 * The method receives an ITestResult that describes the result of a test.
	 */
	@Override
	  public void onTestFailure(ITestResult testResult){
		  String testName = testResult.getTestContext().getName();
		  
		  String errorMessage = testResult.getThrowable().getMessage();
		  LogManager.error("Test Case Name: \"" + testName + "\" FAILED" );
	      LogManager.error("Error Massage:" + errorMessage);
		  /*
		   * TestListener is the class that is going to take screenshots. 
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
	  
	/* 
	 * The method receives an ITestResult that describes the result of a test.
	 */
	@Override
	  public void onTestStart(ITestResult testResult){
		  String testName = testResult.getTestContext().getName();
		  LogManager.info("Test : \"" + testName + "\" STARTED" );
		  ReportManager.appendTestCaseName(testName);
	  }
}
