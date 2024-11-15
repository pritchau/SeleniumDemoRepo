package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;

import java.lang.reflect.Field;

import resources.Base;
import utilities.ExtentReport;

public class Listeners extends Base implements ITestListener {
	
	WebDriver driver = null;
	ExtentReports extentreport = ExtentReport.getExtentReport();
	ExtentTest extentTest;
	ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();

	
	@Override
	public void onTestStart(ITestResult result) {
		
		String testname = result.getName();
		extentTest = extentreport.createTest(testname);
		extentTestThread.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testname = result.getName();
		//extentTest.log(Status.PASS, "Test got pass");
		extentTestThread.get().log(Status.PASS, testname);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		WebDriver driver = null;
		
		String testMethodName = result.getName();
		
		//extentTest.fail(result.getThrowable());
		extentTestThread.get().fail(result.getThrowable());
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
		
		try {
			String screenshotFilePath= takeScreenshot(testMethodName,driver);
			extentTestThread.get().addScreenCaptureFromPath(screenshotFilePath);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}

	@Override
	public void onStart(ITestContext context) {
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extentreport.flush();
		
	}

}
