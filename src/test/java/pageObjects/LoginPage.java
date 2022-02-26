package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resources.UtilClass;
import resources.base;
public class LoginPage extends base{

	
	public WebDriver driver;
	
	By email=By.cssSelector("[id='user_email']");
	By password=By.cssSelector("[type='password']");
	By login=By.cssSelector("[value='Log In']");
	By forgotPassword = By.cssSelector("[href*='password/new']");
	By title=By.xpath("//a[text()='WebServices Testing using SoapUI ']");
	
	
	
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		setDriver(driver);
		this.driver=base.getDriver();
	}



public ForgotPassword forgotPassword()
{
	driver.findElement(forgotPassword).click();
	return new ForgotPassword(driver);
	
}
	public WebElement getEmail()
	{
		return driver.findElement(email);
	}
	

	public void setPassword(String strInput) throws Exception
	{
		UtilClass.sendKeys(password, strInput);
	}
	
	public void getLoginClick() throws Exception
	{
		UtilClass.click(login);
	}
	
	
	public WebElement getTitle() {
		return driver.findElement(title);
	}
	
}
