package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resources.base;
public class LandingPage extends base {

	
	public WebDriver driver;
	
	By signin=By.cssSelector("a[href*='sign_in']");
	By title=By.cssSelector(".text-center>h2");
	By NavBar=By.cssSelector(".nav.navbar-nav.navbar-right>li>a");
	
	
	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
	setDriver(driver);
	this.driver=base.getDriver();
		
	}
	public LoginPage getLogin()
	{
		 driver.findElement(signin).click();
		 LoginPage lp=new LoginPage(driver);
		 return lp;
		 
	}
	public WebElement getNavigationBar()
	{
		return driver.findElement(NavBar);
	}
	public WebElement getTitle()
	{
		return driver.findElement(title);
	}
	
	
	
}
