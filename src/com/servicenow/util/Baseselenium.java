package com.servicenow.util;

import static org.junit.Assert.fail;

import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.servienow.test.TestManager;


public class Baseselenium {
	protected StringBuilder verificationErrors = new StringBuilder();

	/* Need setUp and tearDownSelenium methods in each Test Case to 
	 * handle the situation where a single case is being run independently of 
	 * the suite.  If the suite is being run the calls should have no effect.
	 */
	
	@BeforeClass
	public static void setUpSelenium() throws Exception {
		TestManager.getInstance().loadSelenium();
	}
	
	//@AfterClass
	//public static void tearDownSelenium() throws Exception {
		//Seleniumtestmanager.getInstance().unloadSeleniumForTest();
	//}
	
	@After
	public void tearDown() throws Exception {
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
	
	public WebDriver getDriver() {
		return TestManager.getInstance().getDriver();
	}

}
