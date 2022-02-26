package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterNG extends base{
	static ExtentReports extent;
	
	public static ExtentReports getReportObject()
	{
		
		String path =System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Muthukumar 2020");
		reporter.config().setTheme(Theme.DARK);
		extent =new ExtentReports();
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.attachReporter(reporter);
		return extent;
		
	}
}
