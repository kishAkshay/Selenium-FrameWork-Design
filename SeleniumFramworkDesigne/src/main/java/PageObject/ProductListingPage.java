package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponents.AbstractComponent;

public class ProductListingPage extends AbstractComponent {
	
	//List<WebElement> products = driver.findElements(By.xpath("//h5"));
	WebDriver driver;
	
	public ProductListingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath ="//h5/b")
	List<WebElement> products;
	
	By ByProduct = By.xpath("//h5");
	
	@FindBy(xpath = "//button[text()=' Add To Cart']")
	List<WebElement> addToCartIcon;
	
	By toastMessage = By.id("toast-container");
	
	@FindBy(xpath = "//div[contains(@class,'ng-animating')]")
	WebElement spiner;
	
	
	 
	
	//By addToCart = By.xpath("//button[text()=' Add To Cart']");
	
	public List<WebElement> getTheProducts() {
		
		waitForElementToAppear(ByProduct);
		return products;	
	}
	
	public void getProductsByNameAndAddToCart(List<WebElement> products) {
		
		//List<WebElement> products = driver.findElements(By.xpath("//h5"));
		//List<WebElement> products = getTheProducts();
		for (int i = 0; i < products.size(); i++) {
			 String productName = products.get(i).getText();
			
			if (productName.equalsIgnoreCase("IPHONE 13 PRO")) {
				addToCartIcon.get(i).click();
			}
		}
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.id("toast-container"))));
		waitForElementToAppear(toastMessage);
		//		wait.until(ExpectedConditions.invisibilityOf((driver.findElement(By.xpath("//div[contains(@class,'ng-animating')]")))));
		waitForElementToDisappear(spiner);
		
	}

}
