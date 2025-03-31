package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this); 
	}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//PageFactory
	
	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	//WebElement password = driver.findElement(By.id("userPassword"));
	
	@FindBy(id = "userPassword")
	WebElement password1;
	
	//WebElement = loginbut = driver.findElement(By.id("login"));
	
	@FindBy(id="login")
	WebElement loginbut;
	
	@FindBy(xpath="//div[@role='alert']")
	WebElement errorMessage;
	
	public void loginApplication(String email,String password) {
		
		userEmail.sendKeys(email);
		password1.sendKeys(password);
		loginbut.click();
		
	}
	
	public String getTheerrorMessage() {
		waitForWebElementToAppear(errorMessage);
		String messageTest = errorMessage.getText();;
		return messageTest;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	

}
