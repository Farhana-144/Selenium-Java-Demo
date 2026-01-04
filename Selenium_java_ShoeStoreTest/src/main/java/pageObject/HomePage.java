package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	By HeaderMenus=By.xpath("//*[@id='header_nav']/nav/ul/li");
	By DropDownSelect=By.id("brand");
	By BrandCount=By.xpath("//*[@id='brand']/option");
	By Search=By.id("search_button");
	By Home=By.cssSelector(".navbar-brand");
	
	public List<WebElement> HeaderMenusList()
	{
		List<WebElement> ListOfMenu= driver.findElements(HeaderMenus);
		return ListOfMenu;
	}
	
	public List<WebElement> BrandCountList()
	{
		List<WebElement> ListOfBrand= driver.findElements(BrandCount);
		return ListOfBrand;
	}
	public WebElement DropDownInputField()
	{
		return driver.findElement(DropDownSelect);
	}
	public WebElement SearchButton()
	{
		return driver.findElement(Search);
	}

	public WebElement HomeButton()
	{
		return driver.findElement(Home);
	}
}
