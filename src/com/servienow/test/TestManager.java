package com.servienow.test;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;



public class TestManager {
public TestManager() {}
							
	private static final TestManager instance = new TestManager();
	
	private WebDriver driver;
	private Properties props;
	
	private boolean suiteRun;
	private boolean loaded;
	
	public static TestManager getInstance() {
		return instance;
	}
	
	public void setSuiteRun(boolean suiteRun) {
		this.suiteRun = suiteRun;
	}
	
	public void loadSelenium() throws Exception {
		if (!loaded) {
			loadPropsAndInitialize();
		}
	}
	
	private synchronized void loadPropsAndInitialize() throws Exception {
		if (!loaded) {
			String propFileName = System.getProperty("selenium.propfile", "ci.selenium.prop");
			props= new Properties();
			props.load(ClassLoader.getSystemResourceAsStream(propFileName));
			String seleniumServerUrl = props.getProperty("selenium.server.url");
			String browserType = props.getProperty("browser.type");
			 initiateWebDriver(seleniumServerUrl, browserType);
			loaded = true;
		}
	}
	
	private WebDriver initiateWebDriver(String seleniumServerUrl, String browserType) throws Exception {
		DesiredCapabilities dc = new DesiredCapabilities();
		if ("firefox".equalsIgnoreCase(browserType)) {
			driver= new FirefoxDriver();
		} else if ("ie".equalsIgnoreCase(browserType)) {
			System.setProperty("webdriver.ie.driver", "C://Users//eigenaar//Desktop//seleniumsripts//BaseProject//Drivers//IEDriverServer.exe");
			driver= new InternetExplorerDriver(dc);
		}
		else if ("chrome".equalsIgnoreCase(browserType)) {
			System.setProperty("webdriver.chrome.driver", "C://Users//eigenaar//Desktop//seleniumsripts//BaseProject//Drivers//chromedriver.exe");
			driver=new ChromeDriver(dc);
		}
		return driver;
	}
		public void setQuickTimeout() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	
	    public void unloadSeleniumForTest() throws Exception {
		if (!suiteRun) {
			driver.quit();
	}
	}
	
       public void unloadSeleniumForTestSuite() throws Exception {
		 if (suiteRun) {
			  driver.quit();
		}
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}
}