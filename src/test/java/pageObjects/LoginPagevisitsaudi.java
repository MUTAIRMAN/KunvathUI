package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resources.UtilClass;
import resources.base;
public class LoginPagevisitsaudi extends base{

	
	public WebDriver driver;
	
	public By email=By.id("tb-userName");
	By password=By.id("tb-pwd");
	By login=By.xpath("(//button[@class='icon-button'])[3]");
	
	
	
	
	public LoginPagevisitsaudi(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		setDriver(driver);
		this.driver=base.getDriver();
	}



	public WebElement getPerson()
	{
		return driver.findElement(email);
	}
	public void setEmail(String strInput) throws Exception
	{
		UtilClass.sendKeys(email, strInput);
	}

	public void setPassword(String strInput) throws Exception
	{
		UtilClass.sendKeys(password, strInput);
	}
	
	public void getPersonClick() throws Exception
	{
		UtilClass.click(login);
	}
	
	
	
	
}
