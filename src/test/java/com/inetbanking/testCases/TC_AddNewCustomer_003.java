package com.inetbanking.testCases;

import org.apache.commons.lang.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.HomePage;
import com.inetbanking.pageObjects.LoginPage;

public class TC_AddNewCustomer_003 extends BaseClass{
	
	@Test
	public void AddNewCustomer() throws Exception {
		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = new HomePage(driver);
		AddCustomerPage addCust = new AddCustomerPage(driver);
		loginPage.performLogin(username, password);
		Thread.sleep(3000);
		homePage.clickNewCustomer();
		logger.info("Navigagted to Add New Customer");
		addCust.enterCustomerName("Anson");
		addCust.selectGender("male");
		addCust.enterDateOfBirth("06", "11", "1988");
		Thread.sleep(3000);
		addCust.enterAddress("India");
		addCust.enterCity("Bangalore");
		addCust.enterState("Karnataka");
		addCust.enterPIN("560021");
		addCust.enterMobileNumber("9988776655");
		addCust.enterEmail(RandomStringUtils.randomAlphabetic(8)+"@gmail.com");
		addCust.enterPassword(RandomStringUtils.randomAlphanumeric(8));
		addCust.clickSubmit();
		Thread.sleep(3000);
		if(driver.getPageSource().contains("Customer Registered Successfully")) {
			captureScreenShot(driver, "AddNewCustomerPass");
			logger.info("AddNewCustomer test passed");
			Assert.assertTrue(true);
		} else {
			captureScreenShot(driver, "AddNewCustomerFail");
			logger.info("AddNewCustomer test failed");
			Assert.assertTrue(false);
		}
	}

}
