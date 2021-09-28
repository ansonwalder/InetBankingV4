package com.inetbanking.testCases;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.HomePage;
import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilties.XLUtils;

public class TC_LoginDataDrivenTest_002 extends BaseClass {

	@Test(dataProvider = "LoginData")
	public void loginDataDrivenTest(String usn, String pwd) throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = new HomePage(driver);
		loginPage.setUserName(usn);
		logger.info("Entered username");

		loginPage.setPassword(pwd);
		logger.info("Entered password");

		loginPage.clickLogin();
		
		System.out.println("isAlertPresent():"+isAlertPresent());
		
		try {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			captureScreenShot(driver, "LoginDataDrivenTest");
			Thread.sleep(2000);
			logger.warn("Login test failed");
			Assert.assertTrue(false);
		} catch(Exception e) {
			logger.info("Login test passed");
			Assert.assertTrue(true);
			homePage.clickLogout();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			logger.info("Logout is performed and login page is displayed");
		}
		
		/*if(isAlertPresent()) {
			captureScreenShot(driver, "LoginDataDrivenTest");
			Thread.sleep(2000);
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			logger.warn("Login test failed");
			Assert.assertTrue(false);
		} else {
			logger.info("Login test passed");
			Assert.assertTrue(true);
			homePage.clickLogout();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			logger.info("Logout is performed and login page is displayed");
		}*/
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	@DataProvider(name = "LoginData")
	public String[][] getData() throws Exception {
		String path = "./src/test/java/com/inetbanking/testData/LoginData.xlsx";
		int rowCount = XLUtils.getRowCount(path, "Sheet1");
		int colCount = XLUtils.getCellCount(path, "Sheet1", rowCount);
		String loginData[][] = new String[rowCount][colCount];

		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				loginData[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return loginData;
	}
}
