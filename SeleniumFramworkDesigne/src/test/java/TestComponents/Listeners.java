package TestComponents;

import java.io.IOException;

import org.checkerframework.checker.units.qual.t;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Resources.ExtentReportNG;

public class Listeners extends BaseTests implements ITestListener{
	
	ExtentReports extent = ExtentReportNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	 @Override
	    public void onTestStart(ITestResult result) {
	        test = extent.createTest(result.getMethod().getMethodName());
	        extentTest.set(test);
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	    	extentTest.get().log(Status.PASS, "Test is pass");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	  
	    	//the below line will through the error message
	    	extentTest.get().fail(result.getThrowable());
	    	try {
				driver =  (WebDriver) result.getTestClass().getRealClass().getField("driver")
						.get(result.getInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	    	
	    	String filePath = null;
			try {
				filePath = getScreenShot(result.getMethod().getMethodName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			extentTest.get().addScreenCaptureFromBase64String(filePath, result.getMethod().getMethodName());
	    	
	    	
	    	//screen shot, //Attach to report
	    	
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        // This method is invoked if the test method is skipped.
	        System.out.println("Test Skipped: " + result.getName());
	    }

	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	        // This method is invoked if the test fails but the failure percentage is within the success criteria.
	        System.out.println("Test Failed but within Success Percentage: " + result.getName());
	    }

	    @Override
	    public void onStart(org.testng.ITestContext context) {
	        // This method is invoked before any test methods are run.
	        System.out.println("Test Suite Started: " + context.getName());
	    }

	    @Override
	    public void onFinish(org.testng.ITestContext context) {
	        // This method is invoked after all test methods are run.
	    	extent.flush();
	    }

}
