package com.automation.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;



public class FirebaseListenerUtility  implements ITestListener{
	private Logger mylog = LogManager.getLogger(FirebaseListenerUtility.class);
	private ExtentReportsUtility extentReportUtility=ExtentReportsUtility.getInstance();
	
	@Override
	public void onStart(ITestContext context) {
		
		mylog.info(context.getName()+" started...............");
	}

	@Override
	public void onFinish(ITestContext context) {
		mylog.info(context.getName()+" ended.................");
	}
	

	@Override
	public void onTestStart(ITestResult result) {
		
		mylog.info(result.getMethod().getMethodName()+" started..................");
		extentReportUtility.startSingleTestReport(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		mylog.info(result.getMethod().getMethodName()+" ended with success......................");
		extentReportUtility.logTestpassed(result.getMethod().getMethodName()+" ended with success......................");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		mylog.error(result.getMethod().getMethodName()+" ended with failure.....................");
		extentReportUtility.logTestFailedWithException(result.getThrowable());
		String filename=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		String path=Constants.SCREENSHOTS_DIRECTORY_PATH+filename+".png";
		//takeScreenshot(path);
		extentReportUtility.logTestWithscreenshot(path);
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		mylog.warn(result.getMethod().getMethodName()+" skiped...........................");
		extentReportUtility.logTestFailed(result.getMethod().getMethodName()+" skiped...........................");
		
	}

	
	

}