/*===============================================================================================================
Company		 -		  OTDA_QMC
Title	     -		  OTDA_DRAGON
Test Version -		  1.0
Class Name   -        LOGIN OTDA   
Tool		 -		  Selenium WebDriver 
Purpose      -        OTDA_PUBLIC_HomePage Load
Created By   -        Vandana Dasarath
Created On   -        14th March 2018
=================================================================================================================*/
package nyenet.state.otda;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import javax.mail.MessagingException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import utility.GenericFunctions;

public class OTDA_INTRANET extends GenericFunctions {
	
	HashMap<String, String> map = new HashMap<String, String>();

	ExtentReports extent;
	ExtentTest logger;
    
	@BeforeTest
	public void startReport() {
		extent = new ExtentReports("C:\\Dragon_Workspace_2018\\DRAGON_OTDA_INTRANET\\ExtentReports\\OTDA_INTRANET_Results.html", true);
	}

	@BeforeClass
	public void beforeAllTest() {
		System.setProperty("webdriver.firefox.profile", "Selenium_Profile");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://otda.state.nyenet");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		TestfileName = "C:\\Dragon_Workspace_2018\\DRAGON_OTDA_INTRANET\\OTDA_Intranet_Credentials.xls";
		sheetNumber = 0;
	}
	/* verify the login page when valid userid and password is provided */

	@Test(priority = 1)
	public void OTDAHOMEPAGE() throws Exception {
		logger = extent.startTest(" OTDA_HOMEPAGE ");
		map.clear();
		@SuppressWarnings("unused")
		String actualTitle = driver.getTitle();
		driver.manage().window().maximize();
		String expectedTitle = "OTDA Intranet";
		 if(actualTitle.equalsIgnoreCase(expectedTitle)) {
			 System.out.println(" 1) Successfully Logged in to OTDA Intranet Home Page  " + new Date());
			 map.put(" 1) Successfully Logged in to OTDA Intranet Home Page", "Pass" + new Date());
	}
		 else
		 {
			    System.out.println("OTDA Intranet Home Page Not Loaded." + new Date());
				map.put("OTDA Intranet Home Page Not Loaded.", "Fail" + new Date());
				logger.log(LogStatus.FAIL, "OTDA Intranet Home Page Not Loaded....");
				Assert.assertTrue(false);
		}
		logger.log(LogStatus.PASS, "Successfully Logged into OTDA Home Page");
	}
	String attachment = "C:\\Dragon_Workspace_2018\\DRAGON_OTDA_INTRANET\\ExtentReports\\OTDA_INTRANET_Results.html";
	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			//logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
		extent.endTest(logger);
	}
	
	@AfterTest
	public void endReport() throws EncryptedDocumentException, FileNotFoundException, InvalidFormatException, MessagingException, IOException, InterruptedException {
		extent.flush();
		extent.close();
		Thread.sleep(2*1000);
		driver.close();
		Thread.sleep(5*1000);
		checkmail(map,attachment);
	}
}