package DataDrivenFramework.DataDrivenFramework;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resource.Base;
import resource.ExtentReport;


public class listners extends Base implements ITestListener 
{
	WebDriver driver;
	ExtentReports reports= ExtentReport.getReportObject();
	ExtentTest test; 
ThreadLocal<ExtentTest> tl=new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		String MethodName= result.getMethod().getMethodName();
		test=reports.createTest(MethodName);
		tl.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
	//	ITestListener.super.onTestSuccess(result);
	//	test.p
		
		tl.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result ) {
		WebDriver driver = null;
		// TODO Auto-generated method stub
	//	ITestListener.super.onTestFailure(result);
		
		String MethodName= result.getMethod().getMethodName();
		tl.get().fail("TestFailed");
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getScreenshot(MethodName,driver);
		try {
			tl.get().addScreenCaptureFromPath(getScreenshot(MethodName,driver), MethodName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	//	ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	//	ITestListener.super.onFinish(context);
	reports.flush();
	}



}
