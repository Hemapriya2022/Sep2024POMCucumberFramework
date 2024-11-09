package com.automation.pages.base;

import java.time.Duration;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.automation.utility.ExtentReportsUtility;

public class BasePage {
	
	protected  WebDriver driver;
	 private WebDriverWait wait = null;	
	  private Logger mylog = LogManager.getLogger(BasePage.class);
	 // protected ExtentReportsUtility extentReportUtility=ExtentReportsUtility.getInstance();
	  
	   public BasePage(WebDriver driver) {
		   this.driver=driver;
		  PageFactory.initElements(driver, this);
	  }
	
	 public String getTitle() {
	        try {
	            String title = driver.getTitle();
	            mylog.info("Page title is: " + title);
	            return title;
	        } catch (Exception e) {
	            mylog.error("Failed to get page title: " + e.getMessage());
	            return null;
	        }
	    }
	 
	 public String getPageSource() {
	        try {
	            String pageSource = driver.getPageSource();
	            mylog.info("Page source retrieved.");
	            return pageSource;
	        } catch (Exception e) {
	            mylog.error("Failed to get page source: " + e.getMessage());
	            return null;
	        }
	    }

	 
	 public void close() {
	        try {
	            driver.close();
	            mylog.info("Browser closed successfully.");
	        } catch (Exception e) {
	            mylog.error("Failed to close browser: " + e.getMessage());
	        }
	    }
	 
	 public void quit() {
	        try {
	            driver.quit();
	            mylog.info("Driver quit successfully.");
	        } catch (Exception e) {
	            mylog.error("Failed to quit driver: " + e.getMessage());
	        }
	    }
	 
	 public void cleanup() {
	        try {
	            if (driver != null) {
	                driver.quit();
	                mylog.info("Driver cleaned up successfully.");
	            }
	        } catch (Exception e) {
	            mylog.error("Failed to clean up driver: " + e.getMessage());
	        }
	    }
	 
	// Method to find multiple elements
	    public List<WebElement> findElements(By by) {
	        try {
	            List<WebElement> elements = driver.findElements(by);
	            mylog.info("Elements found: " + by.toString());
	            return elements;
	        } catch (Exception e) {
	            mylog.error("Failed to find elements: " + by.toString() + " - " + e.getMessage());
	            return null;
	        }
	    }
	    
	    // Method to get the current URL
	    public String getCurrentUrl() {
	        try {
	            String currentUrl = driver.getCurrentUrl();
	            mylog.info("Current URL is: " + currentUrl);
	            return currentUrl;
	        } catch (Exception e) {
	            mylog.error("Failed to get current URL: " + e.getMessage());
	            return null;
	        }
	    }
	    
	    
	 // Method to right-click (context click)
	    public void rightClick(By locator) {
	        try {
	            WebElement element = driver.findElement(locator);
	            Actions actions = new Actions(driver);
	            actions.contextClick(element).perform();
	            mylog.info("Right click performed.");
	        } catch (Exception e) {
	            mylog.error("Unable to perform right click: " + e.getMessage());
	        }
	    }
	    
	    
	    public  void selectFromDropdownByVisibleText(WebDriver driver, By dropdownLocator, String visibleText) {
	        WebElement dropdownElement = driver.findElement(dropdownLocator);
	        Select dropdown = new Select(dropdownElement);
	        dropdown.selectByVisibleText(visibleText);
	    }

	    // Reusable method to select from a dropdown by value
	    public  void selectFromDropdownByValue(WebDriver driver, By dropdownLocator, String value) {
	        WebElement dropdownElement = driver.findElement(dropdownLocator);
	        Select dropdown = new Select(dropdownElement);
	        dropdown.selectByValue(value);
	    }

	    // Reusable method to select from a dropdown by index
	    public  void selectFromDropdownByIndex(WebDriver driver, By dropdownLocator, int index) {
	        WebElement dropdownElement = driver.findElement(dropdownLocator);
	        Select dropdown = new Select(dropdownElement);
	        dropdown.selectByIndex(index);
	    }

	    // Reusable method to handle user menu dropdown and select an option
	    public  void selectUserMenuOption(WebDriver driver, String optionText) {
	        // Click the user menu dropdown
	        WebElement userMenu = driver.findElement(By.id("userNavLabel"));
	        userMenu.click();

	        // Click the desired option in the dropdown
	        WebElement option = driver.findElement(By.xpath("//a[text()='" + optionText + "']"));
	        option.click();
	    }
	
	    
	    
	 // Method to drag and drop
	    public void dragAndDrop(By sourceLocator, By targetLocator) {
	        try {
	            WebElement source = driver.findElement(sourceLocator);
	            WebElement target = driver.findElement(targetLocator);
	            Actions actions = new Actions(driver);
	            actions.dragAndDrop(source, target).perform();
	            mylog.info("Drag and drop performed.");
	        } catch (Exception e) {
	            mylog.error("Unable to perform drag and drop: " + e.getMessage());
	        }
	    }
	    
	    
	    public void selectCheckBox(WebElement ele,String objectName) {
	    	if(ele.isSelected()) {
	    		ele.click();
	    		mylog.info(objectName+" button is selected");
	    	}
	    	else {
	    		mylog.error(objectName+" button is not selected");
	    	}
	    }
	    
	    public void selectByValueData(WebElement ele,String value) {
	    	Select select = new Select(ele);
	    	select.selectByValue(value);
	    	
	   }
	    
	    public void selectByTextData(WebElement ele,String value) {
	    	Select select = new Select(ele);
	    	select.selectByVisibleText(value);
	    	
	   } 
	    
	    
	    public void selectByIndexData(WebElement ele, int value) {
	    	Select select = new Select(ele);
	    	select.selectByIndex(value);
	    	
	   } 
	    
	    public WebElement selectFromListUsingText(List<WebElement> list, String text) {
	    	WebElement element = null;
	    	for(WebElement i : list) {
	    		if(i.getText().equalsIgnoreCase(text)) {
	    			mylog.info ("selected=" + i.getText());
	    			element = i;
	    			break;
	    		}
	    	}
	    	return element;
	    	
	    }
	    
	   public void ContextClickOnElement(WebElement ele, String objName) {
		   Actions action = new Actions(driver);
		   action.contextClick(ele).build().perform();
		   mylog.info ("right click performed on web element " + objName);
	   }
	    
	   public void waitForVisibility(WebElement ele, int time, int pollingtime, String objectName) {
		   FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		   wait
		   .withTimeout(Duration.ofSeconds(time))
		   .pollingEvery(Duration.ofSeconds(pollingtime))
		   .ignoring(ElementNotInteractableException.class)
		   .withMessage(objectName+" is not visible.fluent wait time expires");
		   
		   wait.until(ExpectedConditions.visibilityOf(ele));
		   mylog.info(objectName + " is waited for visibility using fluent wait");
	   }
	    
	 // Method to scroll down or up
	    public void scrollBy(int x, int y) {
	        try {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("window.scrollBy(" + x + "," + y + ");");
	            mylog.info("Scrolled by x: " + x + ", y: " + y);
	        } catch (Exception e) {
	            mylog.error("Unable to scroll: " + e.getMessage());
	        }
	    }
	    
	 // Method to select item from dropdown
	    public void selectFromDropdown(By locator, String value) {
	        try {
	            WebElement dropdownElement = driver.findElement(locator);
	            Select dropdown = new Select(dropdownElement);
	            dropdown.selectByVisibleText(value);
	            mylog.info("Selected " + value + " from dropdown.");
	        } catch (Exception e) {
	            mylog.error("Unable to select from dropdown: " + e.getMessage());
	        }
	    }
	    
	    // Method to open a new tab
	    public void openNewTab() {
	        try {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("window.open();");
	            mylog.info("New tab opened.");
	        } catch (Exception e) {
	            mylog.error("Unable to open new tab: " + e.getMessage());
	        }
	    }
	    
	 // Method to open a new window
	    public void openNewWindow() {
	        try {
	            ((JavascriptExecutor) driver).executeScript("window.open('','_blank');");
	            mylog.info("New window opened.");
	        } catch (Exception e) {
	            mylog.error("Unable to open new window: " + e.getMessage());
	        }
	    }
	    
	 // Method to enter text
	    public void enterText(By locator, String text) {
	        try {
	            WebElement element = driver.findElement(locator);
	            element.clear();
	            element.sendKeys(text);
	            mylog.info("Entered text: " + text);
	        } catch (Exception e) {
	            mylog.error("Unable to enter text: " + e.getMessage());
	        }
	    }
	    
	    public void enterText(By locator, String textData, String fieldName) {
	        try {
	            WebElement element = driver.findElement(locator);
	            element.clear();  // Clear the field before entering new text
	            element.sendKeys(textData);
	            mylog.info("Entered text '" + textData + "' in " + fieldName);
	        } catch (Exception e) {
	        	mylog.info("Unable to enter text in " + fieldName + ": " + e.getMessage());
	        }
	    }

	
	
	public  void enterText(WebElement ele,String data,String objectName) {
		if(ele.isDisplayed()) {
			ele.clear();
			ele.sendKeys(data);
			mylog.info("data is entered in the "+objectName);
			//extentReportUtility.logTestInfo("data is entered in the "+objectName);
		}
		else{
			mylog.error(objectName+" textbox is not diplayed");
			//extentReportUtility.logTestFailed("data is entered in the"+objectName);
		}
	}
	
	
	public  void clickElement(WebElement ele,String objectName) {
		if(ele.isEnabled()) {
			ele.click();
			mylog.info(objectName+" button is clicked");
		}
		else{
			mylog.error(objectName+" button is not diplayed");
		}
	}

	
	public  void selectElement(WebElement ele,String objectName) {
		if(!ele.isSelected()) {
			ele.click();
			mylog.info(objectName+" button is selected");
		}
		else{
			mylog.error(objectName+" button is already selected");
		}
	}
	
	 // Explicit wait example for element visibility
 public void waitForElementVisibility(By locator, int timeout) {
     WebDriverWait wait = new WebDriverWait(driver, timeout);
     wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
 }

 
 // Method to wait for a specific number of seconds (explicit wait)
 public void addwait(int seconds) {
     try {
         Thread.sleep(seconds * 1000); // Pause execution for the specified number of seconds
     } catch (InterruptedException e) {
         e.printStackTrace();
     }
 }
	
	public void isElementDisplayed(WebElement element) {
     try {
         if (element.isDisplayed()) {
             mylog.info("Element is displayed.");
         } else {
             mylog.error("Element is not displayed.");
         }
     } catch (Exception e) {
         mylog.error("Exception in isElementDisplayed: " + e.getMessage());
     }
 }
	
	
	
	 public void isElementEnabled(WebElement element) {
	        try {
	            if (element.isEnabled()) {
	                mylog.info("Element is enabled.");
	            } else {
	                mylog.error("Element is not enabled.");
	            }
	        } catch (Exception e) {
	            mylog.error("Exception in isElementEnabled: " + e.getMessage());
	        }
	    }
	 
	 public void isElementSelected(WebElement element) {
	        try {
	            if (element.isSelected()) {
	                mylog.info("Element is selected.");
	            } else {
	                mylog.error("Element is not selected.");
	            }
	        } catch (Exception e) {
	            mylog.error("Exception in isElementSelected: " + e.getMessage());
	        }
	    }
	 
	 
	 public void getElementText(WebElement element) {
	        try {
	            String text = element.getText();
	            mylog.info("Text of the element: " + text);
	        } catch (Exception e) {
	            mylog.error("Exception in getElementText: " + e.getMessage());
	        }
	    }
	 
	 
	 public void getElementAttribute(WebElement element, String attribute) {
	        try {
	            String attrValue = element.getAttribute(attribute);
	            mylog.info("Attribute '" + attribute + "' of the element: " + attrValue);
	        } catch (Exception e) {
	            mylog.error("Exception in getElementAttribute: " + e.getMessage());
	        }
	    }
	 
	 
	 public void getElementLocation(WebElement element) {
	        try {
	            mylog.info("Element location: " + element.getLocation().toString());
	        } catch (Exception e) {
	            mylog.error("Exception in getElementLocation: " + e.getMessage());
	        }
	    }
	 
	 
	 public void getElementSize(WebElement element) {
	        try {
	            mylog.info("Element size: " + element.getSize().toString());
	        } catch (Exception e) {
	            mylog.error("Exception in getElementSize: " + e.getMessage());
	        }
	    }
	 
	 public void clearElement(WebElement element) {
	        try {
	            element.clear();
	            mylog.info("Cleared the element.");
	        } catch (Exception e) {
	            mylog.error("Exception in clearElement: " + e.getMessage());
	        }
	    }
	 
	 public void sendKeysToElement(WebElement element, String keysToSend) {
	        try {
	            element.sendKeys(keysToSend);
	            mylog.info("Sent keys '" + keysToSend + "' to the element.");
	        } catch (Exception e) {
	            mylog.error("Exception in sendKeysToElement: " + e.getMessage());
	        }
	    }
	 
	 
	
	public void selectByValue(WebElement ele, String value) {
		
		Select select = new Select(ele);
		select.selectByValue(value);
	}
	
	public void selectByIndex(WebElement ele, int value) {
		
		Select select = new Select(ele);
		select.selectByIndex(value);
	}
	
	
	public void getFirstSelectedOption(By locator) {
     try {
         WebElement dropdown = driver.findElement(locator);
         Select select = new Select(dropdown);
         String firstOption = select.getFirstSelectedOption().getText();
         mylog.info("First selected option: " + firstOption);
     } catch (Exception e) {
         mylog.error("Error getting first selected option: " + e.getMessage());
     }
 }
	
	
	
	public void deselectByVisibleText(By locator, String text) {
     try {
         WebElement dropdown = driver.findElement(locator);
         Select select = new Select(dropdown);
         select.deselectByVisibleText(text);
         mylog.info("Option with visible text '" + text + "' deselected successfully.");
     } catch (Exception e) {
         mylog.error("Error deselecting option by visible text: " + e.getMessage());
     }
 }
	
	
	
	public void deselectByValue(By locator, String value) {
     try {
         WebElement dropdown = driver.findElement(locator);
         Select select = new Select(dropdown);
         select.deselectByValue(value);
         mylog.info("Option with value '" + value + "' deselected successfully.");
     } catch (Exception e) {
         mylog.error("Error deselecting option by value: " + e.getMessage());
     }
 }
	
	
	public void deselectAll(By locator) {
     try {
         WebElement dropdown = driver.findElement(locator);
         Select select = new Select(dropdown);
         select.deselectAll();
         mylog.info("All options deselected successfully.");
     } catch (Exception e) {
         mylog.error("Error deselecting all options: " + e.getMessage());
     }
 }
	
	
	public void mouseOverOnElement(WebElement ele, String objName) {
		Actions action = new Actions(driver);
		action.moveToElement(ele).build().perform();
		mylog.info("cursor moved to element ="+objName);
	}
	
	
	public Alert switchToAlert() {
		waitForAlertToPresent(30, "error loginalert box");
		Alert alert = driver.switchTo().alert();
		mylog.info("switched to an alert");
		return alert;
	}
	
	
	public String getAlertText(Alert alert, String objectname) {
		mylog.info("extracting text in the " + "alert");
		String text = alert.getText();
		mylog.error(" text extracted from alert box is == " + text);
		return text;
		
	}
	
	
	public void AcceptAlert(Alert alert) {
		alert.accept();
		mylog.info("Alert accepted");
	
	}
	
	
	public void CancelAlert(Alert alert) {
		alert.dismiss();
		mylog.info("Alert cancelled");
	
	}
	
	
	 // Handle checkboxes
 public void checkCheckbox(By locator) {
     try {
         WebElement checkbox = driver.findElement(locator);
         if (!checkbox.isSelected()) {
             checkbox.click();
             mylog.info("Checkbox selected");
         }
     } catch (Exception e) {
         mylog.error("Unable to select checkbox: " + e.getMessage());
     }
 }
 
 // Handle new windows (switch to the new window)
 public void switchToNewWindow() {
     try {
         String currentWindow = driver.getWindowHandle();
         for (String windowHandle : driver.getWindowHandles()) {
             if (!windowHandle.equals(currentWindow)) {
                 driver.switchTo().window(windowHandle);
                 mylog.info("Switched to new window");
                 break;
             }
         }
     } catch (Exception e) {
         mylog.error("Unable to switch to new window: " + e.getMessage());
     }
 }

 
 public String extractText(WebElement element, String elementDescription) {
     try {
         // Ensure the element is visible before extracting text
         WebDriverWait wait = new WebDriverWait(driver, 10); // adjust timeout as needed
         wait.until(ExpectedConditions.visibilityOf(element));

         String text = element.getText();
         mylog.info("Text extracted from " + elementDescription + ": " + text);
         return text;

     } catch (Exception e) {
     	mylog.error("Failed to extract text from " + elementDescription + ". Error: " + e.getMessage());
         return null;  // Return null or handle as needed in case of failure
     }
 }

 public void waitForTextToBePresentInElement(WebElement element, int timeout, String text) {
     WebDriverWait wait = new WebDriverWait(driver, timeout);
     wait.until(ExpectedConditions.textToBePresentInElement(element, text));
 }
 
 public WebElement waitForVisibility1(WebElement webEle, int timeout, String errorMsg) {
     try {
         WebDriverWait wait = new WebDriverWait(driver, timeout);
         return wait.until(ExpectedConditions.visibilityOf(webEle));
     } catch (Exception e) {
         mylog.error("Error: " + errorMsg);
         throw e;
     }
 }
 
 public void waitUntilAlertPresent(int sec) {
	 WebDriverWait wait = new WebDriverWait(driver,sec);
	 wait.until(ExpectedConditions.alertIsPresent());
 }
 
 // Non-static method to get text from a WebElement
 public String getTextFromElement(WebElement element, String defaultText) {
     try {
         // Check if element is displayed and has text
         if (element.isDisplayed()) {
             String text = element.getText();
             // Return text if available, otherwise return default text
             return (text != null && !text.isEmpty()) ? text : defaultText;
         } else {
             mylog.info("Element is not displayed.");
         }
     } catch (Exception e) {
         mylog.error("Exception encountered: " + e.getMessage());
     }
     return defaultText;  // Return default text in case of failure
 }

public void waitForVtextToBePresentInElement(WebElement webEle, int timeout, String text, String errorMsg) {
 WebDriverWait wait = new WebDriverWait(driver, timeout);
 try {
     wait.until(ExpectedConditions.textToBePresentInElement(webEle, text));
 } catch (Exception e) {
     mylog.error(errorMsg + ": " + e.getMessage());
 }
}



public void waitForAlertToPresent(long timeInSec,String ObjectName) {
mylog.info(ObjectName+"waiting for visibility for maximum of "+timeInSec+"sec");
wait=new WebDriverWait(driver,timeInSec);
wait.until(ExpectedConditions.alertIsPresent());
}

public void waitForElementToClickable(WebElement ele,long timeInSec, String ObjectName) {
 mylog.info(ObjectName + " waiting for visibility for a maximum of " + timeInSec + " sec");
 wait=new WebDriverWait(driver,timeInSec);
 wait.until(ExpectedConditions.elementToBeClickable(ele));

}
public void waitForVtextToBEPresentInElement(WebElement ele,long timeInSec, String text,String ObjectName) {
 mylog.info(ObjectName+"waiting for visibility for maximum of "+timeInSec+"sec");
 wait=new WebDriverWait(driver,timeInSec);
 wait.until(ExpectedConditions.textToBePresentInElement(ele,text));
 }


public void clickIfNotSelected(By locator, WebDriver driver) {
 WebDriverWait wait = new WebDriverWait(driver, 10); // Using integer timeout for Selenium 3
 WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
 
 if (!element.isSelected()) {
     element.click();
 }
 //clickIfNotSelected(By.id("rememberUn"), driver);// calling method

}

public void clickElementWhenVisible(WebDriver driver, By locator) {
 WebDriverWait wait = new WebDriverWait(driver, 10); // 10 seconds timeout
 WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
 if (!element.isSelected()) {
     element.click();
 }
 //clickElementWhenVisible(driver, By.id("rememberUn"));  //calling method

}


}
	


