package testCases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObject.JanuaryPageObj;
import resource.CommonAPI;

public class JanuaryPageTest extends CommonAPI {
	
public  WebDriver driver;
	
	private ThreadLocal<String> testName = new ThreadLocal<String>();
	
	@BeforeMethod()
	public void BeforeMethod(Method method,Object[] testData,ITestContext ctx)
	{
		if(testData.length >0)
		{
			testName.set(method.getName());
			ctx.setAttribute("testName", testName.get());
			
		}
		else ctx.setAttribute("testName", method.getName());
	}
	
	@BeforeClass()
	public void driverinit() throws IOException
	{
		driver=initializer();
		
	}
	
	@Test()
	public void DisplayedBrand()
	{
		
		JanuaryPageObj jpo= new JanuaryPageObj(driver);
		jpo.January().click();
		int BrandCountInJan=jpo.ListOfBrandsinJan().size();
		if(BrandCountInJan==5)
		{
			Assert.assertTrue(true);
		}
		else 
			Assert.assertTrue(false, "Brand count is more than expected");
	}
	
	@AfterClass
	public void teardown()
	{
		driver.close();
	}
	

}
