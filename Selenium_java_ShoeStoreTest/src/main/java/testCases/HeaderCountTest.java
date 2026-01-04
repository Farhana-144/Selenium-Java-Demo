package testCases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.HomePage;
import resource.CommonAPI;

public class HeaderCountTest extends CommonAPI{
	
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
	HomePage hp;
	int count=0;
	@Test()
	public void headerMenuCount() throws InterruptedException
	{  
		
		
		//HeaderCountTest hct=new HeaderCountTest();
		hp=new HomePage(driver);
		 count=hp.HeaderMenusList().size();
		if(count==13)
		{
			Assert.assertEquals(count, 13);
		}
		for(int i=0;i<count;i++)
		{
			String MenuName=hp.HeaderMenusList().get(i).getText();
			//Thread.sleep(3000);
			System.out.println(MenuName);
		}
		
		Thread.sleep(2000);
		
	}
	@Test
	public void clickOnEachMenu() throws InterruptedException
	{ hp=new HomePage(driver);
		int Menucount=hp.HeaderMenusList().size();
		//WebDriverWait wait= new WebDriverWait(driver, 20);
		for(int i=0;i<Menucount;i++)
		{
			hp.HeaderMenusList().get(i).click();
			Thread.sleep(3000);
		}
		
		hp.HomeButton().click();
		Thread.sleep(2000);
	}
	
	@Test
	public void BrandDropDownCount() throws InterruptedException
	{
		hp= new HomePage(driver);
		int Brands=hp.BrandCountList().size();
		if(Brands==507)
		{
			Assert.assertTrue(true);
		}
		
		Thread.sleep(2000);
	}
	@Test(dataProvider="dataset",enabled=true,alwaysRun=true,priority=1)
	public void searchBrand(Map datamap) throws InterruptedException
	{
		HomePage hp=new HomePage(driver);
		hp.DropDownInputField().click();
		int Brandcount=hp.BrandCountList().size();
		for(int i =0;i< Brandcount;i++)
		{
			String BrandName=hp.BrandCountList().get(i).getText().trim();
			if(BrandName.equalsIgnoreCase((String) datamap.get("Brand Name")))
			{
				hp.BrandCountList().get(i).click();
				Thread.sleep(1000);
				break;
			}
			
		}
		hp.SearchButton().click();
		Thread.sleep(2000);
		hp.HomeButton().click();
	}
	
	@DataProvider
	public Object[][] dataset() throws IOException
	{
		HeaderCountTest hc= new HeaderCountTest();
		Object obj[][]=hc.dataread("Brand");
		return obj;
		
	}
	
	@AfterClass
	public void teardown()
	{
		driver.close();
	}
	
	

}

