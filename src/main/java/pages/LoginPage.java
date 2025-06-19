package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Log;

public class LoginPage {
	
	private WebDriver driver;
	
//	private By usernameTextBox = By.id("Email");
//	private By passwordTextBox = By.className("password");
//	private By loginButton = By.cssSelector(".login-button");
	
	@FindBy(id="Email")
	WebElement usernameTextBox;
	
	@FindBy(className="password")
	WebElement passwordTextBox;
	
	@FindBy(css=".login-button")
	WebElement loginButton;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void enterUsername(String username) {
		Log.info("Entering username: "+username);
		usernameTextBox.clear();
		usernameTextBox.sendKeys(username);
		
	}
	
	public void enterPassword(String password) {
		Log.info("Entering password: ******");
		passwordTextBox.clear();
		passwordTextBox.sendKeys(password);
	}
	
	public void clickLogin() {
		Log.info("Clicking login button...");
		loginButton.click();
		
	}
	

}
