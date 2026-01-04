package resource;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Listeners extends CommonAPI implements ITestListener{
	
	ExtentReports extents=ExtentReportng.ExtentReport();
	ExtentTest Test;
    ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		
		Test=(extents.createTest(result.getTestContext().getAttribute("testName").toString()));
		extentTest.set(Test);
		//ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String logTest="<b>Test Method "+result.getMethod().getMethodName()+" is successful!<b>";
		Markup m=MarkupHelper.createLabel(logTest, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS,m);
		//ITestListener.super.onTestSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailure(result);
		
		extentTest.get().skip("<details><summary><b><font color=red>" + "Exception Occured,Click to see details: "+"</font></b></summary>" +
				result.getThrowable()+"</details> \n");
				WebDriver driver=null;
				
				String FailedTestCaseName=result.getTestContext().getAttribute("testName").toString();
				try {
					driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver")
							.get(result.getInstance());
					
				}catch (Exception e)
				{
					
				}
				try
				{
					extentTest.get().addScreenCaptureFromPath(getScreenShotPath(FailedTestCaseName,driver),
							FailedTestCaseName);
				}
				catch(IOException e)
				{
					e.printStackTrace();
					
				}
				
				String logTest="<b>Test Method "+FailedTestCaseName+" is Failed!<b>";
				Markup m=MarkupHelper.createLabel(logTest, ExtentColor.RED);
				extentTest.get().log(Status.FAIL,m);
				
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().skip("<details><summary><b><font color=YELLOW>" + "Exception Occured,Click to see details: "+"</font></b></summary>" +
		result.getThrowable()+"</details> \n");
		WebDriver driver=null;
		
		String FailedTestCaseName=result.getTestContext().getAttribute("testName").toString();
		try {
			driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
			
		}catch (Exception e)
		{
			
		}
		
		String logTest="<b>Test Method "+FailedTestCaseName+" is Skipped!<b>";
		Markup m=MarkupHelper.createLabel(logTest, ExtentColor.YELLOW);
		extentTest.get().log(Status.SKIP,m);
		
		//ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
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
		extents.flush();
		//ITestListener.super.onFinish(context);
	}

}
