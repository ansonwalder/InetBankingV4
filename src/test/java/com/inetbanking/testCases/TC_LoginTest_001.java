package com.inetbanking.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.HomePage;
import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void loginTest() {
		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = new HomePage(driver);

		loginPage.setUserName(username);
		logger.info("Entered username");

		loginPage.setPassword(password);
		logger.info("Entered password");

		loginPage.clickLogin();
		logger.info("Clicked login button");

		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			logger.info("Login test passed");
			Assert.assertTrue(true);
			homePage.clickLogout();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			logger.info("Logout is performed and login page is displayed");
		} else {
			logger.info("Login test failed");
			captureScreenShot(driver, "loginTest");
			Assert.assertTrue(false);
		}
	}
}
