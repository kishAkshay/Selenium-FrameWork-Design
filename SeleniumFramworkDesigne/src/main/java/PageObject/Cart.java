package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractComponents.AbstractComponent;

public class Cart extends AbstractComponent {
	WebDriver driver;

	public Cart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List<WebElement> cartProducts =
	// driver.findElements(By.xpath("//div[@class='cartSection']/h3"));

	@FindBy(xpath = "//div[@class='cartSection']/h3")
	List<WebElement> cartProducts;

	// driver.findElement(By.xpath("//ul //button[@type='button']")).click();
	@FindBy(xpath = "//ul //button[@type='button']")
	WebElement checkoutbutn;

	public boolean checkProductAddedTocard(String productname) {
		boolean flag = false;
		for (int i = 0; i < cartProducts.size(); i++) {

			String Text = cartProducts.get(i).getText();
			if (Text.equalsIgnoreCase(productname)) {
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;
	}

	public void clickOnCheckoutbutton() {

		checkoutbutn.click();
	}

}
