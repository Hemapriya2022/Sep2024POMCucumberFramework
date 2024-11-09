package examples;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class LogTests {
	 ExtentReports extent=null;
	 ExtentSparkReporter spark=null;
	Logger mylog;
	@BeforeClass
	public void setUp() {
		 mylog=LogManager.getLogger(LogTests.class);
		 extent = new ExtentReports();	
		 spark = new ExtentSparkReporter("results/Spark.html");
		 spark.config().setDocumentTitle("Test Execution Report");
		 spark.config().setReportName("firebase regression tests");
		 spark.config().setTheme(Theme.DARK);
		 
		 extent.setSystemInfo("Host Name", "Salesforce");
		 extent.setSystemInfo("Environment", "QA");
		 extent.setSystemInfo("User Name", "Hemapriya");
		 extent.attachReporter(spark);
	}
	@AfterClass
	public void tearDown() {
		extent.flush();
	}
	
	@Test
	public void test1() {
		ExtentTest test=extent.createTest("test1");
		try {
		int a=10/0;
		//System.out.println();
		mylog.info("test completed with success");
		//mylog.log(Level.INFO, "test completed with success");
		
		
		test.pass("test completed with success");
		test.log(Status.PASS, "test completed with success");
		
		
		}
		catch (ArithmeticException e) {
			mylog.error("test completed with failure");
			test.fail("test completed with failure");
			test.fail(e);
		}
		
		
	}
	
	@Test
	public void test2() {
		ExtentTest test=extent.createTest("test2");
		
		mylog.debug("test started");
		test.info("test started");
	}
	
	@Test
	public void test3() {
		ExtentTest test=extent.createTest("test3");
		mylog.debug("driver strated");
		test.warning("driver started");
	}

}