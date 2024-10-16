package com.gaurav.listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CommonTestListener extends TestListenerAdapter {



	/*************************************************************
	 * @author :Gaurav.Kakkar@gaurav.com.au
	 * @Method_Name: MainframeExtentListener
	 * @Description : Constructor Calling Parent Class Constructor
	 ************************************************************/
	public CommonTestListener() {
		super();
	}

	/*************************************************************
	 * @author :Gaurav.Kakkar@gaurav.com.au
	 * @Method_Name: onStart
	 * @Description : Overriding the onStart Method for Loading the Property files
	 *              and Database connection
	 ************************************************************/
	public void onStart(ITestContext iTestContext) {
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		
	}

	/*************************************************************
	 * @author :Gaurav.Kakkar@gaurav.com.au
	 * @Method_Name: onTestFinish
	 * @Description : Overriding the onTestFinish Method for closing any browser
	 *              instance and connection objects
	 ************************************************************/
	
	public void onTestFinish(ITestResult iTestResult) {

	}

	/*************************************************************
	 * @author :Gaurav.Kakkar@gaurav.com.au
	 * @Method_Name: onFinish
	 * @Description : Overriding the onFinish Method for closing any browser
	 *              instance and connection objects
	 ************************************************************/
	public void onFinish(ITestContext iTestContext) {
				
	}
	
	@Override
	public void onTestFailure(ITestResult iTestResult) {
		// Get the test Context
		ITestContext testContext = iTestResult.getTestContext();
		ExtentTest extentTest = (ExtentTest) testContext.getAttribute("ExtentTest");
		String Base64Image = "";
		
		if(testContext.getAttribute("TestTech").toString().equals("Web")) {
			WebDriver driver = (WebDriver) testContext.getAttribute("driver");
			Base64Image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			extentTest.log(LogStatus.FAIL, "<html><body><font color=\"red\">Failure Screenshot</font></body></html>", 
					extentTest.addBase64ScreenShot("data:image/png;base64," + Base64Image));
		}
		

		// Construct the comments
		String testName = testContext.getName();
		if (!(iTestResult.getThrowable() == null)) {
			extentTest.log(LogStatus.FAIL, "<html><body><font color=\"red\">Failure Cause</font></body></html>", iTestResult.getThrowable());
		} else {
			extentTest.log(LogStatus.FAIL, testName, "No error message avaialble !!");
		}
		
		
		
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		ITestContext testContext = result.getTestContext();
		ExtentTest extentTest = (ExtentTest) testContext.getAttribute("ExtentTest");
	
		String Base64Image = "";
			if(result.getTestContext().getAttribute("TestTech").toString().equals("Web")){
					WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
					Base64Image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					extentTest.log(LogStatus.PASS, "<html><body><font color=\"green\">Final Screenshot</font></body></html>", 
							extentTest.addBase64ScreenShot("data:image/png;base64," + Base64Image));
				}
			
		
	}
	
}	
