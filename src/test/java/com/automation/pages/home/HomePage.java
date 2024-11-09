package com.automation.pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.automation.pages.base.BasePage;

public class HomePage extends BasePage{
	
	//@FindBy(xpath = "/html/body/div[2]/div[2]/h1") WebElement studentRegistration;
	@FindBy(tagName = "h1") WebElement studentRegistration;
	
	public HomePage(WebDriver driver) {
		//PageFactory.initElements(driver,this);
		super(driver);
	}
	
	public String getTextFromStudentRegistrationFormText() {
		//waitForVisibility1(studentRegistration, 60,"student registration text area");
	    //waitForVtextToBePresentInElement(studentRegistration, 60,"Student Registration Form","student registration text area");
		
		String data= getTextFromElement(studentRegistration,"Student registration form text");
		System.out.println("text on student regi-==="+data);
		return data;
	}

}
