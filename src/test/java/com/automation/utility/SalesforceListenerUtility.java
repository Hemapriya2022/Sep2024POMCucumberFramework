package com.automation.utility;

	import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
	import org.testng.ITestListener;
	import org.testng.ITestResult;

	public class SalesforceListenerUtility implements ITestListener{
		private Logger mylog = LogManager.getLogger(SalesforceListenerUtility.class);
		

		
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
		}

		@Override
		public void onTestSuccess(ITestResult result) {
			mylog.info(result.getMethod().getMethodName()+" ended with success......................");
		}

		@Override
		public void onTestFailure(ITestResult result) {
			mylog.error(result.getMethod().getMethodName()+" ended with failure.....................");
		}

		@Override
		public void onTestSkipped(ITestResult result) {
			mylog.warn(result.getMethod().getMethodName()+" skiped...........................");
			
		}

		
		

	}



