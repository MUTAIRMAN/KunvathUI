package testcases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import pageObjects.LoginPagevisitsaudi;
import resources.UtilClass;
import resources.base;

public class AudiTest extends base{
	
	LoginPagevisitsaudi lpg=new LoginPagevisitsaudi(getDriver());

	@Test
	public void AudiLoginTest() throws Exception {
		
		TestData=getTestData("TC_TAMARA_001");
		getDriver().get(property.getProperty("url"));
		System.out.println(TestData);
		lpg.getPersonClick();
		UtilClass.explicitWait(lpg.email);
		lpg.setEmail(TestData.get("Username"));
		lpg.setPassword(TestData.get("Password"));
		StepsPass.add("Successfully validated Text message _ Tamara");
		Assert.assertEquals("Test","Test");

	}

	
	
	
	
}
