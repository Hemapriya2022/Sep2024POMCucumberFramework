package com.automation.pages.login;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.pages.base.BasePage;

public class LoginPage  extends BasePage{
	
	
	@FindBy(id="email_field") WebElement userNameEle;
	@FindBy(id="password_field") WebElement passwordElement;
	@FindBy(tagName="button") WebElement loginButtonElement;
	
	public LoginPage(WebDriver driver) {
		//PageFactory.initElements(driver, this);
		super(driver);
	}
	public void enterUserName(String data) {
		enterText(userNameEle,data,"username filed");
	}
	
    public void enterPassword(String data) {
    	enterText(passwordElement,data,"password filed");
    }
    
    public WebDriver clickloginButton() {
    	clickElement(loginButtonElement,"loginButton");  
    	return driver;
    }
    
    public Alert switchToErrorAlert() {
    	return switchToAlert();
    }
    public String extractTextFromAlert(Alert a) {
    	return getAlertText(a,"LoginError Alert");
    }
    public void acceptErrorAlert(Alert a) {
    	AcceptAlert(a);
    }
    public void rejectErrorAlert(Alert a) {
    	CancelAlert(a);
    }
    }
