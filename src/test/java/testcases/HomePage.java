package testcases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import junit.framework.Assert;
import pageObjects.ForgotPassword;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.Listeners;
import resources.base;

public class HomePage extends base {


	
	@Test
	public void basePageNavigation() throws Exception {
		TestData=getTestData("TC_Test_001");
		System.out.println(TestData);
		getDriver().get(property.getProperty("url"));
		LandingPage l = new LandingPage(getDriver());
		LoginPage lp = l.getLogin(); // driver.findElement(By.css()
		lp.getEmail().sendKeys(TestData.get("Username"));
		//frameworkmethod to sendkeys
		lp.setPassword(TestData.get("Password"));
		//frameworkmethod to click
		lp.getLoginClick();
		

		StepsPass.add("mthu");
		Assert.assertEquals(lp.getTitle().getText().trim(),"WebServices Testing using SoapUI");

	}
	@Test
	public void basePageNavigation2() throws Exception {
		
		TestData=getTestData("TC_Test_001");
		getDriver().get(property.getProperty("url"));
		System.out.println(TestData);
		LandingPage l = new LandingPage(getDriver());
		LoginPage lp = l.getLogin(); // driver.findElement(By.css()
		lp.getEmail().sendKeys(TestData.get("Username"));
		//frameworkmethod to sendkeys
				lp.setPassword(TestData.get("Password"));
				//frameworkmethod to click
				lp.getLoginClick();
				StepsPass.add("Successfully validated Text message");
		Assert.assertEquals(lp.getTitle().getText().trim(),"WebServices Testing using SoapUI");
	}

	
}
