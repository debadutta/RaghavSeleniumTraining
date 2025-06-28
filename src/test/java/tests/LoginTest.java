package tests;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;

public class LoginTest extends BaseTest {
	
	@DataProvider(name="LoginData")
	public Object[][] getLoginData() throws IOException{
		String filePath = System.getProperty("user.dir")+"/testdata/TestData.xlsx";
		ExcelUtils.loadExcel(filePath, "Sheet1");
		int rowCount = ExcelUtils.getRowCount();
		Object[][] data = new Object[rowCount-1][2];
		
		for(int i=1; i<rowCount; i++) {
			data[i-1][0] = ExcelUtils.getCellData(i, 0); //username
			data[i-1][1] = ExcelUtils.getCellData(i, 1); //password
		}		
		ExcelUtils.closeExcel();
		return data;
	}
	
	@DataProvider(name = "LoginData2")
	public Object[][] getData(){
		return new Object[][] {
				{"user1","pass1"	},
				{"user2","pass2"	},
				{"admin@yourstore.com","admin"},
				{"user3","pass3"	},
				{"user4","pass4"	}
		};
		
	}
	
	@Test(dataProvider = "LoginData2")
	public void testValidLogin(String username, String password) {
		Log.info("Starting login test...");
		test=ExtentReportManager.createMyTest("Login Test Verification with valid credential");
		test.info("Navigating to URL");
		LoginPage loginPage = new LoginPage(driver);
		test.info("Adding credentials");
//		loginPage.enterUsername("admin@yourstore.com");
//		loginPage.enterPassword("admin");
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		test.info("Clicking on login button");
		loginPage.clickLogin();
		
		Log.info("Verifying page title...");
		String title = driver.getTitle();
		System.out.println("The title of the page is : "+title);
		
		test.info("Verifying page title");
		Assert.assertEquals("Just a moment...", title);
		
		Log.info("Login test completed");
		test.pass("Login test is successful");
	}
	
	@Test(dependsOnMethods = {"testValidLogin"}, enabled = false)
	public void testInvalidLogin() {
		Log.info("Starting login test...");
		test=ExtentReportManager.createMyTest("Login Test Verification with invalid credential");
		test.info("Navigating to URL");
		LoginPage loginPage = new LoginPage(driver);
		test.info("Adding credentials");
		loginPage.enterUsername("admin1@yourstore.com");
		loginPage.enterPassword("admin1");
		test.info("Clicking on login button");
		loginPage.clickLogin();
		
		Log.info("Verifying page title...");
		String title = driver.getTitle();
		System.out.println("The title of the page is : "+title);
		
		test.info("Verifying page title");
		Assert.assertEquals("Just a moment...", title);
		
		Log.info("Login test completed");
		test.pass("Login test is successful");
	}	

}
