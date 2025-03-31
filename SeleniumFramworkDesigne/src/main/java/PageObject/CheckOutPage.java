package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("india");
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement DropDown;
	
	By OptionList = By.xpath("//section[contains(@class,'list-group')]");
	
	//driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
	WebElement countryName;
	//driver.findElement(By.cssSelector(".action__submit")).click();
	@FindBy(css = ".action__submit")
	WebElement submitButton;
	
	
		public void enterCountryNameInDropDownAndWaitForOptionAppear(String countryName) {
			
			DropDown.sendKeys(countryName);
			
			waitForElementToAppear(OptionList);
			
		}
		
		public void selectCountyNameFromOptions() {
			countryName.click();
		}
		
		public void clickOnSubmitButton() {
			submitButton.click();
		}
		
		
		
		
		
}
