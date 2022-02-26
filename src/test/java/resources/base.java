package resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class base {

	// PropertyfileVariables
	public static BufferedReader reader;
	public static Properties property = new Properties();

	// TestData Variables
	static Workbook WbTestData;
	static Sheet sheetTestData;
	static Row rowTestKey;
	static Row rowTestVal;
	static Cell cell_ValTestDatakey;
	static Cell cell_ValTestDataVal;
	static String TCname;
	public HashMap<String, String> TestData;
	public static Map<String, HashMap<String, String>> listOfData = new HashMap<String, HashMap<String, String>>();

	// Selenium Variables
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public ThreadLocal<WebDriverWait> waitdriver = new ThreadLocal<WebDriverWait>();
	public static Logger log = LogManager.getLogger();
	public WebDriver driverClass;
	public static ThreadLocal<String> tcName;

	// Reporter Variables
	public static List<String> StepsPass = new ArrayList<String>();
	public static List<String> StepsFail = new ArrayList<String>();
	
	@BeforeClass
	public void initialize() throws IOException {
		setDatahandler();
	}
	
	@BeforeMethod
	public void initializeDriver_() throws IOException{
		initializeDriver();
	}
	
	@AfterMethod()
	public void closeDriver()
	{
		getDriver().close();
	}
	@AfterClass
	public void teardown() {
	}


	public ThreadLocal<WebDriver> initializeDriver() throws IOException {
		String browserName = property.getProperty("browser");
		System.out.println(browserName);
		if(property.getProperty("headless").toLowerCase().equals("true")){
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("disable-gpu");
			driverClass = new ChromeDriver(chromeOptions);

		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxoptions=new FirefoxOptions();
			firefoxoptions.addArguments("--no-sandbox");
			firefoxoptions.addArguments("--headless");
			firefoxoptions.addArguments("disable-gpu");
			driverClass = new FirefoxDriver(firefoxoptions);
		} else if (browserName.equals("IE")) {
			WebDriverManager.iedriver().setup();
			driverClass = new InternetExplorerDriver();

		}

		}
		else {
			if (browserName.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				driverClass = new ChromeDriver();

			} else if (browserName.equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driverClass = new FirefoxDriver();
			} else if (browserName.equals("IE")) {
				WebDriverManager.iedriver().setup();
				driverClass = new InternetExplorerDriver();

			}

		}
		
		
		
		driver.set(driverClass);
		driverClass.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driverClass.manage().window().maximize();
	    return driver;

	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(WebDriver driverLocal) {
		driver.set(driverLocal);
	}

	public static String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;

	}

	public static void setDatahandler() throws IOException {

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\resources\\data.properties");
		property.load(fis);

		String fdpath = property.getProperty("testData");
		File file_data = new File(System.getProperty("user.dir") + fdpath);
		FileInputStream inputStream_data = new FileInputStream(file_data);

		WbTestData = null;

		// Find the file extension by splitting file name in substring and getting only
		// extension name

		String fileExtensionName_data = fdpath.substring(fdpath.indexOf("."));

		// Check condition if the file is xlsx file

		if (fileExtensionName_data.equals(".xlsx")) {

			// If it is xlsx file then create object of XSSFWorkbook class

			WbTestData = new XSSFWorkbook(inputStream_data);

		}

		// Check condition if the file is xls file

		else if (fileExtensionName_data.equals(".xls")) {

			// If it is xls file then create object of HSSFWorkbook class

			WbTestData = new HSSFWorkbook(inputStream_data);

		}

		HashMap<String, String> testData = new HashMap<String, String>();

		sheetTestData = WbTestData.getSheetAt(0);

		int rowDatano = 0;
		for (int k = 0; k <= sheetTestData.getLastRowNum(); k++) {
			rowTestKey = sheetTestData.getRow(k);
			String tcName = rowTestKey.getCell(0).getStringCellValue();
			for (int j = 0; j < rowTestKey.getLastCellNum(); j++) {
				rowTestVal = sheetTestData.getRow(rowDatano);

				cell_ValTestDatakey = rowTestKey.getCell(j);
				cell_ValTestDataVal = rowTestVal.getCell(j);
				testData.put(cell_ValTestDataVal.toString().trim(), cell_ValTestDatakey.toString().trim());

			}
			listOfData.put(tcName, testData);
			testData = new HashMap<String, String>();
		}

	}

	public static HashMap getTestData(String tcName) {
		return listOfData.get(tcName);
	}

	
}
