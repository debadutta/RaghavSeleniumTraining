package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import utils.Log;

public class BaseTest {
	protected WebDriver driver;

	@BeforeTest
	public void setup() {
		Log.info("Starting WebDriver...");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		Log.info("Navigating to URL...");
		driver.get("https://admin-demo.nopcommerce.com/login");
	}

	@AfterTest
	public void teardown() {
		if (driver != null)
			Log.info("Closing Browser...");
			driver.quit();

	}

}
