package resource;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportng {
	
	static ExtentReports extents;
	
	public static ExtentReports ExtentReport()
	{
		String Path= System.getProperty("user.dir")+"//Report//index.html";
		ExtentSparkReporter reporter= new ExtentSparkReporter(Path);
		reporter.config().setDocumentTitle("Test Results!");
		reporter.config().setReportName("Shoe Store Automation");
		reporter.config().setTheme(Theme.DARK);
		extents=new ExtentReports();
		extents.attachReporter(reporter);
		extents.setSystemInfo("Tester", "Farhana Rashid");
		return extents;
	}
	

}
