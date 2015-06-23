package com.servicenow.util;

import java.io.FileInputStream;

import jxl.Sheet;
import jxl.Workbook;
import objrepository.Branch;
import objrepository.LoginInfo;
import objrepository.Logoutinfo;
import objrepository.Pagination;
import objrepository.Serachquery;
import objrepository.Staff;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.servienow.test.PropetiesFile1;

public class MethodExtensions extends Baseselenium  {
	   Actions action;  
	   public  PropetiesFile1 pr=null;
	   WebDriverWait wait;
	 
  public void Login() throws Exception {
	    wait = new WebDriverWait(getDriver(),30);
		pr = new PropetiesFile1();
		action = new Actions(getDriver());
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(pr.geturl()); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginInfo.login)));
		getDriver().findElement(By.xpath(LoginInfo.login)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LoginInfo.Login_id)));
		getDriver().findElement(By.id(LoginInfo.Login_id)).sendKeys(pr.getlogin_id());
		getDriver().findElement(By.id(LoginInfo.Psw_id)).sendKeys(pr.getlogin_pswd());
		getDriver().findElement(By.xpath(LoginInfo.Submit_xpath)).click();
		Thread.sleep(1000);
	  
  }
  
  public void branchcreation() throws Exception{
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Branch.Entities)));
	  WebElement a = getDriver().findElement(By.xpath(Branch.Entities));
	  action.moveToElement(a).click().build().perform();
	  
	  FileInputStream fis = new FileInputStream("C://Users//eigenaar//Desktop//Servicenow.xls");
		Workbook wb =Workbook.getWorkbook(fis);
		Sheet s =wb.getSheet(0);
		WebElement b = getDriver().findElement(By.xpath(Branch.Branch));
		action.moveToElement(b).click().build().perform();
		
	  for(int j=1;j<s.getRows();j++){
		  
		  Thread.sleep(1000);
		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Branch.createbranch)));
		  getDriver().findElement(By.xpath(Branch.createbranch)).click();
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Branch.Name)));
		  String c1= s.getCell(0,j).getContents();
	      Thread.sleep(1000);
	      String c2= s.getCell(1,j).getContents();
	      getDriver().findElement(By.xpath(Branch.Name)).sendKeys(c1);
	      getDriver().findElement(By.xpath(Branch.Code)).sendKeys(c2);
	      getDriver().findElement(By.xpath(Branch.save)).click();
	    }
  }
	    
  public void staffcreation() throws Exception{
	  
	  WebElement d = getDriver().findElement(By.xpath(Branch.Entities));
	  action.moveToElement(d).click().build().perform();
	  
	  WebElement e = getDriver().findElement(By.xpath(Staff.Staff_selection));
	  action.moveToElement(e).click().build().perform();
	  
	  FileInputStream fis = new FileInputStream("C://Users//eigenaar//Desktop//Servicenow.xls");
		Workbook wb =Workbook.getWorkbook(fis);
		Sheet s =wb.getSheet(1);
		
	  for(int k=1;k<s.getRows();k++){
		  
		  Thread.sleep(1000);
		  getDriver().findElement(By.xpath(Staff.Staff_creation)).click();
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Staff.Staff_name)));
		  String e1= s.getCell(0,k).getContents();
		  Thread.sleep(1000);
		  String e2= s.getCell(1,k).getContents();
		  getDriver().findElement(By.xpath(Staff.Staff_name)).sendKeys(e1);
		  getDriver().findElement(By.xpath(Staff.Staff_Branch)).sendKeys(e2);
		  getDriver().findElement(By.xpath(Staff.Staff_Submit)).click();
		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Branch.Entities)));
	  }
  }
  
  public void search_editbranch() throws Exception{
	  
	  	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Branch.Entities)));
	  	WebElement f = getDriver().findElement(By.xpath(Branch.Entities));
	  	action.moveToElement(f).click().build().perform();
	  
	  	WebElement g = getDriver().findElement(By.xpath(Branch.Branch));
	  	action.moveToElement(g).click().build().perform();
		FileInputStream fis = new FileInputStream("C://Users//eigenaar//Desktop//Servicenow.xls");
		Workbook wb =Workbook.getWorkbook(fis);
		Sheet s =wb.getSheet(2);
		for(int l=1;l<s.getRows();l++){
		
			  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Serachquery.Search_xpath)));
			  String h1= s.getCell(0,l).getContents();
			  String h2= s.getCell(1,l).getContents();
			  getDriver().findElement(By.xpath(Serachquery.Search_xpath)).clear();
			  getDriver().findElement(By.xpath(Serachquery.Search_xpath)).sendKeys(h1);
			  getDriver().findElement(By.xpath(Serachquery.Search_enter)).click();
			  Thread.sleep(1000);
			  getDriver().findElement(By.xpath(Serachquery.edit_item)).click();
			  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Branch.Name)));
			  getDriver().findElement(By.xpath(Branch.Name)).clear();
			  getDriver().findElement(By.xpath(Branch.Code)).clear();
			  getDriver().findElement(By.xpath(Branch.Name)).sendKeys(h1);
		 	   getDriver().findElement(By.xpath(Branch.Code)).sendKeys(h2);
		 	  getDriver().findElement(By.xpath(Branch.save)).click();
		 	  Thread.sleep(1000);
		 	 // wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Staff.Delete_item)));
		 	 // getDriver().findElement(By.xpath(Staff.Delete_item)).click();
		 	 //Thread.sleep(3000);
		 	//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Staff.Delete_window)));
		 	//  getDriver().findElement(By.xpath(Staff.Delete_window)).click();
		 	//Thread.sleep(3000);
		  }
  }
  public void search_editstaff() throws Exception{
	
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Branch.Entities)));
	  WebElement m = getDriver().findElement(By.xpath(Branch.Entities));
	  action.moveToElement(m).click().build().perform();
	  WebElement n = getDriver().findElement(By.xpath(Staff.Staff_selection));
	  action.moveToElement(n).click().build().perform();
	  FileInputStream fis = new FileInputStream("C://Users//eigenaar//Desktop//Servicenow.xls");
	  Workbook wb =Workbook.getWorkbook(fis);
	  Sheet s =wb.getSheet(3);
		for(int o=1;o<s.getRows();o++){
			  
			  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Serachquery.Search_xpath)));
			  String p1= s.getCell(0,o).getContents();
			  String p2= s.getCell(1,o).getContents();
			  getDriver().findElement(By.xpath(Serachquery.Search_xpath)).clear();
			  getDriver().findElement(By.xpath(Serachquery.Search_xpath)).sendKeys(p1);
			  getDriver().findElement(By.xpath(Serachquery.Search_enter)).click();
			  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Serachquery.edit_item)));
			  getDriver().findElement(By.xpath(Serachquery.edit_item)).click();
			  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Staff.Staff_name)));
			  getDriver().findElement(By.xpath(Staff.Staff_name)).clear();
			  getDriver().findElement(By.xpath(Staff.Staff_name)).sendKeys(p1);
		 	   getDriver().findElement(By.xpath(Staff.Staff_Submit)).click();
		 	   Thread.sleep(1000);
		 	 //   getDriver().findElement(By.xpath(Staff.Delete_item)).click(); 
		 	 //  Thread.sleep(1000);
		 	  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Staff.Delete_window)));
		 	  //  getDriver().findElement(By.xpath(Staff.Delete_window)).click();
		 	   
		 	   
		  }
	  
  }
  
  public void pageination_and_View() throws Exception{
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Pagination.forward)));
		getDriver().findElement(By.xpath(Pagination.forward)).click();
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		getDriver().findElement(By.xpath(Pagination.Backward)).click();
		action.sendKeys(Keys.PAGE_UP).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Pagination.View_button)));
		getDriver().findElement(By.xpath(Pagination.View_button)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Pagination.View_back)));
		getDriver().findElement(By.xpath(Pagination.View_back)).click();
		Thread.sleep(1000);
  }
  
  
  public void logout(){
	  WebElement q= getDriver().findElement(By.xpath(Logoutinfo.Account_xpath));
	  action.moveToElement(q).click().build().perform();
	  WebElement r = getDriver().findElement(By.xpath(Logoutinfo.Logout_xpath));
	  action.moveToElement(r).click().build().perform();
  }
  
  }

