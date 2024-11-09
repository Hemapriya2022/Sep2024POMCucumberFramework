package com.automation.stepDefinitions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.automation.pages.home.HomePage;
import com.automation.pages.login.LoginPage;
import com.automation.utility.Constants;
import com.automation.utility.PropertiesUtility;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(com.automation.utility.FirebaseListenerUtility.class)
public class FirebaseStepDefinition  {
	private Logger mylog = LogManager.getLogger(FirebaseStepDefinition.class);
	 protected static  WebDriver driver=null;
	 LoginPage login;
	 HomePage home;
	
	
	public   void launchBrowser(String browserName) {
		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			break;

		default:
			break;
		}
	}
	
	
	
	public  void goToUrl(String url) throws InterruptedException {
		driver.get(url);
		mylog.info(url + "is entered");
		Thread.sleep(5000);
		
	}
	
	
	public void closeDriver()  {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		driver.close();
	}
	
	@Before
	public void beforeScenario() {
		launchBrowser("chrome");
	}
	
	@After
	public void afterScenario() {
		closeDriver();
	}
	
	@AfterStep
	public void afterStep(Scenario sce) {
		if(sce.isFailed()) {
		    byte[] screenshot= ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)	;
		    sce.attach(screenshot, "image/png", sce.getName());
		}
		
	}
	
	@Given("the url {string}")
	public void the_url(String url) {
		try {
			goToUrl(url);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@When("i land in {string}")
	public void i_land_in(String page) {
		
		if (page.equalsIgnoreCase("loginpage")) {
			login=new LoginPage(driver);
		}
		else if(page.equalsIgnoreCase("homepage")) {
			home=new HomePage(driver);
		}
	}

	@When("i enter the username as {string}")
	public void i_enter_the_username_as(String usernameData) {
		login.enterUserName(usernameData);
	}

	@When("i enter the password as {string}")
	public void i_enter_the_password_as(String passwordData) {
		login.enterPassword(passwordData);
	}
	
	@When("i click the login button")
	public void i_click_the_login_button() {
		driver=login.clickloginButton();
	}

	@Then("i should be able to see homepage")
	public void i_should_be_able_to_see_homepage() throws InterruptedException {
		/*String expectedData = "Student registration form text";
		String actual = home.getTextFromStudentRegistrationFormText();
		Assert.assertEquals(actual,expectedData );*/
		int a=10/0;
	}
	
	@Then("i should be able to see error message {string}")
	public void i_should_be_able_to_see_error_message(String expectedString) throws InterruptedException {
		/*Thread.sleep(2000);
		Alert alert=driver.switchTo().alert();
		String actual=alert.getText();
		System.out.println(actual);
		Assert.assertEquals(actual, expectedString);
		alert.accept();
		
		driver.close();*/
		
		Alert alert = login.switchToAlert();
		String actualError=login.extractTextFromAlert(alert);
		login.AcceptAlert(alert);
		Assert.assertEquals(actualError, expectedString);
	}

	
		}
