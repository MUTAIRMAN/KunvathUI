package resources;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class UtilClass extends base{
	
	public static void click(By element) throws Exception {
		int count=0;
		try {
			getDriver().findElement(element).click();
			count++;

		}
		catch(ElementNotVisibleException ex) {
		    if(count==10000)
		    	throw new Exception();
			click(element);
		}
		catch(Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}
	
	public static void sendKeys(By element,String strInput) throws Exception {
		int count=0;
		try {
			getDriver().findElement(element).sendKeys(strInput);
		}
		catch(ElementNotVisibleException ex) {
		    if(count==10000)
		    	throw new Exception();
			click(element);
		}
		catch(Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}
	
	public static void JSClick(By element) throws Exception {
		int count=0;

		try {
			WebElement elementLocal = getDriver().findElement(element);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", elementLocal);
					}
		catch(ElementNotVisibleException ex) {
		    if(count==10000)
		    	throw new Exception();
		    JSClick(element);
		}
		catch(Exception ex) {
			Assert.fail(ex.getMessage());
		}
		
	}

	public static void explicitWait(By element) throws Exception{
		try {
		WebDriverWait wait=new WebDriverWait(getDriver(),10);
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
		}
		catch(Exception ex) {
			Assert.fail(ex.getMessage());

		}
	}
}
