package com.servicenow.util;

import org.testng.annotations.Test;

public class Triggers extends MethodExtensions{
  @Test
  public void Main_prg() throws Exception {
	  Login();
	  branchcreation();
	  staffcreation();
	  search_editbranch();
	  search_editstaff();
	  pageination_and_View();
	  logout();
  }
  
}
