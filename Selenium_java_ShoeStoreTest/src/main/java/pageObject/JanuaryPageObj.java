package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JanuaryPageObj {
	
WebDriver driver;
	
	public JanuaryPageObj(WebDriver driver) {
	// TODO Auto-generated constructor stub
		this.driver=driver;
}

	By BrandList=By.xpath("//*[@id='shoe_list']/li");
	By JanuaryTab=By.xpath("//a[contains(text(),'January')]");
	
	public List<WebElement> ListOfBrandsinJan()
	{
		List<WebElement> ListBrand=driver.findElements(BrandList);;
		return ListBrand;
	}
	public WebElement January()
	{
		return driver.findElement(JanuaryTab);
	}
}
