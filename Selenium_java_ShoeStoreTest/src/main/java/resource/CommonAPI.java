package resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CommonAPI {
	
	public static WebDriver driver;
	public  Properties prop;
	public  FileInputStream fis;
	
	public  void readDataProperties() throws IOException
	{
		prop=new Properties();
		fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resource\\data.properties");
		prop.load(fis);
	}
	

	public WebDriver initializer() throws IOException
	{
		readDataProperties();
		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("Chrome"))
{
	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resource\\chromedriver.exe");
	DesiredCapabilities capability = new DesiredCapabilities();
	capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
	ChromeOptions c= new ChromeOptions();
	c.setPageLoadStrategy(PageLoadStrategy.NORMAL);
	c.merge(capability);
	driver=new ChromeDriver(c);
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get(prop.getProperty("url"));
	
			
}
		else if(browserName.equalsIgnoreCase("IE"))
		{
			//code for IE
		}
		return driver;
	}
	
	
	
	
	public  XSSFSheet getsheet(String SheetName) throws IOException
	{
		//readDataProperties();
		//FileInputStream fis=new FileInputStream(new File("C:\\demo\\student.xls"));  
		Properties prop=new Properties();

	 FileInputStream fis= new 	FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resource\\data.properties");
	 prop.load(fis);
	 File Excel=new File(prop.getProperty("testdataPath"));
	 FileInputStream fis1=new FileInputStream(Excel);
     XSSFWorkbook workbook= new XSSFWorkbook(fis1);
	 int sheets=workbook.getNumberOfSheets();
	 XSSFSheet sheet=null;
	 for (int i=0;i<sheets;i++) {
		 if(workbook.getSheetName(i).equals(SheetName))
		 {
			 sheet=workbook.getSheetAt(i);
			 workbook.close();
			 break;
		 }
	 }
	 return sheet;
	}
	
	public  Object[][] dataread(String SheetName) throws IOException
	{
		XSSFSheet sheet=getsheet(SheetName);
				int rowCount=sheet.getLastRowNum();
		        int columnCount=sheet.getRow(0).getLastCellNum();
		        int col=0;
		        for(int k=0;k<columnCount;k++)
		        {
		        	if(sheet.getRow(0).getCell(k).getStringCellValue().equalsIgnoreCase("Indicator"))
		        	{
		        		col=k;
		        		break;
		        	}
		        }
		        int counter=0;
		        for(int i=0;i<rowCount;i++)
		        	
		        {
		        	if(sheet.getRow(i+1).getCell(col).getStringCellValue().equalsIgnoreCase("Yes"))
		        	{
		        		counter++;
		        		
		        	}
		        }
		        
		        Object[][] ob= new Object[counter][1];
		        int loopCount=0;
		        for(int i =1;i<=rowCount;i++)
		        {
		        	Map<Object,Object> datamap=new HashMap<Object,Object>();
		        	if(sheet.getRow(i) !=null && sheet.getRow(i).getCell(col).getStringCellValue().equalsIgnoreCase("Yes"))
		        	{
		        		loopCount++;
		        	
		        	for(int j=0;j<columnCount;j++)
		        	{
		        		if(sheet.getRow(i).getCell(j).getCellType()==CellType.STRING)
		        		{
		        			datamap.put(sheet.getRow(0).getCell(j).toString(),sheet.getRow(i).getCell(j).getStringCellValue());
		        					        		}
		        		else if(sheet.getRow(i).getCell(j).getCellType()==CellType.NUMERIC)
		        		{
		        			datamap.put(sheet.getRow(0).getCell(j).toString(),sheet.getRow(i).getCell(j).getNumericCellValue());
		        		} 
		        	}
		        	ob[loopCount-1][0]=datamap;
		        	
		        }
		        	else {
		        		System.out.println("Not Executed!");
		        	}
		        }
		       return ob; 	
	}
	
	public String getScreenShotPath(String FailedTestCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot scrshot=(TakesScreenshot)driver;
		File source = scrshot.getScreenshotAs(OutputType.FILE);
		String destinationPath=System.getProperty("user.dir") + "//Report//" + FailedTestCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationPath));
		return destinationPath;
	}
	
}
