package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[contains(text(),'Log out')]")
	@CacheLookup
	WebElement linkLogout;
	
	@FindBy(how = How.XPATH, using="//a[contains(text(),'New Customer')]")
	@CacheLookup
	WebElement linkNewCustomer;
	
	public void clickLogout() {
		linkLogout.click();
	}
	
	public void clickNewCustomer() {
		linkNewCustomer.click();
	}
}
